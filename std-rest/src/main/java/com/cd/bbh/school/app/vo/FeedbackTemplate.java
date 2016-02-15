package com.cd.bbh.school.app.vo;

import java.util.List;

public class FeedbackTemplate {
	private Feedback feedBack;
	private List<FeedbackItem> feedBackItems;
	
	public Feedback getFeedBack() {
		return feedBack;
	}
	public void setFeedBack(Feedback feedBack) {
		this.feedBack = feedBack;
	}
	public List<FeedbackItem> getFeedBackItems() {
		return feedBackItems;
	}
	public void setFeedBackItems(List<FeedbackItem> feedBackItems) {
		this.feedBackItems = feedBackItems;
	}
}
