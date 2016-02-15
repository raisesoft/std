package com.cd.bbh.parent.mine.model;

import java.util.Date;

public class ChildMedia {
	private long mediaid;
	private Date dtcreate;
	private String creator;
	private long creatorid;
	private String remark;
	private String mediapath;
	private String mediatype;

	public long getMediaid() {
		return mediaid;
	}

	public void setMediaid(long mediaid) {
		this.mediaid = mediaid;
	}

	public Date getDtcreate() {
		return dtcreate;
	}

	public void setDtcreate(Date dtcreate) {
		this.dtcreate = dtcreate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMediapath() {
		return mediapath;
	}

	public void setMediapath(String mediapath) {
		this.mediapath = mediapath;
	}

	public String getMediatype() {
		return mediatype;
	}

	public void setMediatype(String mediatype) {
		this.mediatype = mediatype;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public long getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(long creatorid) {
		this.creatorid = creatorid;
	}

}
