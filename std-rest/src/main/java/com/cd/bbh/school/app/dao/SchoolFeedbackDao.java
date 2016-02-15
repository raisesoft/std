package com.cd.bbh.school.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.app.vo.Feedback;
import com.cd.bbh.school.app.vo.FeedbackItem;
import com.cd.bbh.school.app.vo.FeedbackItemRecord;
import com.cd.bbh.school.app.vo.FeedbackItemVO;
import com.cd.bbh.school.app.vo.FeedbackRecord;
import com.cd.bbh.school.app.vo.FeedbackVO;

public interface SchoolFeedbackDao {
	
	/**
	 * 获取学校模板
	 * @param schoolId
	 * @return
	 */
	List<Feedback> selectFeedbackBySchool(@Param("schoolId") long schoolId);
	
	/**
	 * 获取反馈信息列表
	 * @param teacher
	 * @return
	 */
	List<FeedbackVO> selectFeedbackByTeacher(@Param("teacherId") long teacher, @Param("search") Map<String, Object> search);
	
	/**
	 * 获取指定模板
	 * @param id
	 * @return
	 */
	Feedback selectFeedbackById(@Param("id") long id);
	
	/**
	 * 获取指定模板选项
	 * @param feedbackId
	 * @return
	 */
	List<FeedbackItem> selectFeedbackItemByFeedBackId(@Param("feedbackId") long feedbackId);
	
	/**
	 * 查成绩
	 * @param recordId
	 * @return
	 */
	List<FeedbackItemVO> selectFeedbackItemByTeacher(@Param("feedbackIds") List<Long> feedbackIds);
	
	/**
	 * 反馈表提交分数
	 * @param firs
	 * @return
	 */
	int insertFeedbackItemRecord(@Param("firs") List<FeedbackItemRecord> firs);
	
	/**
	 * 提交评论
	 * @param feedbackRecord
	 * @return
	 */
	int insertFeedbackRecord(@Param("fr") FeedbackRecord feedbackRecord);
	
	
}
