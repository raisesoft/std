package com.cd.bbh.parent.mine.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address {
	@JsonProperty(value = "addressid")
	private long id;
	private long countryid;
	private long provinceid;
	private long cityid;
	private long countyid;

	private String addr;

	private String postcode;

	private String phone;

	private Date dtcreate;
	@JsonIgnore
	private String createdby;
	@JsonIgnore
	private long parentid;

	private String isdefault;

	private String receiver;

	public Address() {
	}

	public Address(long countryid, long provinceid, long cityid, long countyid, String addr, String postcode,//
			String phone, Date dtcreate, String createdby, long parentid, String isdefault, String receiver) {
		this.countryid = countryid;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.countyid = countyid;
		this.addr = addr;
		this.postcode = postcode;
		this.phone = phone;
		this.dtcreate = dtcreate;
		this.createdby = createdby;
		this.parentid = parentid;
		this.isdefault = isdefault;
		this.receiver = receiver;
	}

	public Address(Long id, long countryid, long provinceid, long cityid, long countyid, String addr, //
			String postcode, String phone, Date dtcreate, String createdby, long parentid, String isdefault, String receiver) {
		this.id = id;
		this.countryid = countryid;
		this.provinceid = provinceid;
		this.cityid = cityid;
		this.countyid = countyid;
		this.addr = addr;
		this.postcode = postcode;
		this.phone = phone;
		this.dtcreate = dtcreate;
		this.createdby = createdby;
		this.parentid = parentid;
		this.isdefault = isdefault;
		this.receiver = receiver;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCountryid() {
		return countryid;
	}

	public void setCountryid(long countryid) {
		this.countryid = countryid;
	}

	public long getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(long provinceid) {
		this.provinceid = provinceid;
	}

	public long getCityid() {
		return cityid;
	}

	public void setCityid(long cityid) {
		this.cityid = cityid;
	}

	public long getCountyid() {
		return countyid;
	}

	public void setCountyid(long countyid) {
		this.countyid = countyid;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr == null ? null : addr.trim();
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode == null ? null : postcode.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public Date getDtcreate() {
		return dtcreate;
	}

	public void setDtcreate(Date dtcreate) {
		this.dtcreate = dtcreate;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby == null ? null : createdby.trim();
	}

	public long getParentid() {
		return parentid;
	}

	public void setParentid(long parentid) {
		this.parentid = parentid;
	}

	public String getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(String isdefault) {
		this.isdefault = isdefault;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver == null ? null : receiver.trim();
		;
	}
}