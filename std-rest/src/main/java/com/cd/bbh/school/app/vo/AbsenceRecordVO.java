package com.cd.bbh.school.app.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AbsenceRecordVO {
	private long id;
	//请假人
	private String applicantUser;
	private String imgHead;
	//请假人身份  老师(T),学生(S)
	@JsonProperty("applicantType")
	private String applocantType;
	//离开时间
	private Date leaveTime;
	//返回时间
	private Date backTime;
	//请假原因
	private String absenceType;
	//是否需要计算费用
	@JsonProperty("isCharge")
	private String isChange;
	//请假备注
	private String remark;
	//创建时间
	private Date createDate;
	private String createUser;
	//审核请假人
	private String confirmUser;
	//审核时间
	private Date confirmDate;
	private String className;
	private String school;
	//等待 （W）,同意 （A）,拒绝 （R）
	private String state;
	//是否请假  是（Y）,否（N）
	private String isApplied;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getApplicantUser() {
		return applicantUser;
	}
	public void setApplicantUser(String applicantUser) {
		this.applicantUser = applicantUser;
	}
	public String getImgHead() {
		return imgHead;
	}
	public void setImgHead(String imgHead) {
		this.imgHead = imgHead;
	}
	public String getApplocantType() {
		return applocantType;
	}
	public void setApplocantType(String applocantType) {
		this.applocantType = applocantType;
	}
	public Date getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Date leaveTime) {
		this.leaveTime = leaveTime;
	}
	public Date getBackTime() {
		return backTime;
	}
	public void setBackTime(Date backTime) {
		this.backTime = backTime;
	}
	public String getAbsenceType() {
		return absenceType;
	}
	public void setAbsenceType(String absenceType) {
		this.absenceType = absenceType;
	}
	public String getIsChange() {
		return isChange;
	}
	public void setIsChange(String isChange) {
		this.isChange = isChange;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getConfirmUser() {
		return confirmUser;
	}
	public void setConfirmUser(String confirmUser) {
		this.confirmUser = confirmUser;
	}
	public Date getConfirmDate() {
		return confirmDate;
	}
	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsApplied() {
		return isApplied;
	}
	public void setIsApplied(String isApplied) {
		this.isApplied = isApplied;
	}
	
}
