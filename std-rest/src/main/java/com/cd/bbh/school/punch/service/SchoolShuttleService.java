package com.cd.bbh.school.punch.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.school.dao.ShuttleDao;
import com.cd.bbh.school.punch.dao.SchoolSyncDao;

@Service
public class SchoolShuttleService {

	@Autowired
	private ShuttleDao shuttleDao;

	@Autowired
	private SchoolSyncDao schoolSyncDao;

	public boolean createShuttle(Map<String, Object> data) {
		Long id = shuttleDao.searchIsShuttle(data);
		if (id != null) {
			shuttleDao.updateShuttle(id);
			return true;
		} else {
			shuttleDao.insertShuttle(data);
			return false;
		}
	}

	public Long searchSchoolByPunch(String punchSerial) {
		return schoolSyncDao.selectSchoolByPunch(punchSerial);
	}
}
