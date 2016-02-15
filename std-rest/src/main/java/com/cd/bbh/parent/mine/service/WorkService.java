package com.cd.bbh.parent.mine.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.parent.mine.dao.OrderDao;
import com.cd.bbh.parent.mine.dao.WorkDao;
import com.cd.bbh.parent.mine.model.ChildWork;
import com.cd.bbh.parent.mine.model.Order;
import com.cd.bbh.parent.mine.model.ProductOffer;
import com.cd.bbh.parent.mine.model.Work;

@Service
public class WorkService {

	@Autowired
	private WorkDao workDao;

	@Autowired
	private OrderDao orderDao;

	public List<Work> findAllWork(Pageable pageable) {
		return workDao.searchAllWork(pageable.getPage(), pageable.getPagesize());
	}

	public Work findWork(Long workid) {
		return workDao.searchWork(workid);
	}

	public List<Work> findUndoWork(Long childid, Pageable pageable) {
		return workDao.searchUndoWork(childid, pageable.getPage(), pageable.getPagesize());
	}

	public List<ChildWork> findWorkHistory(Long childid, Pageable pageable) {
		return workDao.searchWorkHistory(childid, pageable.getPage(), pageable.getPagesize());
	}

	/**
	 * <strong>为小孩购买添加工作</strong></br>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1).检查工作限制（未完成工作不能超过5条）</br>
	 * 
	 * @param orderid
	 * @param creatorid
	 */
	public void createChildWork(Long orderid, Long creatorid) {
		Order order = orderDao.searchOrder(orderid);
		int dataCount = workDao.searchUndoWorkCount(order.getChildid()) + order.getProducts().size();
		if (dataCount >= 5) {
			throw new ApplicationException(ResultEnum.WORK_ADD_LIMITE_ERROR);
		}

		// todo pay for works

		List<ProductOffer> offers = order.getProducts();
		List<Map<String, Object>> params = new ArrayList<Map<String, Object>>();
		for (ProductOffer productOffer : offers) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("childid", order.getChildid());
			param.put("workid", productOffer.getProductid());
			param.put("createdby", creatorid);
			param.put("stat", "C");
			param.put("comments", null);
			params.add(param);
		}
		workDao.insertChildWork(params);
	}

	public int updateChildWork(Long workid, String comments, Long createby, Date finishDate, float score) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("workid", workid);
		params.put("comments", comments);
		params.put("createdby", createby);
		params.put("stat", "D");
		params.put("finishdate", finishDate);
		params.put("score", score);
		return workDao.updateChildWork(params);
	}

	public int deleteChildWork(Long workid) {
		return workDao.removeChildWork(workid);
	}
}
