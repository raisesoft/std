package com.cd.bbh.parent.mine.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.Pageable;
import com.cd.bbh.parent.mine.dao.OrderDao;
import com.cd.bbh.parent.mine.dao.WorkDao;
import com.cd.bbh.parent.mine.model.Order;
import com.cd.bbh.parent.mine.model.ProductOffer;

@Service
public class OrderService {
	@Autowired
	private WorkDao workDao;
	@Autowired
	private OrderDao orderDao;

	public Order createOrder(Long childid, String creator, int amount, Long productid, BigDecimal price, String productType, String name, String imageUrl) {

		BigDecimal totalPrice = new BigDecimal(amount).multiply(price).setScale(2);
		Order order = new Order(((Long) System.nanoTime()).toString(), totalPrice, childid, creator);
		orderDao.insertOrder(order);
		List<ProductOffer> offers = new ArrayList<ProductOffer>();
		ProductOffer offer = new ProductOffer();
		offer.setOrderid(order.getOrderid());
		offer.setAmount(amount);
		offer.setProductType(productType);
		offer.setProductid(productid);
		offer.setPrice(price.setScale(2));
		offer.setTotalPrice(totalPrice);
		offers.add(offer);
		orderDao.insertProductOffer(offers);
		return orderDao.searchOrder(order.getOrderid());
	}

	public Order findOrder(Long orderid) {
		return orderDao.searchOrder(orderid);
	}

	public int deleteOrder(Long orderid) {
		return orderDao.deleteOrder(orderid);
	}

	public List<Order> findOrders(Long childid, String creator, Pageable pageable) {
		return orderDao.searchOrders(childid, creator, pageable.getPage(), pageable.getPagesize());
	}
}
