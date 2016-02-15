package com.cd.bbh.login;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.cd.bbh.common.HttpCaller;
import com.cd.bbh.common.Processor;

public class HomeSearchProcessor extends Processor {

	@Override
	public String execute() throws Exception {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("condition", MOBILE_PHONE);
		valueMap.put("cityname", "成都市");
		valueMap.put("searchType", "S");
		valueMap.put("pagesize", 20);
		valueMap.put("page", 1);
		return HttpCaller.post(url("home/search"), valueMap, new ArrayList<File>());
	}
}
