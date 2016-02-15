package com.cd.bbh.school.app.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.school.app.service.SchoolFeedbackService;
import com.cd.bbh.school.app.vo.FeedbackRecord;

@RestController
@RequestMapping(value = "api/school")
public class SchoolFeedbackApi extends BaseApi {
	
	@Autowired
	private SchoolFeedbackService sfs;
	
	/**
	 * 获取模板列表
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping("feedbacktemplate/list")
	public Result feedbackTemplateList(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(sfs.findFeedbackTemplates(getCurrentUserID(params)));
		return result;
	}
	
	/**
	 * 获取指定模板
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping("feedbacktemplate")
	public Result feedbackTemplate(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(sfs.findFeedbackTemplate(getParamLongValue(params, "templateId")));
		return result;
	}

	/**
	 * 
	 * 老师给学生打分和评论
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping(value = "feedback", method = { RequestMethod.POST, RequestMethod.PUT })
	public Result feedback(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		FeedbackRecord fr = new FeedbackRecord();
		fr.setCreateDate(new Date());
		fr.setTeacherComments(getParamTextValue(params, "comments"));
		fr.setPupilId(getParamLongValue(params, "studentId"));
		fr.setTemplateId(getParamLongValue(params, "templateId"));
		fr.setCreateUserId(getCurrentUserID(params));

		sfs.createFeedbackItemRecord(getParamTextValue(params, "scores"), fr);
		
		return result;
	}

	/**
	 * 
	 * 老师查看反馈列表
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.TEACHER)
	@RequestMapping(value = "feedback/list")
	public Result feedbacklist(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		long teacherId = getCurrentUserID(params);		
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("title", getParamTextValue(params, "search", null));
		search.put("dataNum", getParamLongValue(params, "pageSize", 0L));
		search.put("page", getParamLongValue(params, "page", 1L));
		search.put("student", getParamTextValue(params, "student", null));

		result.setData(sfs.findFeedbackList(teacherId, search));
		
		return result;
	}
}
