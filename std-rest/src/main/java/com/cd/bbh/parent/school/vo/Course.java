package com.cd.bbh.parent.school.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Course {

	@JsonIgnore
	private Long courseId;
	private String courseName;
	private String courseComments;
	private String courseImage;
	private String teacher;
	private Date begin;
	private Date end;
	private long duration;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseComments() {
		return courseComments;
	}

	public void setCourseComments(String courseComments) {
		this.courseComments = courseComments;
	}

	public String getCourseImage() {
		return courseImage;
	}

	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public long getDuration() {
		if(duration == 0){
			duration = (end.getTime() - begin.getTime())/(1000*60);
		}
		return duration;
	}
}
