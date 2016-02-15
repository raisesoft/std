package com.cd.bbh.parent.mine.model;

import java.util.Date;

public class MineHome {

	private long id;
	private String headimg;
	private String name;
	private String nickname;
	private Integer age;
	private String gender;
	private float totaldoller;
	private float weekdoller;
	private float availabledoller;
	private Date dtbirthday;
	private int cntwork;
	private String stat;
	private float voucher;
	private String bloodtype;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public float getWeekdoller() {
		return weekdoller;
	}

	public void setWeekdoller(float weekdoller) {
		this.weekdoller = weekdoller;
	}

	public float getAvailabledoller() {
		return availabledoller;
	}

	public void setAvailabledoller(float availabledoller) {
		this.availabledoller = availabledoller;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public float getTotaldoller() {
		return totaldoller;
	}

	public void setTotaldoller(float totaldoller) {
		this.totaldoller = totaldoller;
	}

	public Date getDtbirthday() {
		return dtbirthday;
	}

	public void setDtbirthday(Date dtbirthday) {
		this.dtbirthday = dtbirthday;
	}

	public int getCntwork() {
		return cntwork;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public void setCntwork(int cntwork) {
		this.cntwork = cntwork;
	}

	public float getVoucher() {
		return voucher;
	}

	public void setVoucher(float voucher) {
		this.voucher = voucher;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

}
