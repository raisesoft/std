package com.cd.bbh.login;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cd.bbh.common.HttpCaller;
import com.cd.bbh.common.Processor;

public class LoginProcessor extends Processor {

	@Override
	public String execute() throws Exception {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("mobilephone", MOBILE_PHONE);
		valueMap.put("password", "aaa");
		return HttpCaller.post(url("user/login"), valueMap, new ArrayList<File>());
	}
}
