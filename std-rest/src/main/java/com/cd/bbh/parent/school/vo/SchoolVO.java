package com.cd.bbh.parent.school.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchoolVO {
	private Long schoolid;
	private String logo;
	private String remark;
	private String address;
	private BigDecimal score;
	private String logopop;
	private Float longtitude;
	private Float latitude;
	@JsonProperty(value = "name")
	private String contact;
	private String email;
	private String phone;

	public Long getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getLogopop() {
		return logopop;
	}

	public void setLogopop(String logopop) {
		this.logopop = logopop;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(Float longtitude) {
		this.longtitude = longtitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
