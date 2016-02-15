package com.cd.bbh.school.app.webapi;

import java.util.Date;
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
import com.cd.bbh.parent.school.vo.RecipesVO;
import com.cd.bbh.school.app.service.TeacherSchoolService;

/**
 * 食谱
 * 
 * @author aowin
 *
 */
@RestController
@RequestMapping(value = "api/school")
public class SchoolRecipeApi extends BaseApi {

	@Autowired
	private TeacherSchoolService teacherSchoolService;

	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping(value = "recipe")
	public Result monitor(@RequestParam("params") String params) {
		List<RecipesVO> recipes = teacherSchoolService.findRecipes(//
				getParamDateValue(params, "searchDate", new Date()), //
				getCurrentUserID(params));

		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(recipes);
		return result;
	}
}
