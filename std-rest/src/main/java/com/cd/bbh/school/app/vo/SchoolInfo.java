package com.cd.bbh.school.app.vo;

public class SchoolInfo extends UserInfo{

	private String imgLogoKey;
	private String remark;
	private String country;
	private String province;
	private String city;
	private String county;
	private String address;
	private float score;
	private String imgLogoUrl;
	private String imgPopUrl;
	
	public String getImgLogoKey() {
		return imgLogoKey;
	}
	public void setImgLogoKey(String imgLogoKey) {
		this.imgLogoKey = imgLogoKey;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getImgLogoUrl() {
		return imgLogoUrl;
	}
	public void setImgLogoUrl(String imgLogoUrl) {
		this.imgLogoUrl = imgLogoUrl;
	}
	public String getImgPopUrl() {
		return imgPopUrl;
	}
	public void setImgPopUrl(String imgPopUrl) {
		this.imgPopUrl = imgPopUrl;
	}
}
