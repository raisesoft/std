package com.cd.bbh.common.section;

import java.lang.annotation.Annotation;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cd.bbh.common.enums.ClientTypeEnum;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.common.exception.JsonException;
import com.cd.bbh.common.exception.RedisOperationException;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class PermAspcet {

	private Logger logger = LoggerFactory.getLogger(PermAspcet.class);

	@Autowired
	private RedisUtils redisUtils;
	private static final ObjectMapper mapper = new ObjectMapper();

	@Pointcut("execution(* com.cd.bbh.*.*.webapi.*.*(..))")
	private void paramCheck() {
	}//

	@Around("paramCheck()")
	public Object doParamCheck(ProceedingJoinPoint point) {

		Annotation[] annotations = ((MethodSignature) point.getSignature()).getMethod().getAnnotations();
		RequestMapping reqClassMapping = point.getTarget().getClass().getAnnotation(RequestMapping.class);
		RequestMapping methodMapping = ((MethodSignature) point.getSignature()).getMethod().getAnnotation(RequestMapping.class);
		try {
			Object[] params = point.getArgs();
			if (params != null && params.length > 0) {
				logger.debug("");
				logger.debug(params[0].toString().replace("\n", ""));

				JsonNode paramters = mapper.readTree(params[0].toString());
				paramsValidation(paramters);
				permissionValidation(annotations, paramters);

				long start = System.currentTimeMillis();
				Object result = point.proceed();
				long end = System.currentTimeMillis();

				logger.trace(mapper.writeValueAsString(result));
				logger.debug(String.format("%s/%s execute time: %dms", reqClassMapping.value()[0], methodMapping.value()[0], end - start));
				return result;
			} else {
				throw new ApplicationException(ResultEnum.PARAM_NULL_ERROR.code(), String.format(ResultEnum.PARAM_NULL_ERROR.desc(), ""), null);
			}
		} catch (JsonProcessingException exception) {
			logger.debug(String.format("%s/%s execute failed..", reqClassMapping.value()[0], methodMapping.value()[0]));
			throw new JsonException(ResultEnum.PARAM_PATTERN_ERROR, exception);
		} catch (Throwable cause) {
			logger.debug(String.format("%s/%s execute failed..", reqClassMapping.value()[0], methodMapping.value()[0]));
			if (cause instanceof ApplicationException || cause instanceof JsonException || cause instanceof RedisOperationException) {
				throw (ApplicationException) cause;
			} else {
				throw new ApplicationException(ResultEnum.UNKNOW_ERROR, cause);
			}
		}
	}

	/**
	 * <strong>检查基本参数</strong>
	 * 
	 * @param params
	 */
	private void paramsValidation(JsonNode node) {
		JsonNode imei = node.get("imei");
		if (imei == null || StringUtils.isBlank(imei.textValue())) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "imei"), null);
		}
		JsonNode clientType = node.get("clientType");
		if (clientType == null || StringUtils.isBlank(clientType.textValue())) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "clientType"), null);
		}
		JsonNode sysversion = node.get("sysversion");
		if (sysversion == null || StringUtils.isBlank(sysversion.textValue())) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "sysversion"), null);
		}
		JsonNode appversion = node.get("appversion");
		if (appversion == null || StringUtils.isBlank(appversion.textValue())) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "appversion"), null);
		}
		JsonNode encryptkey = node.get("encryptkey");
		if (encryptkey == null || StringUtils.isBlank(encryptkey.textValue())) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "encryptkey"), null);
		}
		JsonNode accessToken = node.get("access_token");
		if (accessToken == null) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), "access_token"), null);
		}
	}

	/**
	 * <strong>根据注解检查权限</strong>
	 * 
	 * @param annotations
	 * @param params
	 */
	private void permissionValidation(Annotation[] annotations, JsonNode params) {
		for (Annotation annotation : annotations) {
			if (annotation instanceof BBHPerm) {
				JsonNode node = checkUserSession(params.get("access_token").textValue());
				String clientType = params.get("clientType").textValue();
				if (clientType.equals(ClientTypeEnum.PARENT.siginal())) {
					checkParentPermission(node, ((BBHPerm) annotation).value());
					continue;
				}
				if (clientType.equals(ClientTypeEnum.SCHOOL.siginal())) {
					checkSchoolPermission(node, ((BBHPerm) annotation).value());
					continue;
				}
				if (clientType.equals(ClientTypeEnum.PUNCH.siginal())) {
					continue;
				}
				throw new ApplicationException(ResultEnum.NOT_AUTH_ERROR);
			}
		}
	}

	/**
	 * <strong>根据请求检查用户</strong><br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检查用户是否登录<br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检查用户状态（是否被禁止访问）<br/>
	 * 
	 * @param accessToken
	 * @return 用户一般信息
	 */
	private JsonNode checkUserSession(String accessToken) {
		if (StringUtils.isBlank(accessToken)) {
			throw new ApplicationException(ResultEnum.NOT_LOGIN_ERROR);
		}
		JsonNode node = redisUtils.getInfoByAccessToken(accessToken);
		if (node == null) {
			throw new ApplicationException(ResultEnum.NOT_LOGIN_ERROR);
		}

		String userState = node.get("state").textValue();
		if (!UserStateEnum.ACTIVE.siginal().equals(userState) && !UserStateEnum.CREATED.siginal().equals(userState)) {
			throw new ApplicationException(ResultEnum.NOT_ALLOWED_ERROR);
		}
		return node;
	}

	/**
	 * <strong>根据请求检查公共版用户</strong><br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检查用户是否被授权<br/>
	 * 
	 * @param node
	 * @param perm
	 *            接口允许的权限
	 */
	private void checkParentPermission(JsonNode node, RoleEnum methodPerm) {
		String userRole = node.get("role").textValue();
		String currentAuth = node.get("auth").textValue();
		if (userRole.equals("50")) {
			if (currentAuth.equals(RoleEnum.PAUTH.siginal())) {// 认证家长
				return;
			} else if (methodPerm.siginal().equals(currentAuth)) {// 未认证家长权限与注解匹配
				return;
			}
		}
		throw new ApplicationException(ResultEnum.NOT_AUTH_ERROR);
	}

	/**
	 * <strong>根据请求检查教师端用户</strong><br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;检查教师端用户是否被授权<br/>
	 * 
	 * @param node
	 * @param perm
	 *            接口允许的权限
	 */
	private void checkSchoolPermission(JsonNode node, RoleEnum methodPerm) {
		if (methodPerm.siginal().equals(node.get("role").textValue())) {
			return;
		}
		throw new ApplicationException(ResultEnum.NOT_AUTH_ERROR);
	}
}
