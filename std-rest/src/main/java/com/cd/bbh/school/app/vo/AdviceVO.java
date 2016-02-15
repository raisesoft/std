package com.cd.bbh.school.app.vo;

import com.cd.bbh.common.enums.JPushEnum;

public class AdviceVO {
	private Long adviceid;
	private String adviceTitle;
	private String adviceContent;
	private String level;
	private String state;
	private String createDate;
	private Long creatorid;
	private String creator;
	private String pushTags;
	private JPushEnum type;

	public Long getAdviceid() {
		return adviceid;
	}

	public void setAdviceid(Long adviceid) {
		this.adviceid = adviceid;
	}

	public String getAdviceTitle() {
		return adviceTitle;
	}

	public void setAdviceTitle(String adviceTitle) {
		this.adviceTitle = adviceTitle;
	}

	public String getAdviceContent() {
		return adviceContent;
	}

	public void setAdviceContent(String adviceContent) {
		this.adviceContent = adviceContent;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Long getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(Long creatorid) {
		this.creatorid = creatorid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getPushTags() {
		return pushTags;
	}

	public void setPushTags(String pushTags) {
		this.pushTags = pushTags;
	}

	public JPushEnum getType() {
		return type;
	}

	public void setType(JPushEnum type) {
		this.type = type;
	}

}
