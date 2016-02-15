package com.cd.bbh.school.punch.webapi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
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
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.sys.service.SysMessageService;
import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;
import com.cd.bbh.school.punch.service.SchoolShuttleService;

@RestController
@RequestMapping(value = "api/school")
public class SchoolShuttleApi extends BaseApi {

	@Autowired
	private ChildrenService childrenService;

	@Autowired
	private SysMessageService sysMessageService;

	@Autowired
	private SchoolShuttleService schoolShuttleService;

	@RequestMapping("shuttle/create")
	public Result inputShuttle(@RequestParam("params") String params) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("cardNo", getParamTextValue(params, "cardNo"));
		data.put("punchSerial", getParamTextValue(params, "punchSerial"));
		data.put("childid", getParamLongValue(params, "childid"));
		data.put("headImg", getParamTextValue(params, "heardImgUrl", null));
		data.put("remark", getParamTextValue(params, "remark", null));
		data.put("headImgKey", getParamTextValue(params, "headImgKey", null));

		boolean value = schoolShuttleService.createShuttle(data);
		saveNotification(params, value);
		return new Result(ResultEnum.SUCCESS);
	}

	private void saveNotification(String params, boolean flag) {
		List<MessageUserVO> users = sysMessageService.searchMessageUserByChild(getParamLongValue(params, "childid"));
		if (users == null || users.size() == 0) {
			return;
		}

		List<String> regids = new ArrayList<String>();
		for (MessageUserVO messageUserVO : users) {
			regids.add(messageUserVO.getRegid());
		}
		String childName = childrenService.findChild(getParamLongValue(params, "childid")).getName();
		Calendar calendar = Calendar.getInstance();
		String title = String.format(constant.shuttle, childName, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), flag ? "离" : "入");

		NotificationVO notification = new NotificationVO(title, //
				null, "Y", "SHUTTLE", JPushEnum.PERSON.siginal(),//
				regids.toString().substring(1, regids.toString().length() - 1), //
				schoolShuttleService.searchSchoolByPunch(getParamTextValue(params, "punchSerial")));

		sysMessageService.createNotification(notification);
	}
}
