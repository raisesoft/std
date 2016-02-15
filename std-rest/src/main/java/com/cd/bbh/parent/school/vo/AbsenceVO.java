package com.cd.bbh.parent.school.vo;

import java.util.Date;

import com.cd.bbh.common.enums.JSONEnumSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class AbsenceVO {

	private Long id;
	private Date leaveDate;
	private Date backDate;
	private String creator;
	private String confirmor;
	private Date confirmDate;
	@JsonSerialize(using = JSONEnumSerializer.class)
	private AbsenceType type;
	@JsonSerialize(using = JSONEnumSerializer.class)
	private AbsenceStatus state;
	private String remark;
	@JsonIgnore
	private Long applicantid;
	@JsonIgnore
	private Long classid;
	@JsonIgnore
	private Long schoolid;
	private String isApplied;

	public AbsenceVO() {
	}

	public AbsenceVO(Date leaveDate, Date backDate, AbsenceType type, AbsenceStatus state, String remark, String creator, Long applicantid, Long classid, Long schoolid) {
		this.leaveDate = leaveDate;
		this.backDate = backDate;
		this.creator = creator;
		this.type = type;
		this.state = state;
		this.remark = remark;
		this.applicantid = applicantid;
		this.classid = classid;
		this.schoolid = schoolid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	public Date getBackDate() {
		return backDate;
	}

	public void setBackDate(Date backDate) {
		this.backDate = backDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public AbsenceStatus getState() {
		return state;
	}

	public void setState(AbsenceStatus state) {
		this.state = state;
	}

	public String getConfirmor() {
		return confirmor;
	}

	public void setConfirmor(String confirmor) {
		this.confirmor = confirmor;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public AbsenceType getType() {
		return type;
	}

	public void setType(AbsenceType type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getClassid() {
		return classid;
	}

	public void setClassid(Long classid) {
		this.classid = classid;
	}

	public Long getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(Long schoolid) {
		this.schoolid = schoolid;
	}

	public Long getApplicantid() {
		return applicantid;
	}

	public void setApplicantid(Long applicantid) {
		this.applicantid = applicantid;
	}

	public String getIsApplied() {
		return isApplied;
	}

	public void setIsApplied(String isApplied) {
		this.isApplied = isApplied;
	}
}
