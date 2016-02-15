package com.cd.bbh.parent.mine.webapi;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.common.utils.MD5Utils;
import com.cd.bbh.parent.login.service.LoginService;
import com.cd.bbh.parent.mine.model.Personal;
import com.cd.bbh.parent.mine.model.User;
import com.cd.bbh.parent.mine.model.UserService;

@RestController
@RequestMapping(value = "api/settings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineSettingsApi extends BaseApi {

	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;

	// /////////////////////////个人信息修改或者添加///////////////////////////
	@BBHPerm
	@RequestMapping(value = "personal")
	public Result personal(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(userService.findUserDetail(getCurrentUserID(params), getCurrentUserRole(params)));
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "personal/u")
	public Result personalUpdate(@RequestParam("params") String params, @RequestParam(name = "file", required = false) MultipartFile file) {
		Map<String, Object> resultMap = userService.findDetailCount(getCurrentUserID(params), getCurrentUserRole(params));

		Personal personal = new Personal();
		Date birthday = DateUtils.parseDateTime(getParamTextValue(params, "birthday"));
		personal.setHeadimg(resultMap.get("headimg") == null ? "" : resultMap.get("headimg").toString());
		personal.setExist((Long) resultMap.get("amount"));
		personal.setAge(DateUtils.getAge(birthday));
		personal.setUsername(getParamTextValue(params, "username"));
		personal.setUserid(getCurrentUserID(params));
		personal.setGender(getParamTextValue(params, "gender"));
		personal.setBirthday(birthday);
		personal.setRoleid(getCurrentUserRole(params));
		personal.setDevice(getImei(params));

		userService.updateUserDetail(personal, file);
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(personal);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "pass")
	public Result password(@RequestParam("params") String params) {
		User user = userService.find(getCurrentUserID(params));
		String newpass = MD5Utils.getMD5(getParamTextValue(params, "newpass"));
		if (!user.getPass().equals(MD5Utils.getMD5(getParamTextValue(params, "password")))) {
			return new Result(ResultEnum.PASSWORD_UPDATE_ORG_PASS_ERROR);
		}
		if (user.getPass().equals(newpass)) {
			return new Result(ResultEnum.PASSWORD_UPDATE_SAME_PASS_ERROR);
		}
		loginService.updatePassword(newpass, getCurrentUserID(params));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
