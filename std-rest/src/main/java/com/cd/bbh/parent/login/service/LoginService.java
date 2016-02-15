package com.cd.bbh.parent.login.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.section.UserStateEnum;
import com.cd.bbh.parent.login.dao.LoginDao;
import com.cd.bbh.parent.login.vo.LoginInfoVO;

@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * 根据手机或者邮箱检查用户是否存在
	 * 
	 * @return
	 */
	public boolean checkUserExists(String column) {
		return loginDao.checkUserExists(column) > 0;
	}

	/**
	 * 注册用户，手机端只能注册父母种类的用户
	 * 
	 * @param cellPhone
	 * @param password
	 * @param deviceSerialNo
	 * @return
	 */
	public int createUser(String cellPhone, String password, String deviceSerialNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("roleid", Constant.ROLE_PARENT);
		params.put("cellphone", cellPhone);
		params.put("password", password);
		params.put("device", deviceSerialNo);
		params.put("userstat", UserStateEnum.ACTIVE.siginal());
		params.put("parentstat", RoleEnum.PNORMAL.siginal());
		return loginDao.insertUser(params);
	}

	/**
	 * 根据电话或者email查询用户信息
	 * 
	 * @param email
	 *            /phone
	 * @return
	 */
	public LoginInfoVO findUserByUnique(String column) {
		return loginDao.selectUserByUnique(column);
	}

	/**
	 * 更新用户密码
	 * 
	 * @param password
	 * @param userid
	 * @return
	 */
	public int updatePassword(String password, Long userid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("password", password);
		return loginDao.updatePassword(params);
	}

	public int updateParentDeviceNo(Long userid, String imei) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userid", userid);
		params.put("imei", imei);
		return loginDao.updateDeviceNo(params);
	}
}
