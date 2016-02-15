package com.cd.bbh.parent.mine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.parent.mine.dao.CargoDao;
import com.cd.bbh.parent.mine.dao.ChildrenDao;
import com.cd.bbh.parent.mine.dao.DreamDao;
import com.cd.bbh.parent.mine.model.CargoPayModel;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.model.Dream;

/**
 * Created by aowin on 2015/11/13.
 */
@Service
public class DreamService {

	@Autowired
	private DreamDao dreamDao;

	@Autowired
	private ChildrenDao childDao;

	@Autowired
	private CargoDao cargoDao;

	public List<Dream> findMyDreams(Long childid) {
		return dreamDao.searchDreams(childid);
	}

	public List<Dream> searchDreamHistory(Long childid) {
		return dreamDao.searchDreamHistory(childid);
	}

	public int addDream(long cargoid, long parentid, long childid) {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("cargoid", cargoid);
		params.put("parentid", parentid);
		params.put("childid", childid);
		return dreamDao.add(params);
	}

	public void updateDream(Long dreamid, String stat, int buyAmount, String paytype) {

		Map<String, Object> cargoMap = cargoDao.searchCargoByDreamId(dreamid);
		Long childid = (Long) cargoMap.get("childid");
		Long cargoid = (Long) cargoMap.get("cargoid");
		int amount = (int) cargoMap.get("cntrep"); // 库存
		float voucher = (float)(((BigDecimal) cargoMap.get("voucher")).floatValue()*buyAmount);
		float currprice = (float)(((BigDecimal) cargoMap.get("currprice")).floatValue()*buyAmount);
		
		Child child = childDao.search(childid);
		float haveDoller = child.getAvailabledoller();
		float haveVoucher = child.getVoucher();
		
		if (amount < buyAmount || amount < 1) {
			throw new ApplicationException(ResultEnum.CARGO_NOT_ENOUGH_ERROR);
		}
		if ((CargoPayModel.VOUCHER.key().equals(paytype) && haveVoucher < voucher) //
				|| (CargoPayModel.DOLLER.key().equals(paytype) && haveDoller < currprice)) {
			throw new ApplicationException(ResultEnum.USER_MONEY_NOT_ENOUGH_ERROR);
		}

		Map<String, Object> dreamParams = new HashMap<String, Object>();
		dreamParams.put("stat", stat);
		dreamParams.put("dreamid", dreamid);
		dreamDao.update(dreamParams);
		Map<String, Object> cargoParams = new HashMap<String, Object>();
		cargoParams.put("cargoid", cargoid);
		cargoParams.put("amount", buyAmount);
		cargoDao.updateCargoAmount(cargoParams);
		if (CargoPayModel.VOUCHER.key().equals(paytype)) {
			child.setVoucher(haveVoucher - voucher);
		}
		if (CargoPayModel.DOLLER.key().equals(paytype)) {
			child.setAvailabledoller(haveDoller - currprice);
		}
		childDao.updateByRealizeDream(child);
	}
}
