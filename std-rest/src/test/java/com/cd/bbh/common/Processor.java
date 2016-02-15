package com.cd.bbh.common;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class Processor {

	protected static String ACCESS_TOKEN = "d4379a4b0fbb414a942cdf46dfce2c43";
	protected static final String MOBILE_PHONE = "13689033803";
	protected static final ObjectMapper MAPPER = new ObjectMapper();

	protected static String url(String path) {
		String basePath = "http://192.168.2.254:8111/bbh/api/%s";
		return String.format(basePath, path);
	}

	protected String callSmscode(int smstype) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("mobilephone", MOBILE_PHONE);
		valueMap.put("smstype", smstype);
		return HttpCaller.post(url("user/smscode"), valueMap, new ArrayList<File>());
	}

	public abstract String execute() throws Exception;
}
