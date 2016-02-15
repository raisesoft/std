package com.cd.bbh.parent.mine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.mine.dao.CargoDao;
import com.cd.bbh.parent.mine.model.Cargo;

/**
 * Created by aowin on 2015/11/13.
 */
@Service
public class CargoService {

	@Autowired
	private CargoDao cargoDao;

	public List<Cargo> findAllCargos(Pageable pageable, String category) {
		return cargoDao.searchAll(category, pageable.getPage(), pageable.getPagesize());
	}
}
