package com.cd.bbh.tag.vo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Message {

	private String title;
	private String msgType;
	private String isAlert;
	private String content;
	@JsonIgnore
	private String level;
	@JsonIgnore
	private BigDecimal id;
	@JsonIgnore
	private String state;
	@JsonIgnore
	private String msgId;
	@JsonIgnore
	private String pushType;
	@JsonIgnore
	private String pushTags;
	@JsonIgnore
	private String sendDate;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
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

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
