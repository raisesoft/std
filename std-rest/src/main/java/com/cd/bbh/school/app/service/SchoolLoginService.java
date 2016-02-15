package com.cd.bbh.school.app.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.utils.MD5Utils;
import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.cd.bbh.school.app.dao.SchoolLoginDao;
import com.cd.bbh.school.app.vo.UserInfo;

@Service
public class SchoolLoginService {
	
	@Autowired
	private SchoolLoginDao sld;

	/**
	 * 登录
	 * @param phone
	 * @param password
	 * @return
	 */
	public LoginInfoVO doLogin(String phone, String password) {
		password = MD5Utils.getMD5(password);
		LoginInfoVO u = sld.selectTeacher(phone, password);
		if(u != null) {
			u.setRoles(Constant.ROLE_TEACHER);
		} else {
			u = sld.selectSchool(phone, password);
			if(u != null) {
				u.setRoles(Constant.ROLE_SCHOOL_MASTER);
			}
		}
		
		return u;
	}
	
	public long selectUserCount(String phone) {
		return sld.selectUserCount(phone);
	}
	
	/**
	 * 更新密码
	 * @param phone
	 * @param pass
	 * @return
	 */
	public boolean updatePassword(String phone, String pass) {
		boolean flag = false;
		if(sld.selectUserCount(phone) >0) {
			pass = MD5Utils.getMD5(pass);
			if(sld.updatePassword(phone, pass) > 0) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	/**
	 * 获取用户信息
	 * @param id
	 * @param roles
	 * @return
	 */
	public UserInfo selectUserInfo(long id, RoleEnum roles) {
		UserInfo u = null;
		if(roles.equals(RoleEnum.TEACHER)) {
			u = sld.selectTeacherInfoById(id);
		} else if(roles.equals(RoleEnum.SCHOOL)) {
			u =sld.selectSchoolInfoById(id);
		}
		return u;
	}
	
	public boolean updateUserInfo(Map<String, Object> data, RoleEnum roles) {
		boolean flag = false;
		int length = 0;
		if(roles.equals(RoleEnum.TEACHER)) {
			length = sld.updateTeacherInfo(data);
		} else if(roles.equals(RoleEnum.SCHOOL)) {
			length = sld.updateSchoolInfo(data);
		}
		if(length > 0) {
			flag = true;
		}
		
		return flag;
	}
}
