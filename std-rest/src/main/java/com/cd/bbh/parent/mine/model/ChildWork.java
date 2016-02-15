package com.cd.bbh.parent.mine.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ChildWork extends Work {

	private String comments;

	private String address;

	private float score;

	private Date finishdate;

	@JsonIgnore
	private String stat;

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Date getFinishdate() {
		return finishdate;
	}

	public void setFinishdate(Date finishdate) {
		this.finishdate = finishdate;
	}

	public String getStat() {
		return stat;
	}

	public void setStat(String stat) {
		this.stat = stat;
	}

}
