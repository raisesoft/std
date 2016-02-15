package com.cd.bbh.school.app.webapi;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.school.app.service.TeacherSchoolService;

/**
 * 食谱
 * 
 * @author aowin
 *
 */
@RestController
@RequestMapping(value = "api/school")
public class SchoolCourseApi extends BaseApi {

	@Autowired
	private TeacherSchoolService teacherSchoolService;

	/**
	 * 课程安排
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping(value = "syllabus")
	public Result course(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		// 获取查询时间
		Date syncDate = getParamDateValue(params, "syncDate", DateUtils.getCurrDate());
		result.setData(teacherSchoolService.findSyllabus(syncDate, getCurrentUserID(params)));

		return result;
	}
}
