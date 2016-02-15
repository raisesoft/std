package com.cd.bbh.parent.school.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.school.dao.ShuttleDao;
import com.cd.bbh.parent.school.vo.Shuttle;

@Service
public class ShuttleService {

	@Autowired
	private ShuttleDao shuttleDao;

	public List<Shuttle> findShuttles(Date time, Long child, int page, int num) {
		int start = 0;
		if(num > 0) {
			start = num * (page - 1); 
		}
		return shuttleDao.searchShuttles(time, child, start, num);
	}

	public List<Map<String, Object>> findElders(Long childId) {
		return shuttleDao.searchElders(childId);
	}
}
