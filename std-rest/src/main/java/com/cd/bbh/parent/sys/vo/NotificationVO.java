package com.cd.bbh.parent.sys.vo;

public class NotificationVO {

	private String title;
	private Long messageId;
	private String isAlert;
	private String msgType;
	private String pushType;
	private String pushTags;
	private Long createdBy;

	public NotificationVO() {
	}

	public NotificationVO(String title, Long messageId, String isAlert, String msgType, String pushType, String pushTags, Long createdBy) {
		this.title = title;
		this.messageId = messageId;
		this.isAlert = isAlert;
		this.msgType = msgType;
		this.pushType = pushType;
		this.pushTags = pushTags;
		this.createdBy = createdBy;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getIsAlert() {
		return isAlert;
	}

	public void setIsAlert(String isAlert) {
		this.isAlert = isAlert;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getPushType() {
		return pushType;
	}

	public void setPushType(String pushType) {
		this.pushType = pushType;
	}

	public String getPushTags() {
		return pushTags;
	}

	public void setPushTags(String pushTags) {
		this.pushTags = pushTags;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

}
