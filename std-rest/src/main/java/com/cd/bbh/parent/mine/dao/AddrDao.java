package com.cd.bbh.parent.mine.dao;

import java.util.List;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.Address;

public interface AddrDao extends BaseDao<Address> {

	List<Address> searchMyAddress(Long parentid);

	Address searchMyDefaultAddress(long parentid);
}