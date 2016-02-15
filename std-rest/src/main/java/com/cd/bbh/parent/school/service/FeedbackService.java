package com.cd.bbh.parent.school.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.school.dao.FeedbackDao;
import com.cd.bbh.parent.school.vo.FeedbackVO;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackDao feebackDao;

	public List<FeedbackVO> findFeedbacks(Pageable pageable, Date endDate, Long pupilId) {
		return feebackDao.searchFeedbacks(pupilId, endDate, pageable.getPage(), pageable.getPagesize());
	}

	public FeedbackVO findFeedback(Long feedbackid) {
		return feebackDao.searchFeedback(feedbackid);
	}

	public int updateFeedback(String comments, Long feedbackid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("comments", comments);
		params.put("feedbackid", feedbackid);
		return feebackDao.updateFeedback(params);
	}
}
