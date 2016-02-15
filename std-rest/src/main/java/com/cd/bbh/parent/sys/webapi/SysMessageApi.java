package com.cd.bbh.parent.sys.webapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.parent.sys.service.SysMessageService;

@RestController
@RequestMapping(value = "api/sys", produces = MediaType.APPLICATION_JSON_VALUE)
public class SysMessageApi extends BaseApi {

	@Autowired
	private SysMessageService sysMessageService;
	
	@RequestMapping(value = "parent/message")
	public Result getMessage(@RequestParam("params") String params) {
		Long userId = null;
		try {
			userId = getCurrentUserID(params);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("userId", userId);
		data.put("status", getParamTextValue(params, "status", null));
		data.put("page", getParamIntValue(params, "page", 1));
		data.put("pageSize", getParamIntValue(params, "pageSize", 0));
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(sysMessageService.getMessage(data));
		return result;
	}
	
	@RequestMapping(value = "parent/message/u")
	public Result updateMessage(@RequestParam("params") String params) {
		//登录检测
		getCurrentUserID(params);
		sysMessageService.updateMessageStatus(
				getParamLongValue(params, "id"),
				getParamTextValue(params, "status", "Y"));
		
		return new Result(ResultEnum.SUCCESS);
	}
}
