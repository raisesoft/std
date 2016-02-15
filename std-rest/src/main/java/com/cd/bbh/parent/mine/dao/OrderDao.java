package com.cd.bbh.parent.mine.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.parent.mine.model.Order;
import com.cd.bbh.parent.mine.model.ProductOffer;

public interface OrderDao {

	List<Order> searchOrders(@Param("childid") Long childid, @Param("creator") String creator, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	Order searchOrder(Long orderid);

	int deleteOrder(Long orderid);

	long insertOrder(Order order);

	int insertProductOffer(List<ProductOffer> offers);
}