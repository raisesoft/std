package com.cd.bbh.parent.school.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.FeedbackVO;

@Repository
public interface FeedbackDao {
	public List<FeedbackVO> searchFeedbacks(@Param("pupilid") Long pupilId, @Param("endDate") Date endDate, @Param("page") int page, @Param("pagesize") int pagesize);

	public FeedbackVO searchFeedback(Long feedbackid);

	public int updateFeedback(Map<String, Object> params);
}
