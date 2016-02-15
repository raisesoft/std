package com.cd.bbh.tag.vo;

import java.util.HashSet;
import java.util.Set;

public class PushData {
	private Set<String> toAdd = new HashSet<String>();
	private Set<String> toRemove = new HashSet<String>();

	public Set<String> getToAdd() {
		return toAdd;
	}

	public void setToAdd(Set<String> toAdd) {
		this.toAdd = toAdd;
	}

	public void addToAdd(String regid) {
		toAdd.add(regid);
	}

	public Set<String> getToRemove() {
		return toRemove;
	}

	public void setToRemove(Set<String> toRemove) {
		this.toRemove = toRemove;
	}

	public void addToRemove(String regid) {
		toRemove.add(regid);
	}

	public boolean isValid() {
		return ((toAdd != null && toAdd.size() > 0) || (toRemove != null && toRemove.size() > 0));
	}
}
