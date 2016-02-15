package com.cd.bbh.parent.school.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.school.dao.CommentDao;

@Service
public class CommentService {

	@Autowired
	private CommentDao commentDao;

	public int createComments(String content, Long childid, Long creator) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("leaderid", commentDao.searchSchool(childid));
		params.put("content", content);
		params.put("spokesman", creator);
		return commentDao.insertComment(params);
	}
}
