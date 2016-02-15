package com.cd.bbh.parent.login.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.login.vo.LoginInfoVO;

@Repository
public interface LoginDao {

	/**
	 * 根据手机或者邮箱检查用户是否存在
	 * 
	 * @return
	 */
	int checkUserExists(String column);

	/**
	 * 注册用户，手机端只能注册父母种类的用户
	 * 
	 * @param cellPhone
	 * @param password
	 * @param deviceSerialNo
	 * @return
	 */
	int insertUser(Map<String, Object> params);

	/**
	 * 根据电话或者email查询用户信息
	 * 
	 * @param column
	 * @return
	 */
	LoginInfoVO selectUserByUnique(String column);

	/**
	 * 根据电话或者email查询用户信息
	 * 
	 * @param column
	 * @return
	 */
	LoginInfoVO selectUser(Long userid);

	/**
	 * 更新用户密码
	 * 
	 * @param password
	 * @param userid
	 * @return
	 */
	int updatePassword(Map<String, Object> params);

	/**
	 * 更新用户IMEI
	 * 
	 * @param IMEI
	 * @param userid
	 * @return
	 */
	int updateDeviceNo(Map<String, Object> params);

}