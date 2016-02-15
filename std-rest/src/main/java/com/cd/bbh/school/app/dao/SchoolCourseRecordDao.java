package com.cd.bbh.school.app.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.app.vo.CourseRecord;

/**
 * 
 * @author weijing
 *
 */
public interface SchoolCourseRecordDao {

	/**
	 * 查询相应时间段的课程
	 * @param teacherId
	 * @param beginDate
	 * @param finishDate
	 * @return
	 */
	List<CourseRecord> selectCourseRecord(@Param("teacherId") long teacherId, @Param("beginDate") Date beginDate,  @Param("finishDate") Date finishDate);
}
