package com.cd.bbh.parent.school.dao;

import java.util.Map;

public interface CommentDao {

	int insertComment(Map<String, Object> params);

	Long searchSchool(Long pupilid);
}
