package com.cd.bbh.login;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cd.bbh.common.HttpCaller;
import com.cd.bbh.common.Processor;

public class PasswordUpdateProcessor extends Processor {

	private final int smstype = 2;

	@Override
	public String execute() throws Exception {
		String smscode = MAPPER.readTree(callSmscode(smstype)).get("data").textValue();
		return updatePassword(smscode);
	}

	private String updatePassword(String smscode) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("mobilephone", MOBILE_PHONE);
		valueMap.put("newpass", "aaa");
		valueMap.put("smscode", smscode);
		valueMap.put("smstype", smstype);
		return HttpCaller.post(url("user/editpass"), valueMap, new ArrayList<File>());
	}
}
