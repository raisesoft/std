package com.cd.bbh.school.app.vo;

import java.util.Date;

public class FeedbackRecord {
	private long id;
	private long pupilId;
	private long templateId;
	private Date createDate;
	private long createUserId;
	private String teacherComments;
	private String parentComments;
	private String teacherStat;
	private String parentStat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPupilId() {
		return pupilId;
	}
	public void setPupilId(long pupilId) {
		this.pupilId = pupilId;
	}
	public long getTemplateId() {
		return templateId;
	}
	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(long createUserId) {
		this.createUserId = createUserId;
	}
	public String getTeacherComments() {
		return teacherComments;
	}
	public void setTeacherComments(String teacherComments) {
		this.teacherComments = teacherComments;
	}
	public String getParentComments() {
		return parentComments;
	}
	public void setParentComments(String parentComments) {
		this.parentComments = parentComments;
	}
	public String getTeacherStat() {
		return teacherStat;
	}
	public void setTeacherStat(String teacherStat) {
		this.teacherStat = teacherStat;
	}
	public String getParentStat() {
		return parentStat;
	}
	public void setParentStat(String parentStat) {
		this.parentStat = parentStat;
	}
}
