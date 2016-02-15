package com.cd.bbh.parent.school.dao;

import java.util.List;
import java.util.Map;

import com.cd.bbh.parent.school.vo.CourseVO;

public interface CourseDao {

	List<CourseVO> searchCourses(Map<String, Object> params);
}
