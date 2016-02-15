package com.cd.bbh.parent.mine.model;

import java.util.Date;

import com.cd.bbh.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Child {
	private long id;

	private String headimg;

	private String name;

	private String nickname;

	private int age;

	private String gender;

	private float totaldoller;

	private float weekdoller;

	private float availabledoller;

	private Date dtbirthday;

	private int cntwork;

	private String stat;

	private float voucher;

	private String bloodtype;

	@JsonIgnore
	private long createdby;

	public Child() {
	}

	public Child(String headimg, String name, String nickName, int age, String gender, Date dtbirthday, String bloodtype, Long createdby) {
		this.headimg = headimg;
		this.name = name;
		this.nickname = nickName;
		this.age = age;
		this.gender = gender;
		this.dtbirthday = dtbirthday;
		this.bloodtype = bloodtype;
		this.createdby = createdby;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg == null ? null : headimg.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname == null ? null : nickname.trim();
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAgeDetail() {
		return DateUtils.getAgeDetail(dtbirthday);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public Float getTotaldoller() {
		return totaldoller;
	}

	public void setTotaldoller(Float totaldoller) {
		this.totaldoller = totaldoller;
	}

	public Float getWeekdoller() {
		return weekdoller;
	}

	public void setWeekdoller(Float weekdoller) {
		this.weekdoller = weekdoller;
	}

	public Float getAvailabledoller() {
		return availabledoller;
	}

	public void setAvailabledoller(Float availabledoller) {
		this.availabledoller = availabledoller;
	}

	public Date getDtbirthday() {
		return dtbirthday;
	}

	public void setDtbirthday(Date dtbirthday) {
		this.dtbirthday = dtbirthday;
	}

	public Integer getCntwork() {
		return cntwork;
	}

	public void setCntwork(Integer cntwork) {
		this.cntwork = cntwork;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat == null ? null : stat.trim();
	}

	public Float getVoucher() {
		return voucher;
	}

	public void setVoucher(Float voucher) {
		this.voucher = voucher;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype == null ? null : bloodtype.trim();
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}
}