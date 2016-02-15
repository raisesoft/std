package com.cd.bbh.parent.sys.dao;

import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.sys.vo.AddressVO;

@Repository
public interface AddressDao {
	AddressVO selectAddrByCity(String cityName);

	AddressVO selectAddrByCounty(String countyName);
}
