package com.cd.bbh.login;

import java.util.HashMap;
import java.util.Map;

import com.cd.bbh.common.HttpCaller;
import com.cd.bbh.common.Processor;

public class SchoolParentProcessor extends Processor {

	@Override
	public String execute() throws Exception {
		System.out.println(recipes());
		System.out.println(coureses());
		return "";
	}

	private String recipes() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("childid", 1);
		valueMap.put("searchDate", "2015-12-03 12:12:21");
		return HttpCaller.post(url("school/parent/recipes"), valueMap, ACCESS_TOKEN);
	}
	
	private String coureses() {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		valueMap.put("childid", 1);
		valueMap.put("searchDate", "2015-12-03 12:12:21");
		return HttpCaller.post(url("school/parent/course"), valueMap, ACCESS_TOKEN);
	}
}
