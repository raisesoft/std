package com.cd.bbh.parent.school.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.parent.school.dao.CourseDao;
import com.cd.bbh.parent.school.vo.CourseVO;

@Service
public class CourseService {

	@Autowired
	private CourseDao courseDao;

	public List<CourseVO> findCourse(Date searchDate, Long childId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("beginDate", DateUtils.getMonday(searchDate));
		params.put("endDate", DateUtils.getSunday(searchDate));
		params.put("child", childId);
		List<CourseVO> temp = courseDao.searchCourses(params);
		List<CourseVO> courseVOs = buildCourseVO();
		for (CourseVO result : temp) {
			CourseVO courseVO = courseVOs.get(courseVOs.indexOf(result));
			courseVO.setCoureses(result.getCoureses());
			courseVO.setDate(result.getDate());
			result.setCoureses(null);// 释放引用
		}
		return courseVOs;
	}

	private List<CourseVO> buildCourseVO() {
//		String[] week = { "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日" };
		String[] week = { "星期一", "星期二", "星期三", "星期四", "星期五" };
		List<CourseVO> coursevos = new ArrayList<CourseVO>();
		for (int i = 0; i < week.length; i++) {
			CourseVO courseVO = new CourseVO();
			courseVO.setDayOfWeek(week[i]);
			coursevos.add(courseVO);
		}
		return coursevos;
	}
}
