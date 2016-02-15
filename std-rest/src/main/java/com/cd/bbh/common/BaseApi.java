package com.cd.bbh.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.common.exception.JsonException;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.common.utils.RedisUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BaseApi {
	private static final Logger logger = LoggerFactory.getLogger(BaseApi.class);

	@Resource(name = "redisUtils")
	protected RedisUtils redis;

	// @Resource(name = "jpush")
	// protected JPushSender jpush;

	@Autowired
	protected Constant constant;

	protected static ObjectMapper mapper = new ObjectMapper();

	/**
	 * 获取IMEI号，IPHONE为唯一序号
	 * 
	 * @param params
	 * @return
	 */
	protected String getImei(String params) {
		return parseParam(params, "imei").textValue().trim();
	}

	/**
	 * 获取app版本号
	 * 
	 * @param params
	 * @return
	 */
	protected String getAppVersion(String params) {
		return parseParam(params, "appversion").textValue().trim();
	}

	/**
	 * 获取手机系统版本号
	 * 
	 * @param params
	 * @return
	 */
	protected String getSysVersion(String params) {
		return parseParam(params, "sysversion").textValue().trim();
	}

	/**
	 * 获取访问token，未登录用户没有token。
	 * 
	 * @param params
	 * @return
	 */
	protected String getAccessToken(String params) {
		return parseParam(params, "access_token").textValue().trim();
	}

	/**
	 * 获取加密key
	 * 
	 * @param params
	 * @return
	 */
	protected String getEncryptKey(String params) {
		return parseParam(params, "encryptkey").textValue().trim();
	}

	/**
	 * 获取访问的手机端类型(教师端(T)，家长端（P）)
	 * 
	 * @param params
	 * @return
	 */
	protected String getClientType(String params) {
		return parseParam(params, "clientType").textValue().trim();
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected String getParamTextValue(String params, String key) {
		try {
			String value = getParams(params).get(key).textValue().trim();
			if (StringUtils.isBlank(value)) {
				throw new ApplicationException(ResultEnum.PARAM_NULL_ERROR.code(), String.format(ResultEnum.PARAM_NULL_ERROR.desc(), key), null);
			}
			return value;
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected String getParamTextValue(String params, String key, String defaultValue) {
		try {
			return getParams(params).get(key).textValue().trim();
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 获取整数业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected Integer getParamIntValue(String params, String key) {
		try {
			return getParams(params).get(key).intValue();
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取整数业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Integer getParamIntValue(String params, String key, Integer defaultValue) {
		try {
			return getParams(params).get(key).intValue();
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 获取长整形业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected Long getParamLongValue(String params, String key) {
		try {
			return getParams(params).get(key).longValue();
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取长整形业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Long getParamLongValue(String params, String key, Long defaultValue) {
		try {
			return getParams(params).get(key).longValue();
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected Double getParamDobuleValue(String params, String key) {
		try {
			return getParams(params).get(key).doubleValue();
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Double getParamDobuleValue(String params, String key, Double defaultValue) {
		try {
			return getParams(params).get(key).doubleValue();
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected Float getParamFloatValue(String params, String key) {
		try {
			return getParams(params).get(key).floatValue();
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Float getParamFloatValue(String params, String key, Float defaultValue) {
		try {
			return getParams(params).get(key).floatValue();
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @return
	 */
	protected Date getParamDateValue(String params, String key) {
		try {
			return DateUtils.parseDateTime(getParams(params).get(key).textValue());
		} catch (NullPointerException ex) {
			throw new ApplicationException(ResultEnum.PARAM_MISSING.code(), String.format(ResultEnum.PARAM_MISSING.desc(), key), ex);
		}
	}

	/**
	 * 获取业务参数值
	 * 
	 * @param params
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	protected Date getParamDateValue(String params, String key, Date defaultValue) {
		try {
			return DateUtils.parseDateTime(getParams(params).get(key).textValue());
		} catch (NullPointerException ex) {
			return defaultValue;
		}
	}

	/**
	 * 根据业务参数创建分页对象
	 * 
	 * @param params
	 * @return
	 */
	protected Pageable getPageable(String params) {
		return getPageable(params, new Pageable(10, 0));
	}

	/**
	 * 根据业务参数创建分页对象
	 * 
	 * @param params
	 * @return
	 */
	protected Pageable getPageable(String params, Pageable pageable) {
		try {

			int pagesize = getParamIntValue(params, "pagesize");
			int page = getParamIntValue(params, "page");
			if ((pagesize <= 0 || page <= 0)) {
				return (pageable == null ? new Pageable(10, 0) : pageable);
			} else {
				return new Pageable(pagesize, page);
			}
		} catch (Exception ex) {
			logger.warn("Pageable data error. Use the default...");
			return (pageable == null ? new Pageable(10, 0) : pageable);
		}
	}

	/**
	 * 获取业务参数
	 * 
	 * @param params
	 * @return
	 */
	protected JsonNode getParams(String params) {
		return parseParam(params, "data");
	}

	/**
	 * 获取redis中的用户信息
	 * 
	 * @param params
	 * @return
	 */
	protected JsonNode getCurrentUserInfo(String params) {
		return redis.getInfoByAccessToken(getAccessToken(params));
	}

	/**
	 * 获取redis中的用户信息
	 * 
	 * @param params
	 * @param key
	 *            可以使用的有(logintime, gid, role, auth, imei, state, account,
	 *            email, username)
	 * @return
	 */
	protected String getCurrentUserInfo(String params, String key) {
		return redis.getInfoByAccessToken(getAccessToken(params)).get(key).textValue();
	}

	/**
	 * 获取用户ID
	 * 
	 * @param params
	 * @return
	 */
	protected Long getCurrentUserID(String params) {
		return Long.parseLong(redis.getInfoByAccessToken(getAccessToken(params)).get("gid").textValue());
	}

	/**
	 * 获取用户角色
	 * 
	 * @param params
	 * @return
	 */
	protected RoleEnum getCurrentUserRoleEnum(String params) {
		String roleStr = redis.getInfoByAccessToken(getAccessToken(params)).get("role").textValue();
		String auth = redis.getInfoByAccessToken(getAccessToken(params)).get("auth").textValue();
		if (roleStr.equals(RoleEnum.TEACHER.siginal())) {
			return RoleEnum.TEACHER;
		} else if (roleStr.equals(RoleEnum.SCHOOL.siginal())) {
			return RoleEnum.SCHOOL;
		} else if (StringUtils.isNotBlank(auth) && auth.equals(RoleEnum.PAUTH)) {
			return RoleEnum.PAUTH;
		} else if (StringUtils.isNotBlank(auth) && auth.equals(RoleEnum.PNORMAL)) {
			return RoleEnum.PNORMAL;
		} else {
			throw new ApplicationException(ResultEnum.NOT_AUTH_ERROR);
		}
	}

	/**
	 * 获取用户角色
	 * 
	 * @param params
	 * @return
	 */
	protected Long getCurrentUserRole(String params) {
		return Long.parseLong(redis.getInfoByAccessToken(getAccessToken(params)).get("role").textValue());
	}

	/**
	 * 获取用户名
	 * 
	 * @param params
	 * @return
	 */
	protected String getCurrentUsername(String params) {
		return redis.getInfoByAccessToken(getAccessToken(params)).get("username").textValue();
	}

	protected void writeResult(Object object, OutputStream out) {
		try {
			out.write(mapper.writeValueAsString(object).getBytes("UTF-8"));
		} catch (JsonProcessingException | UnsupportedEncodingException cause) {
			throw new JsonException(ResultEnum.PARAM_PATTERN_ERROR, cause);
		} catch (IOException cause) {
			throw new ApplicationException(ResultEnum.UNKNOW_ERROR, cause);
		}
	}

	private JsonNode parseParam(String params, String key) {
		if (StringUtils.isEmpty(params)) {
			throw new JsonException(ResultEnum.PARAM_NULL_ERROR);
		}

		params = params.trim();
		try {
			return mapper.readTree(params).get(key);
		} catch (IOException cause) {
			throw new JsonException(ResultEnum.PARAM_PATTERN_ERROR, cause);
		}
	}
}
