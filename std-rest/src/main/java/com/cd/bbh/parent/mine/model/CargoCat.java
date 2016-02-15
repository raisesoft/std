package com.cd.bbh.parent.mine.model;

import java.util.Date;

public class CargoCat {
	private long cargocatid;

	private String cat;

	private String createdby;

	private Date dtcreate;

	public long getCargocatid() {
		return cargocatid;
	}

	public void setCargocatid(long cargocatid) {
		this.cargocatid = cargocatid;
	}

	public String getCat() {
		return cat;
	}

	public void setCat(String cat) {
		this.cat = cat == null ? null : cat.trim();
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby == null ? null : createdby.trim();
	}

	public Date getDtcreate() {
		return dtcreate;
	}

	public void setDtcreate(Date dtcreate) {
		this.dtcreate = dtcreate;
	}
}