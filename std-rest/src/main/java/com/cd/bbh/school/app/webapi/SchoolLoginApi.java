package com.cd.bbh.school.app.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.utils.SmsUtil;
import com.cd.bbh.parent.login.service.LoginService;
import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.cd.bbh.school.app.service.SchoolLoginService;

@RestController
@RequestMapping(value = "api/school", method = { RequestMethod.POST, RequestMethod.PUT }, produces = MediaType.APPLICATION_JSON_VALUE)
public class SchoolLoginApi extends BaseApi{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SchoolLoginService sls;

	/**
	 * 验证码发送(忘记密码)
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "smscode")
	public Result smscode(@RequestParam("params") String params){
		String mobilephone = getParamTextValue(params, "mobilephone");		
		if (sls.selectUserCount(mobilephone) > 0) {
			return new Result(ResultEnum.USER_PHONE_NOT_EXISTS_ERROR);
		}
		
		String randomcodeTemp = SmsUtil.createRandomVcode();
		String randomcode = String.format(constant.smscodeSendTips, randomcodeTemp, randomcodeTemp.length(), constant.smscodeExpireTime);
		redis.saveSmscode(mobilephone, randomcodeTemp, 2);
		if (constant.smscodeSendNeeded) {
			SmsUtil.sendSMS(mobilephone, randomcode);
			return new Result(ResultEnum.SMSCODE_SEND_SUCCESS);
		} else {
			return new Result(ResultEnum.SMSCODE_SEND_SUCCESS, randomcode);
		}
	}
	
	/**
	 * 登录
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "login")
	public Result login(@RequestParam("params") String params) {
		String cellPhone = getParamTextValue(params, "mobilephone");
		String password = getParamTextValue(params, "password");
		if((cellPhone == null || cellPhone.length() == 0) || (password == null || password.length() == 0)){
			return new Result(ResultEnum.USER_LOGIN_PARAM_ERROR);
		}
		LoginInfoVO u = sls.doLogin(cellPhone, password);
		if(u == null) {
			return new Result(ResultEnum.USER_LOGIN_ERROR);
		} else if(!u.getState().equals("A")) {
			return new Result(ResultEnum.USER_LOGIN_ROLE_ERROR);
		} else {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("access_token", redis.generateAccessToken(u, getImei(params)));
			data.put("usertype", u.getRoles());
			data.put("userId", u.getUserid());
			updateParentDeviceNo(u, params);// 此处为添加imei使用
			return new Result(ResultEnum.USER_LOGIN_SUCCESS, data);
		}
	}
	
	/**
	 * 修改密码
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "editpass")
	public Result editpass(@RequestParam("params") String params) {
		ResultEnum info;
		String newpass = getParamTextValue(params, "newpass");
		String cellPhone = getParamTextValue(params, "mobilephone");
		if (StringUtils.isBlank(cellPhone) || StringUtils.isBlank(newpass)) {
			info = ResultEnum.USER_LOGIN_PARAM_ERROR;
		}
		boolean staus = redis.removeSmscode(cellPhone, getParamTextValue(params, "smscode"), 2);
		if (!staus) {
			info = ResultEnum.SMSCODE_EXPIRE_ERROR;
		}
		if(sls.updatePassword(cellPhone, newpass)) {
			info = ResultEnum.PASSWORD_UPDATE_SUCCESS;
		} else {
			info = ResultEnum.OPERATION_ERROR;
		}
		
		return new Result(info);
	}
	
	@RequestMapping(value = "info")
	public Result userInfo(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		long id = getCurrentUserID(params);
		RoleEnum roles = getCurrentUserRoleEnum(params);
		result.setData(sls.selectUserInfo(id, roles));
		return result;
	}
	
	@RequestMapping(value = "updateinfo")
	public Result updateUserInfo(@RequestParam("params") String params) {
		ResultEnum info;
		RoleEnum roles = getCurrentUserRoleEnum(params);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("id", getCurrentUserID(params));
		data.put("name", getParamTextValue(params, "name", null));
		data.put("qq", getParamTextValue(params, "qq", null));
		data.put("webchat", getParamTextValue(params, "webchat", null));
		data.put("imghead", getParamTextValue(params, "imghead", null));
		data.put("updateUserId", getCurrentUserID(params));
		data.put("updateDate", new Date());
		data.put("remark", getParamTextValue(params, "remark", null));
		//=======================教师独有数据===============
		data.put("age", getParamIntValue(params, "age", 0));
		data.put("sex", getParamTextValue(params, "sex", null));
		data.put("birthday", getParamDateValue(params, "birthday", null));
		//======================学校独有数据======================
		data.put("imgLogoKey", getParamDateValue(params, "imgLogoKey", null));
		data.put("countryId", getParamIntValue(params, "countryId", 0));
		data.put("provinceId",getParamIntValue(params, "provinceId", 0));
		data.put("cityId", getParamIntValue(params, "cityId", 0));
		data.put("countyId", getParamIntValue(params, "countyId", 0));
		data.put("address", getParamDateValue(params, "address", null));
		data.put("imgLogoUrl", getParamDateValue(params, "imgLogoUrl", null));
		data.put("imgPopUrl", getParamDateValue(params, "imgPopUrl", null));
		if(sls.updateUserInfo(data, roles)) {
			info = ResultEnum.SUCCESS;
		} else {
			info = ResultEnum.OPERATION_ERROR;
		}
		
		return new Result(info);
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
