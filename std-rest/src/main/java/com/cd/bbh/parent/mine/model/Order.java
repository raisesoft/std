package com.cd.bbh.parent.mine.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Order {
	private Long orderid;
	private String serialNo;
	private String invoices;
	@JsonIgnore
	private String payChannel;
	private String stat;
	private BigDecimal sum;
	@JsonIgnore
	private Long childid;
	@JsonIgnore
	private Date createDate;
	@JsonIgnore
	private String createdBy;
	private List<ProductOffer> products;

	public Order() {
	}

	public Order(String serialNo, BigDecimal sum, Long childid, String createdBy) {
		this.serialNo = serialNo;
		this.sum = sum;
		this.childid = childid;
		this.createdBy = createdBy;
		this.stat = "W";
	}

	public Long getOrderid() {
		return orderid;
	}

	public void setOrderid(Long orderid) {
		this.orderid = orderid;
	}

	public String getInvoices() {
		return invoices;
	}

	public void setInvoices(String invoices) {
		this.invoices = invoices;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<ProductOffer> getProducts() {
		return products;
	}

	public void setProducts(List<ProductOffer> products) {
		this.products = products;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public Long getChildid() {
		return childid;
	}

	public void setChildid(Long childid) {
		this.childid = childid;
	}
}