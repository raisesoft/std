package com.cd.bbh.parent.mine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Cargo {
	private long cargoid;

	private String name;

	@JsonIgnore
	private long catid;

	@JsonIgnore
	private int oriprice;

	private int currprice;

	private String remark;

	private int cntrep;

	@JsonIgnore
	private String stat;

	private int voucher;

	private String category;

	private String paytype;

	private String imgcargo;

	public long getCargoid() {
		return cargoid;
	}

	public void setCargoid(long cargoid) {
		this.cargoid = cargoid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public long getCatid() {
		return catid;
	}

	public void setCatid(long catid) {
		this.catid = catid;
	}

	public int getOriprice() {
		return oriprice;
	}

	public void setOriprice(int oriprice) {
		this.oriprice = oriprice;
	}

	public int getCurrprice() {
		return currprice;
	}

	public void setCurrprice(int currprice) {
		this.currprice = currprice;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public int getCntrep() {
		return cntrep;
	}

	public void setCntrep(int cntrep) {
		this.cntrep = cntrep;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat == null ? null : stat.trim();
	}

	public int getVoucher() {
		return voucher;
	}

	public void setVoucher(int voucher) {
		this.voucher = voucher;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category == null ? null : category.trim();
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype == null ? null : paytype.trim();
	}

	public String getImgcargo() {
		return imgcargo;
	}

	public void setImgcargo(String imgcargo) {
		this.imgcargo = imgcargo == null ? null : imgcargo.trim();
	}
}