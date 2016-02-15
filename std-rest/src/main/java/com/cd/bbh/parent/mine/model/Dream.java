package com.cd.bbh.parent.mine.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "dreamid", "parentid", "childid", "voucher", "degree", "stat", "dtsign", "createdby", "dtcreate", "cargo" })
public class Dream {
	private long dreamid;

	@JsonIgnore
	private long parentid;

	@JsonIgnore
	private long childid;

	private long voucher;

	private String stat;

	@JsonIgnore
	private Date dtsign;

	@JsonIgnore
	private long createdby;

	@JsonIgnore
	private Date dtcreate;

	private Cargo cargo;

	public long getDreamid() {
		return dreamid;
	}

	public void setDreamid(long dreamid) {
		this.dreamid = dreamid;
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public Date getDtcreate() {
		return dtcreate;
	}

	public void setDtcreate(Date dtcreate) {
		this.dtcreate = dtcreate;
	}

	public long getChildid() {
		return childid;
	}

	public void setChildid(long childid) {
		this.childid = childid;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat == null ? null : stat.trim();
	}

	public Date getDtsign() {
		return dtsign;
	}

	public void setDtsign(Date dtsign) {
		this.dtsign = dtsign;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public float getDegree() {
		if ("S".equals(stat)) {
			return Float.parseFloat("1.00");
		}
		if (cargo.getVoucher() <= 0) {
			return Float.parseFloat("0.00");
		}
		return ((float) voucher / cargo.getVoucher());
	}

	public long getVoucher() {
		return voucher;
	}

	public void setVoucher(long voucher) {
		this.voucher = voucher;
	}
}