package com.cd.bbh.parent.school.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.school.dao.MonitorDao;
import com.cd.bbh.parent.school.vo.MonitorVO;

@Service
public class MonitorService {

	@Autowired
	private MonitorDao monitorDao;

	public List<MonitorVO> findMonitors(Pageable pageable, Long child) {
		return monitorDao.searchCameraInfo(child, pageable.getPage(), pageable.getPagesize());
	}

	public Map<String, String> findMonitor(Long child, Long camera) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("child", child);
		params.put("camera", camera);
		return monitorDao.searchCamera(params);
	}

	public int createCameraPerm(Long pupilId, Date expireDate) {
		List<MonitorVO> monitorVOs = monitorDao.searchCameraInfo(pupilId, null, null);
		List<Map<String, Object>> insertValues = new ArrayList<Map<String, Object>>();
		for (MonitorVO monitorVO : monitorVOs) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("camera", monitorVO.getId());
			params.put("pupil", pupilId);
			params.put("expireDate", expireDate);
			insertValues.add(params);
		}
		return monitorDao.insertCameraRel(insertValues);
	}
}
