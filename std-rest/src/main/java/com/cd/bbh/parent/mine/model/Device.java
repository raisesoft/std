package com.cd.bbh.parent.mine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Device {
	@JsonProperty(value = "deviceid")
	private Long id;

	private String name;

	private String remark;

	private String useinfo;

	@JsonIgnore
	private float oriprice;

	private float curprice;

	private int cntrep;

	private String type;

	private String imgpath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}

	public String getUseinfo() {
		return useinfo;
	}

	public void setUseinfo(String useinfo) {
		this.useinfo = useinfo == null ? null : useinfo.trim();
	}

	public Float getOriprice() {
		return oriprice;
	}

	public void setOriprice(Float oriprice) {
		this.oriprice = oriprice;
	}

	public Float getCurprice() {
		return curprice;
	}

	public void setCurprice(Float curprice) {
		this.curprice = curprice;
	}

	public int getCntrep() {
		return cntrep;
	}

	public void setCntrep(int cntrep) {
		this.cntrep = cntrep;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath == null ? null : imgpath.trim();
	}
}