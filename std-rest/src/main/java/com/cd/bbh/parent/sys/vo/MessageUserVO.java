package com.cd.bbh.parent.sys.vo;

import java.util.List;

public class MessageUserVO {

	private String regid;
	private Long userid;
	private String stat;
	private List<MessageTagVO> tags;

	public MessageUserVO() {
	}

	public MessageUserVO(String regid, Long userid, String stat) {
		this.regid = regid;
		this.userid = userid;
		this.stat = stat;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

	public List<MessageTagVO> getTags() {
		return tags;
	}

	public void setTags(List<MessageTagVO> tags) {
		this.tags = tags;
	}
}
