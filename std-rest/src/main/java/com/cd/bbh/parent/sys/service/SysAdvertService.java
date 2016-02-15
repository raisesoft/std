package com.cd.bbh.parent.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.sys.dao.AdvertDao;
import com.cd.bbh.parent.sys.vo.AdvertVO;

@Service
public class SysAdvertService {

	@Autowired
	private AdvertDao advertDao;

	public List<AdvertVO> findAdverts(Pageable pageable, String cityname) {
		return advertDao.selectAdverts(cityname, pageable.getPage(), pageable.getPagesize());
	}

	public List<AdvertVO> findAdvertsByName(Pageable pageable, String name, String cityname) {
		return advertDao.selectAdvertsByName(cityname, name, pageable.getPage(), pageable.getPagesize());
	}
}
