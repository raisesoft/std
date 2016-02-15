package com.cd.bbh.school.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author weijing
 *
 */
@JsonRootName("courseRecord")
public class CourseRecord {
	
	
	private long id;
	//课程
	private String course;
	//班级
	@JsonProperty("class")
	private String classVO;
	//授课教师
	private String teacher;
	//上课时间
	private Date beginDate;
	//下课时间
	private Date finishDate;
	//创建人
	private String  createdUser;
	//创建时间
	private Date createDate;
	
	public long id() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getClassVO() {
		return classVO;
	}
	public void setClassVO(String classVO) {
		this.classVO = classVO;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
