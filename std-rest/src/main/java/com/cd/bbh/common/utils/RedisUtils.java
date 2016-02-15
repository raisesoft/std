package com.cd.bbh.common.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.common.exception.JsonException;
import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RedisUtils {

	@Autowired
	private Constant configuration;

	private static ObjectMapper mapper = new ObjectMapper();
	@Autowired
	protected RedisTemplate<Serializable, Serializable> redisTemplate;

	/**
	 * 检查accessToken是否存在,并更新失效时间, 不存在则会抛出异常
	 * 
	 * @param accessToken
	 */
	public void checkAccessToken(String accessToken) {
		redisTemplate.execute(new RedisCallback<Void>() {
			@Override
			public Void doInRedis(RedisConnection connection) throws DataAccessException {
				if (connection.exists(accessToken.getBytes())) {
					connection.expire(accessToken.getBytes(), configuration.sessionExpireTime * 60);
					return null;
				}
				throw new ApplicationException(ResultEnum.NOT_LOGIN_ERROR);
			}
		});
	}

	/**
	 * 生成token并保存到redis中。
	 * 
	 * @param user
	 * @param imei
	 * @return
	 */
	public String generateAccessToken(LoginInfoVO user, String imei) {
		try {
			final String accessToken = UUID.randomUUID().toString().replace("-", "");
			Map<String, String> baseData = new HashMap<String, String>();
			baseData.put("gid", Long.toString(user.getUserid()));
			baseData.put("account", user.getAccount());
			baseData.put("email", user.getEmail());
			baseData.put("username", user.getUsername());
			baseData.put("role", Long.toString(user.getRoles()));
			baseData.put("state", user.getState());
			baseData.put("auth", user.getAuth());
			baseData.put("logintime", DateUtils.formatDateTime(DateUtils.getCurrDate()));
			baseData.put("imei", imei);
			redisTemplate.opsForSet().add(accessToken, mapper.writeValueAsString(baseData));
			redisTemplate.expire(accessToken, configuration.sessionExpireTime, TimeUnit.MINUTES);
			return accessToken;
		} catch (JsonProcessingException cause) {
			throw new JsonException(ResultEnum.UNKNOW_ERROR, cause);
		}
	}

	/**
	 * 检查短信验证码并删除，操作成功返回true，操作失败返回false
	 * 
	 * @param phone
	 * @param smscode
	 * @param type
	 *            功能类型
	 * @return
	 */
	public boolean removeSmscode(String phone, String smscode, int type) {
		boolean status = redisTemplate.opsForSet().isMember(phone + type, smscode);
		if (status) {
			redisTemplate.opsForSet().remove(phone + type, smscode);
		}
		return status;
	}

	/**
	 * * 保存smscode到redis中，有效时间内不能重复发送.
	 * 
	 * @param phone
	 * @param smscode
	 * @param type
	 *            功能类型
	 */
	public void saveSmscode(String phone, String smscode, int type) {
		String smscodeKey = phone + type;
		if (redisTemplate.hasKey(smscodeKey)) {
			throw new ApplicationException(ResultEnum.SMSCODE_SEND_ERROR.code(), //
					String.format(ResultEnum.SMSCODE_SEND_ERROR.desc(), configuration.smscodeExpireTime), null);
		}
		redisTemplate.opsForSet().add(smscodeKey, smscode);
		redisTemplate.expire(smscodeKey, configuration.smscodeExpireTime, TimeUnit.MINUTES);
	}

	/**
	 * 根据access token 获取对应用户信息
	 * 
	 * @param token
	 * @return
	 */
	public JsonNode getInfoByAccessToken(String token) {
		try {
			checkAccessToken(token);
			Set<Serializable> userStrs = redisTemplate.opsForSet().members(token);
			for (Serializable serializable : userStrs) {
				if (serializable instanceof String) {
					return mapper.readTree((String) serializable);
				}
			}
			throw new ApplicationException(ResultEnum.NOT_LOGIN_ERROR);
		} catch (IOException e) {
			throw new JsonException(ResultEnum.UNKNOW_ERROR, e);
		}
	}
}
