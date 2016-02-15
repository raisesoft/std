package com.cd.bbh.parent.sys.vo;

import java.util.Date;

public class MessageVO {
	private Long messageId;
	private String title;
	private String content;
	private String msgType;
	private Long creatorId;
	private Date createDate;
	private String isCallback;
	private String relation;
	private String status;
	private Long childid;

	public MessageVO() {
	}

	public MessageVO(String title, String content, String msgType, Long creatorId, String isCallback, String relation, String status, Long childid) {
		this.title = title;
		this.content = content;
		this.msgType = msgType;
		this.creatorId = creatorId;
		this.isCallback = isCallback;
		this.relation = relation;
		this.status = status;
		this.childid = childid;
	}

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsCallback() {
		return isCallback;
	}

	public void setIsCallback(String isCallback) {
		this.isCallback = isCallback;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getChildid() {
		return childid;
	}

	public void setChildid(Long childid) {
		this.childid = childid;
	}
}
