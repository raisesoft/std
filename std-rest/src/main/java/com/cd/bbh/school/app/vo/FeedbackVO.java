package com.cd.bbh.school.app.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FeedbackVO {
	
	private long id;
	private String feedName;
	private String teacher;
	private String student;
	private String instruction;
	private String createDate;
	private String teacherComments;
	private String parentComments;
	private String teacherStat;
	private String parentStat;
	@JsonProperty("items")
	private List<FeedbackItemVO> feedBackItems;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getStudent() {
		return student;
	}
	public void setStudent(String student) {
		this.student = student;
	}
	public String getInstruction() {
		return instruction;
	}
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
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
	public List<FeedbackItemVO> getFeedBackItems() {
		return feedBackItems;
	}
	public void setFeedBackItems(List<FeedbackItemVO> feedBackItems) {
		this.feedBackItems = feedBackItems;
	}
	
	
}
