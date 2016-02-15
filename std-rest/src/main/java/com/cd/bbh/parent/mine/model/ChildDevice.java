package com.cd.bbh.parent.mine.model;

public class ChildDevice {
	private long id;
	private long deviceid;
	private String stat;
	private String devicesn;
	private String account;
	private String password;
	private String imgpath;

	public ChildDevice() {
	}

	public ChildDevice(long deviceid, String imgpath) {
		this.deviceid = deviceid;
		this.imgpath = imgpath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(long deviceid) {
		this.deviceid = deviceid;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public String getDevicesn() {
		return devicesn;
	}

	public void setDevicesn(String devicesn) {
		this.devicesn = devicesn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

}