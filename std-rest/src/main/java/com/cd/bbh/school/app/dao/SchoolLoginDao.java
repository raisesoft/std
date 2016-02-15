package com.cd.bbh.school.app.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.cd.bbh.school.app.vo.SchoolInfo;
import com.cd.bbh.school.app.vo.TeacherInfo;

public interface SchoolLoginDao {

	/**
	 * 教师登录
	 * @param phone
	 * @param pass
	 * @return
	 */
	LoginInfoVO selectTeacher(@Param("phone") String phone,@Param("pass") String pass);
	
	/**
	 * 校长登录
	 * @param phone
	 * @param pass
	 * @return
	 */
	LoginInfoVO selectSchool(@Param("phone") String phone,@Param("pass") String pass);
	
	/**
	 * 验证电话号是否存在
	 * @param phone
	 * @return
	 */
	long selectUserCount(@Param("phone") String phone);
	
	/**
	 * 更新密码
	 * @param phone
	 * @param pass
	 * @return
	 */
	int updatePassword(@Param("phone") String phone,@Param("pass") String pass);
	
	/**
	 * 获取教师信息
	 * @param id
	 * @return
	 */
	TeacherInfo selectTeacherInfoById(@Param("id") long id);
	
	/**
	 * 获取学校信息
	 * @param id
	 * @return
	 */
	SchoolInfo selectSchoolInfoById(@Param("id") long id);
	
	/**
	 * 更新教师信息
	 * @param data
	 * @return
	 */
	int updateTeacherInfo(@Param("t") Map<String, Object> data);
	
	/**
	 * 更新学校信息
	 * @param data
	 * @return
	 */
	int updateSchoolInfo(@Param("s") Map<String, Object> data);
	
	
}
