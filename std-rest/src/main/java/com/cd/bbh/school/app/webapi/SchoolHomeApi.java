package com.cd.bbh.school.app.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.parent.mine.vo.ChildStarVO;
import com.cd.bbh.school.app.service.TeacherSchoolService;

@RestController
@RequestMapping(value = "api/school")
public class SchoolHomeApi extends BaseApi {

	@Autowired
	private TeacherSchoolService teacherSchoolService;

	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping(value = "home")
	public Result teacherViewStar(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<ChildStarVO> stars = null;
		if ("M".equals(getParamTextValue(params, "period", "W"))) {
			stars = teacherSchoolService.findClassMonthStar(getCurrentUserID(params), getPageable(params));
		} else {
			stars = teacherSchoolService.findClassWeekStar(getCurrentUserID(params), getPageable(params));
		}
		if (stars.size() > 0)
			result.setData(stars);
		return result;
	}
}
