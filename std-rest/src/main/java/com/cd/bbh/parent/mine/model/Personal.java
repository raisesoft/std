package com.cd.bbh.parent.mine.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Personal {

	private String username;
	private String headimg;
	private int age;
	private Date birthday;
	private String gender;
	private long userid;
	@JsonIgnore
	private long exist;
	@JsonIgnore
	private String device;
	@JsonIgnore
	private long roleid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getExist() {
		return exist;
	}

	public void setExist(long exist) {
		this.exist = exist;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public long getRoleid() {
		return roleid;
	}

	public void setRoleid(long roleid) {
		this.roleid = roleid;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
}
