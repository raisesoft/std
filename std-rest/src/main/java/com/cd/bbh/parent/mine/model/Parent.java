package com.cd.bbh.parent.mine.model;

public class Parent {
	private long id;

	private String name;

	private String phone;

	private String pass;

	private String deviceno;

	private String idtype;

	private String idno;

	private String stat;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass == null ? null : pass.trim();
	}

	public String getDeviceno() {
		return deviceno;
	}

	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno == null ? null : deviceno.trim();
	}

	public String getIdtype() {
		return idtype;
	}

	public void setIdtype(String idtype) {
		this.idtype = idtype == null ? null : idtype.trim();
	}

	public String getIdno() {
		return idno;
	}

	public void setIdno(String idno) {
		this.idno = idno == null ? null : idno.trim();
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat == null ? null : stat.trim();
	}
}