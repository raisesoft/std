package com.cd.bbh.parent.mine.model;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Work {
	private long workid;

	private String workname;

	private int agerange;

	private String workimg;

	private float salary;

	private String frequence;

	private String workremark;

	private BigDecimal workcost;

	@JsonIgnore
	private String createdby;

	@JsonIgnore
	private Date createdate;

	@JsonIgnore
	private String updateby;

	@JsonIgnore
	private Date updatedate;

	private String address;

	private Date workdate;

	public long getWorkid() {
		return workid;
	}

	public void setWorkid(long workid) {
		this.workid = workid;
	}

	public String getWorkname() {
		return workname;
	}

	public void setWorkname(String workname) {
		this.workname = workname;
	}

	public int getAgerange() {
		return agerange;
	}

	public void setAgerange(int agerange) {
		this.agerange = agerange;
	}

	public String getWorkimg() {
		return workimg;
	}

	public void setWorkimg(String workimg) {
		this.workimg = workimg;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public String getFrequence() {
		return frequence;
	}

	public void setFrequence(String frequence) {
		this.frequence = frequence;
	}

	public String getWorkremark() {
		return workremark;
	}

	public void setWorkremark(String workremark) {
		this.workremark = workremark;
	}

	public BigDecimal getWorkcost() {
		return workcost;
	}

	public void setWorkcost(BigDecimal workcost) {
		this.workcost = workcost;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getWorkdate() {
		return workdate;
	}

	public void setWorkdate(Date workdate) {
		this.workdate = workdate;
	}
}