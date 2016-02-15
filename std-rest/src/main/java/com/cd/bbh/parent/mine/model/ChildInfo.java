package com.cd.bbh.parent.mine.model;

import java.util.Date;

public class ChildInfo {

	private long infoid;
	private String createdby;
	private Date dtcreate;
	private float height;
	private float weight;

	public long getInfoid() {
		return infoid;
	}

	public void setInfoid(long infoid) {
		this.infoid = infoid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getDtcreate() {
		return dtcreate;
	}

	public void setDtcreate(Date dtcreate) {
		this.dtcreate = dtcreate;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

}
