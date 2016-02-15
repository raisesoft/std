package com.cd.bbh.parent.school.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("recipe")
public class Recipe implements Comparable<Recipe> {
	@JsonIgnore
	private long recipeid;
	private String duration;
	private String image;
	private String comments;
	@JsonIgnore
	private String compareKey;

	public long getRecipeid() {
		return recipeid;
	}

	public void setRecipeid(long recipeid) {
		this.recipeid = recipeid;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
		if ("D".equals(duration)) {
			this.compareKey = "S";
		} else if ("DE".equals(duration)) {
			this.compareKey = "SE";
		} else {
			this.compareKey = duration;
		}
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompareKey() {
		return compareKey;
	}

	@Override
	public int compareTo(Recipe o) {
		return compareKey.compareTo(o.getCompareKey());
	}
}
