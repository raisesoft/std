package com.cd.bbh.parent.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.sys.dao.AddressDao;
import com.cd.bbh.parent.sys.vo.AddressVO;

@Service
public class SysAddressService {

	@Autowired
	private AddressDao addressDao;

	public AddressVO findAddrByCity(String cityName) {
		return addressDao.selectAddrByCity(cityName);
	}
	public AddressVO findAddrByCounty(String cityName) {
		return addressDao.selectAddrByCounty(cityName);
	}
}
