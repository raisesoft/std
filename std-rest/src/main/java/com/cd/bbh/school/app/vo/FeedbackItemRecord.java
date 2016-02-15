package com.cd.bbh.school.app.vo;

public class FeedbackItemRecord {
	private long id;
	private long feedbackId;
	private long feedBackItemId;
	private float score;
	private String stat;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(long feedbackId) {
		this.feedbackId = feedbackId;
	}
	public long getFeedBackItemId() {
		return feedBackItemId;
	}
	public void setFeedBackItemId(long feedBackItemId) {
		this.feedBackItemId = feedBackItemId;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	
}
