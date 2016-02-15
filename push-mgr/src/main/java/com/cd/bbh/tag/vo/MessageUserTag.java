package com.cd.bbh.tag.vo;

public class MessageUserTag {

	private Long id;
	private String group;
	private String regid;
	private String sync;
	private String syncDate;
	private String userState;
	private String syncOperation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(String syncDate) {
		this.syncDate = syncDate;
	}

	public String getUserState() {
		return userState;
	}

	public void setUserState(String userState) {
		this.userState = userState;
	}

	public String getSyncOperation() {
		return syncOperation;
	}

	public void setSyncOperation(String syncOperation) {
		this.syncOperation = syncOperation;
	}

}
