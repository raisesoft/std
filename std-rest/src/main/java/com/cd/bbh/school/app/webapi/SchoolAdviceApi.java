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
import com.cd.bbh.school.app.service.SchoolMessageService;
import com.cd.bbh.school.app.service.TeacherSchoolService;
import com.cd.bbh.school.app.vo.Message;

/**
 * Teacher or kindergarten send advice to parent
 * 
 * @author alex
 */
@RestController
@RequestMapping(value = "api/school")
public class SchoolAdviceApi extends BaseApi {

	@Autowired
	private SchoolMessageService sms;
	@Autowired
	private TeacherSchoolService tss;

	/**
	 * 教师提交通知
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "advice", method = { RequestMethod.POST, RequestMethod.PUT })
	public Result teacherMessageSend(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		System.out.println(getCurrentUserID(params));
		// 数据获取与填充
		Message msg = new Message();
		msg.setTitle(getParamTextValue(params, "title"));
		msg.setContent(getParamTextValue(params, "content"));
		msg.setLevel(getParamTextValue(params, "level", "B"));
		msg.setStat("W");//等待发送状态
		msg.setCreateDate(new Date());
		msg.setCreateUser(getCurrentUserID(params));
		RoleEnum roles = getCurrentUserRoleEnum(params);
		// 教师入口
		if (roles.equals(RoleEnum.TEACHER)) {
			msg.setPushTags(getParamTextValue(params, "classId"));
			msg.setType("CLASS");
			// 添加数据
			sms.sendMsg(msg, "T", "C");
		} else if (roles.equals(RoleEnum.SCHOOL)) { // 学校入口
			String sendType = getParamTextValue(params, "sendType", "C");
			if (sendType.equals("S")) { // 按学校发送
				msg.setType("SCHOOL");
			} else if (sendType.equals("C")) { // 按班级发送
				msg.setType("CLASS");
				msg.setPushTags(getParamTextValue(params, "classId"));
			}
			sms.sendMsg(msg, "S", sendType);
		}
		return result;
	}

	/**
	 * 教师获取班级列表及通知等级
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "classandlevel")
	public Result getClassListByTeacher(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		Map<String, Object> data = new HashMap<String, Object>();
		RoleEnum roles = getCurrentUserRoleEnum(params);
		if (roles.equals(RoleEnum.SCHOOL)) { // 校长入口
			data.put("class", tss.findClassListByPrincipal(getCurrentUserID(params)));
		} else if (roles.equals(RoleEnum.TEACHER)) { // 教师入口
			data.put("class", tss.findClassListByTeacher(getCurrentUserID(params)));
		}
		data.put("level", sms.getMessageLevelList());
		result.setData(data);

		return result;
	}
}
