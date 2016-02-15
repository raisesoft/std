package com.cd.bbh.parent.more.webapi;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.utils.MailUtil;
import com.cd.bbh.parent.more.service.AppBaseService;
import com.cd.bbh.parent.sys.service.SysMessageService;

@RestController
@RequestMapping(value = "api/app")
public class AppBaseApi extends BaseApi {

	@Autowired
	private AppBaseService appVersionService;
	@Autowired
	private SysMessageService sysMessageService;

	@RequestMapping(value = "version")
	public Result version(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);

		List<Map<String, String>> data = appVersionService.findLatestVersion(//
				getParamTextValue(params, "ostype"), //
				getClientType(params));
		result.setData(data);
		return result;
	}

	@RequestMapping(value = "advice")
	public Result advice(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		MailUtil.sendEmail(getParamTextValue(params, "advice"), "device");
		return result;
	}

	@RequestMapping(value = "push")
	public Result appkey(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		boolean isLogin = StringUtils.isNotBlank(getAccessToken(params));
		sysMessageService.updateMessageUser(//
				getParamTextValue(params, "regid"), //
				getParamTextValue(params, "cityname"), //
				getImei(params), //
				isLogin ? getCurrentUserInfo(params, "account") : null, //
				isLogin ? getCurrentUserInfo(params, "auth") : "G", //
				isLogin ? getCurrentUserID(params) : null);
		return result;
	}
}
