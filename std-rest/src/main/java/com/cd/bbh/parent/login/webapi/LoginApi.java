package com.cd.bbh.parent.login.webapi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Constant;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.utils.MD5Utils;
import com.cd.bbh.common.utils.SmsUtil;
import com.cd.bbh.parent.login.service.LoginService;
import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.cd.bbh.parent.sys.service.SysMessageService;

@RestController
@RequestMapping(value = "api/user", method = { RequestMethod.POST, RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
public class LoginApi extends BaseApi {

	private Logger logger = LoggerFactory.getLogger(LoginApi.class);
	@Autowired
	private LoginService loginService;

	@Autowired
	private SysMessageService sysMessageService;

	/**
	 * 验证码发送
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "smscode")
	public Result smscode(@RequestParam("params") String params) {
		String mobilephone = getParamTextValue(params, "mobilephone");
		Integer smstype = getParamIntValue(params, "smstype");
		
		if (loginService.findUserByUnique(mobilephone) == null) {
			if(smstype == 2) {
				return new Result(ResultEnum.USER_PHONE_NOT_EXISTS_ERROR);
			}
		} else {
			if(smstype == 1) {
				return new Result(ResultEnum.USER_REGISTOR_EXISTS_ERROR);
			}
		}
		
		String randomcodeTemp = SmsUtil.createRandomVcode();
		String randomcode = String.format(constant.smscodeSendTips, randomcodeTemp, randomcodeTemp.length(), constant.smscodeExpireTime);
		redis.saveSmscode(mobilephone, randomcodeTemp, smstype);
		if (constant.smscodeSendNeeded) {
			SmsUtil.sendSMS(mobilephone, randomcode);
			return new Result(ResultEnum.SMSCODE_SEND_SUCCESS);
		} else {
			return new Result(ResultEnum.SMSCODE_SEND_SUCCESS, randomcode);
		}
	}

	/**
	 * 用户注册(只能注册普通家长)
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "register")
	public Result registuser(@RequestParam("params") String params) {
		String cellPhone = getParamTextValue(params, "mobilephone");
		String password = getParamTextValue(params, "password");

		if (StringUtils.isBlank(cellPhone) || StringUtils.isBlank(password)) {
			return new Result(ResultEnum.USER_LOGIN_PARAM_ERROR);
		}

		boolean staus = redis.removeSmscode(cellPhone, getParamTextValue(params, "smscode"), getParamIntValue(params, "smstype"));
		if (!staus) {
			return new Result(ResultEnum.SMSCODE_EXPIRE_ERROR);
		}

		if (!loginService.checkUserExists(cellPhone)) {
			int record = loginService.createUser(cellPhone, MD5Utils.getMD5(password), getImei(params));
			if (record != 0) {
				return new Result(ResultEnum.USER_REGISTOR_SUCCESS);
			} else {
				return new Result(ResultEnum.UNKNOW_ERROR);
			}
		} else {
			return new Result(ResultEnum.USER_REGISTOR_EXISTS_ERROR);
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "login")
	public Result login(@RequestParam("params") String params) {
		LoginInfoVO loginValue = loginService.findUserByUnique(getParamTextValue(params, "mobilephone"));
		if (loginValue == null) {
			return new Result(ResultEnum.USER_LOGIN_ERROR);
		}
		logger.debug("origianal password:" + loginValue.getPassword());
		logger.debug("input password:" + MD5Utils.getMD5(getParamTextValue(params, "password")));
		if (loginValue.getPassword().equals(MD5Utils.getMD5(getParamTextValue(params, "password")))) {
			loginValue.setRoles(Constant.ROLE_PARENT);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("access_token", redis.generateAccessToken(loginValue, getImei(params)));
			data.put("usertype", loginValue.getRoles());
			data.put("parentid", loginValue.getUserid());
			updateParentDeviceNo(loginValue, params);// 此处为添加imei使用
			return new Result(ResultEnum.USER_LOGIN_SUCCESS, data);
		} else {
			return new Result(ResultEnum.USER_LOGIN_ERROR, "");
		}
	}

	/**
	 * 用户修改密码
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "editpass")
	public Result editpass(@RequestParam("params") String params) {
		String newpass = getParamTextValue(params, "newpass");
		String cellPhone = getParamTextValue(params, "mobilephone");

		if (StringUtils.isBlank(cellPhone) || StringUtils.isBlank(newpass)) {
			return new Result(ResultEnum.USER_LOGIN_PARAM_ERROR);
		}
		boolean staus = redis.removeSmscode(cellPhone, getParamTextValue(params, "smscode"), getParamIntValue(params, "smstype"));
		if (!staus) {
			return new Result(ResultEnum.SMSCODE_EXPIRE_ERROR);
		}

		LoginInfoVO user = loginService.findUserByUnique(cellPhone);
		if (user == null) {
			return new Result(ResultEnum.USER_NOT_EXISTS_ERROR);
		}
		int record = loginService.updatePassword(MD5Utils.getMD5(newpass), user.getUserid());
		if (record != 0) {
			return new Result(ResultEnum.PASSWORD_UPDATE_SUCCESS);
		} else {
			return new Result(ResultEnum.OPERATION_ERROR);
		}
	}

	/**
	 * 某些用户由于是学校添加的，所以数据库中不存在有IMEI号。在第一次登录时更新IMEI
	 * 
	 * @param loginInfo
	 */
	private void updateParentDeviceNo(LoginInfoVO loginInfo, String params) {
		if (StringUtils.isBlank(loginInfo.getImei())) {
			loginService.updateParentDeviceNo(loginInfo.getUserid(), getImei(params));
		}
	}
}
