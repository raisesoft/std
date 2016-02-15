package com.cd.bbh.parent.school.webapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.JPushEnum;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.mine.vo.ChildStarVO;
import com.cd.bbh.parent.school.service.AbsenceService;
import com.cd.bbh.parent.school.service.CommentService;
import com.cd.bbh.parent.school.service.CourseService;
import com.cd.bbh.parent.school.service.FeedbackService;
import com.cd.bbh.parent.school.service.MonitorService;
import com.cd.bbh.parent.school.service.ParentSchoolService;
import com.cd.bbh.parent.school.service.RecipeService;
import com.cd.bbh.parent.school.service.ShuttleService;
import com.cd.bbh.parent.school.vo.AbsenceType;
import com.cd.bbh.parent.school.vo.AbsenceVO;
import com.cd.bbh.parent.school.vo.FeedbackVO;
import com.cd.bbh.parent.school.vo.MonitorVO;
import com.cd.bbh.parent.school.vo.Shuttle;
import com.cd.bbh.parent.sys.service.SysMessageService;
import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;

@RestController
@RequestMapping(value = "api/school/parent")
public class SchoolParentApi extends BaseApi {
	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private ShuttleService shuttleService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private RecipeService recipeService;

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private ParentSchoolService parentSchoolService;

	@Autowired
	private AbsenceService absenceService;

	@Autowired
	private SysMessageService sysMessageService;

	@Autowired
	private ChildrenService childrenSerice;

	/**
	 * 查询最早添加的小朋友，显示在校园首页
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping(value = "home")
	public Result parentViewStar(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<ChildStarVO> stars = null;
		if ("M".equals(getParamTextValue(params, "period", "W"))) {
			stars = parentSchoolService.findClassMonthStar(getParamLongValue(params, "childid"), getPageable(params));
		} else {
			stars = parentSchoolService.findClassWeekStar(getParamLongValue(params, "childid"), getPageable(params));
		}
		if (stars.size() > 0)
			result.setData(stars);
		return result;
	}

	/**
	 * 课程安排
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("course")
	public Result courses(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(courseService.findCourse(//
				getParamDateValue(params, "searchDate", new Date()),//
				getParamLongValue(params, "childid")));
		return result;
	}

	/**
	 * 校园食谱
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("recipes")
	public Result recipes(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(recipeService.findRecipes(getParamDateValue(params, "searchDate", new Date()), getParamLongValue(params, "childid")));
		return result;
	}

	/**
	 * 园丁反馈情况
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("feedback")
	public Result feedback(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<FeedbackVO> feedbacks = feedbackService.findFeedbacks(getPageable(params), //
				getParamDateValue(params, "searchDate", new Date()), //
				getParamLongValue(params, "pupilid"));
		if (feedbacks.size() > 0) {
			result.setData(feedbacks);
		}
		return result;
	}

	/**
	 * 家长评价反馈
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("feedback/response")
	public Result feedbackres(@RequestParam("params") String params) {
		feedbackService.updateFeedback(getParamTextValue(params, "comments"), getParamLongValue(params, "feedbackid"));
		return new Result(ResultEnum.SUCCESS);
	}

	/**
	 * 接送记录（获取绑定小孩子的人）
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("shuttle/elder")
	public Result elders(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(shuttleService.findElders(getParamLongValue(params, "child")));
		return result;
	}

	/**
	 * 接送记录
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("shuttle")
	public Result shuttle(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<Shuttle> shuttles = shuttleService.findShuttles(//
				getParamDateValue(params, "searchDate", new Date()), //
				getParamLongValue(params, "child"), getParamIntValue(params, "page", 1), getParamIntValue(params, "pageSize", 0));
		result.setData(shuttles);

		return result;
	}

	/**
	 * 摄像头列表
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("monitorinfo")
	public Result monitors(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<MonitorVO> monitors = monitorService.findMonitors(getPageable(params), getParamLongValue(params, "child"));
		if (monitors.size() > 0) {
			result.setData(monitors);
		}
		return result;
	}

	/**
	 * 监控
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("monitor")
	public Result monitor(@RequestParam("params") String params) {
		Map<String, String> valueMap = monitorService.findMonitor(getParamLongValue(params, "child"), getParamLongValue(params, "camera"));
		if (valueMap == null || valueMap.size() <= 0 || valueMap.get("serialNo") == null) {
			return new Result(ResultEnum.MONITOR_PERMISSION_ERROR);
		}
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(valueMap);
		return result;
	}

	/**
	 * 监控权购买
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("monitor/applay")
	public Result monitorRequest(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		monitorService.createCameraPerm(getParamLongValue(params, "child"), getParamDateValue(params, "expireDate"));
		return result;
	}

	/**
	 * 校长信箱
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("master/comment")
	public Result kdLeaderMailbox(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		commentService.createComments(getParamTextValue(params, "content"), getParamLongValue(params, "childid"), getCurrentUserID(params));
		return result;
	}

	/**
	 * 请假
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("absence/apply")
	public Result absenceApplay(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		absenceService.createAbsence(getParamDateValue(params, "leaveTime"),//
				getParamDateValue(params, "backTime"), //
				getCurrentUsername(params),//
				getParamTextValue(params, "remark"), //
				getParamLongValue(params, "childid"),//
				getParamLongValue(params, "teacherid"),//
				AbsenceType.T_LEAVE);

		saveAdvice(params);
		return result;
	}

	/**
	 * 请假相关老师
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("absence/teacher")
	public Result absencRelTeacher(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(absenceService.searchTeachers(getParamLongValue(params, "childid")));
		return result;
	}

	/**
	 * 请假列表查看
	 * 
	 * @param params
	 * @return
	 */
	@BBHPerm(RoleEnum.PAUTH)
	@RequestMapping("absence")
	public Result absencesView(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<AbsenceVO> absences = absenceService.searchAbsences(getParamLongValue(params, "childid"), //
				getParamDateValue(params, "absenceDate", null));
		result.setData(absences);
		return result;
	}

	private void saveAdvice(String params) {
		List<MessageUserVO> messageUserVOs = sysMessageService.searchMessageUser(getParamLongValue(params, "teacherid"));
		if (messageUserVOs == null || messageUserVOs.size() <= 0) {
			return;
		}

		List<String> regids = new ArrayList<String>();
		for (MessageUserVO messageUserVO : messageUserVOs) {
			regids.add(messageUserVO.getRegid());
		}
		Child child = childrenSerice.findChild(getParamLongValue(params, "childid"));
		String title = String.format(constant.absence, child.getName());
		NotificationVO notification = new NotificationVO(title, null, "Y", "ABSENCE", JPushEnum.PERSON.siginal(), //
				regids.toString().substring(1, regids.toString().length() - 1), //
				getCurrentUserID(params));

		sysMessageService.createNotification(notification);
	}
}
