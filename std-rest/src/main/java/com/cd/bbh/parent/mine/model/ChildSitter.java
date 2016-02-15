package com.cd.bbh.parent.mine.model;

import java.util.ArrayList;
import java.util.List;

public class ChildSitter {
	private long childid;
	private String name;
	private String headimg;
	private String gender;
	private List<ChildDevice> devices;
	private List<ChildDevice> otherDevices;

	public long getChildid() {
		return childid;
	}

	public void setChildid(long childid) {
		this.childid = childid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<ChildDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<ChildDevice> devices) {
		this.devices = devices;
	}

	public List<ChildDevice> getOtherDevices() {
		return otherDevices;
	}

	public void setOtherDevices(List<ChildDevice> otherDevices) {
		this.otherDevices = otherDevices;
	}

	public void addOtherDevice(ChildDevice device) {
		if (this.otherDevices == null) {
			this.otherDevices = new ArrayList<ChildDevice>();
		}
		this.otherDevices.add(device);
	}
}
