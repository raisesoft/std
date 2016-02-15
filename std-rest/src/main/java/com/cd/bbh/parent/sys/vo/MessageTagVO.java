package com.cd.bbh.parent.sys.vo;

import java.util.Date;

public class MessageTagVO {

	private Long userTagid;
	private String groupid;
	private String group;
	private String remark;
	private String regid;
	private String sync;
	private Date syncDate;

	public Long getUserTagid() {
		return userTagid;
	}

	public void setUserTagid(Long userTagid) {
		this.userTagid = userTagid;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
