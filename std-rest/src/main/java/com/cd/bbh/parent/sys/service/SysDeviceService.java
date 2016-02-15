package com.cd.bbh.parent.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.sys.dao.DeviceDao;
import com.cd.bbh.parent.sys.vo.DeviceVO;

@Service
public class SysDeviceService {

	@Autowired
	private DeviceDao deviceDao;

	public List<DeviceVO> findDevices(Pageable pageable) {
		return deviceDao.selectDevices(pageable.getPage(), pageable.getPagesize());
	}
}
