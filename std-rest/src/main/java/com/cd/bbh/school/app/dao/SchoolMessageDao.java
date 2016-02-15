package com.cd.bbh.school.app.dao;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.app.vo.Message;

public interface SchoolMessageDao {

	int insertMsgByTeacher(@Param("msg") Message msg);
}
