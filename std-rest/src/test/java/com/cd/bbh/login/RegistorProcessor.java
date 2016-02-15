package com.cd.bbh.login;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cd.bbh.common.HttpCaller;
import com.cd.bbh.common.Processor;

public class RegistorProcessor extends Processor {
	private final int smstype = 1;

	@Override
	public String execute() throws Exception {
		String smscode = MAPPER.readTree(callSmscode(smstype)).get("data").textValue();
		return smscode;
//		return callRegister(smscode);
	}

//	private String callRegister(String smscode) {
//		Map<String, Object> valueMap = new HashMap<String, Object>();
//		valueMap.put("mobilephone", MOBILE_PHONE);
//		valueMap.put("password", "wapwap12");
//		valueMap.put("smscode", smscode);
//		valueMap.put("smstype", smstype);
//		return HttpCaller.post(url("user/register"), valueMap, new ArrayList<File>());
//	}
}
