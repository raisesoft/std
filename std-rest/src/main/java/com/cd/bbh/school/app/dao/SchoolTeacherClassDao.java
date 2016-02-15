package com.cd.bbh.school.app.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.app.vo.ClassSimple;
import com.cd.bbh.school.app.vo.StudentClassSchool;

public interface SchoolTeacherClassDao {

	/**
	 * 获取当前教师的班级列表
	 * @param teacherId
	 * @return
	 */
	List<ClassSimple> selectClassByTeacher(@Param("teacherId") long teacherId);
	
	/**
	 * 根据学校查询班级列表
	 * @param principalId
	 * @return
	 */
	List<ClassSimple> selectClassBySchool(@Param("schoolId") long schoolId);
	
	/**
	 * 根据学校查询班级Id列表
	 * @param principalId
	 * @return
	 */
	List<String> selectClassIdBySchool(@Param("schoolId") long schoolId);
	
	/**
	 * 根据教师获取学校ID
	 * @param teacherId
	 * @return
	 */
	Long selectSchoolIdByTeacher(@Param("teacherId") long teacherId);
	
	/**
	 * 根据学校获取代理商ID
	 * @param schoolId
	 * @return
	 */
	Long selectAgentIdBySchool(@Param("schoolId") long schoolId);
	
	/**
	 * 獲取用戶名
	 * @param id
	 * @return
	 */
	String selectUserNameById(@Param("id") long id);
	
	
	List<StudentClassSchool> selectStudentClassSchool(@Param("ids") List<Long> ids);
}
