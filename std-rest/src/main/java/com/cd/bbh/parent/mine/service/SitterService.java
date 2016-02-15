package com.cd.bbh.parent.mine.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.mine.dao.ChildSitterDao;
import com.cd.bbh.parent.mine.model.ChildSitter;
import com.cd.bbh.parent.mine.model.Device;

@Service
public class SitterService {

	@Autowired
	private ChildSitterDao childSitterDao;

	public List<ChildSitter> findSitters(Long parentid) {
		return childSitterDao.searchSitters(parentid);
	}

	public List<Device> findDevices() {
		return childSitterDao.searchAllDevice();
	}

	public Device findDevice(Long deviceid) {
		return childSitterDao.searchDevice(deviceid);
	}

	public int createChildDevice(long childid, long deviceid, long parentid, String daccount, String dsn, String dpassword) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("childid", childid);
		params.put("deviceid", deviceid);
		params.put("createdby", parentid);
		params.put("daccount", daccount);
		params.put("dsn", dsn);
		params.put("dpassword", dpassword);
		return childSitterDao.addChildDevice(params);
	}
	public int updateChildDevice(long childid, long deviceid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("childid", childid);
		params.put("deviceid", deviceid);
		return childSitterDao.bindChildDevice(params);
	}
}
