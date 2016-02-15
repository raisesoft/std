package com.cd.bbh.school.app.vo;

import java.util.List;

public class CourseRecordVO {

	private String dayOfWeek;
	private List<CourseRecord> courseRecords;
	
	public String getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public List<CourseRecord> getCourseRecords() {
		return courseRecords;
	}
	public void setCourseRecords(List<CourseRecord> courseRecords) {
		this.courseRecords = courseRecords;
	}
}
