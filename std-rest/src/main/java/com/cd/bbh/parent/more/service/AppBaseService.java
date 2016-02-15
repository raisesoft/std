package com.cd.bbh.parent.more.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.more.dao.AppBaseDao;

@Service
public class AppBaseService {

	@Autowired
	private AppBaseDao appBaseDao;

	public List<Map<String, String>> findLatestVersion(String ostype, String clientType) {
		return findAppVersionInfo(ostype, null, clientType);
	}

	public List<Map<String, String>> findAppVersionInfo(String ostype, String version, String clientType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("ostype", ostype);
		params.put("clientType", clientType);
		params.put("version", version);
		return appBaseDao.searchAppVersion(params);
	}
}
