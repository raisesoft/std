package com.cd.bbh.parent.sys.vo;

import java.math.BigDecimal;

public class DeviceVO {

	private Long deviceid;
	private String name;
	private String useInfo;
	private String remark;
	private BigDecimal originalPrice;
	private BigDecimal currPrice;
	private int count;
	private String image;

	public Long getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(Long deviceid) {
		this.deviceid = deviceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseInfo() {
		return useInfo;
	}

	public void setUseInfo(String useInfo) {
		this.useInfo = useInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public BigDecimal getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(BigDecimal currPrice) {
		this.currPrice = currPrice;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
