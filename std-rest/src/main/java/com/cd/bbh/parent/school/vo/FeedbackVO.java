package com.cd.bbh.parent.school.vo;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FeedbackVO {

	private Long feedbackid;
	private String tcomments;
	private String pcomments;
	private String pstat;
	private String name;
	@JsonIgnore
	private String summary;
	private Date createDate;
	private String teacher;
	private List<FeedbackItemVO> items;

	public String getTcomments() {
		return tcomments;
	}

	public void setTcomments(String tcomments) {
		this.tcomments = tcomments;
	}

	public String getPcomments() {
		return pcomments;
	}

	public void setPcomments(String pcomments) {
		this.pcomments = pcomments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<FeedbackItemVO> getItems() {
		return items;
	}

	public void setItems(List<FeedbackItemVO> items) {
		this.items = items;
	}

	public Long getFeedbackid() {
		return feedbackid;
	}

	public void setFeedbackid(Long feedbackid) {
		this.feedbackid = feedbackid;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getPstat() {
		return pstat;
	}

	public void setPstat(String pstat) {
		this.pstat = pstat;
	}
}
