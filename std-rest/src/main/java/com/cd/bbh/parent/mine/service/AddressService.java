package com.cd.bbh.parent.mine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.mine.dao.AddrDao;
import com.cd.bbh.parent.mine.model.Address;

@Service
public class AddressService {
	@Autowired
	private AddrDao addrDao;

	public List<Address> findMyAddress(long parentid) {
		return addrDao.searchMyAddress(parentid);
	}

	public Address findMyDefaultAddress(long parentid) {
		return addrDao.searchMyDefaultAddress(parentid);
	}

	public int deleteAddress(long id) {
		return addrDao.remove(id);
	}

	public Address createAddress(Address address) {
		if ("Y".equals(address.getIsdefault())) {
			Address def = addrDao.searchMyDefaultAddress(address.getParentid());
			if (def != null) {
				def.setIsdefault("N");
				addrDao.update(def);
			}
		}
		addrDao.add(address);
		return address;
	}

	public int updateAddress(Address address) {
		Address original = addrDao.search(address.getId());
		original.setReceiver(address.getReceiver());
		original.setPhone(address.getPhone());
		original.setCityid(address.getCityid());
		original.setProvinceid(address.getProvinceid());
		original.setCountyid(address.getCountyid());
		original.setAddr(address.getAddr());
		original.setPostcode(address.getPostcode());
		original.setIsdefault(address.getIsdefault());
		if ("Y".equals(address.getIsdefault())) {
			Address def = addrDao.searchMyDefaultAddress(original.getParentid());
			if (def != null) {
				def.setIsdefault("N");
				addrDao.update(def);
			}
		}
		return addrDao.update(address);
	}
}
