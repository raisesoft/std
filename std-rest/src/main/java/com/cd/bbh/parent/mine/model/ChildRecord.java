package com.cd.bbh.parent.mine.model;

import java.util.ArrayList;
import java.util.List;

public class ChildRecord {
	private long baseid;
	private String headimg;
	private String name;
	private int age;
	private float availabledoller;

	private List<ChildInfo> infos;
	private List<ChildMedia> medias;
	private List<ChildWork> works;

	public long getBaseid() {
		return baseid;
	}

	public void setBaseid(long baseid) {
		this.baseid = baseid;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public float getAvailabledoller() {
		return availabledoller;
	}

	public void setAvailabledoller(float availabledoller) {
		this.availabledoller = availabledoller;
	}

	public List<ChildInfo> getInfos() {
		return infos;
	}

	public void setInfos(List<ChildInfo> infos) {
		this.infos = infos;
	}

	public List<ChildMedia> getMedias() {
		return medias;
	}

	public void setMedias(List<ChildMedia> medias) {
		this.medias = medias;
	}

	public List<ChildWork> getWorks() {
		return works;
	}

	public void setWorks(List<ChildWork> works) {
		this.works = works;
	}

	public void addChildWork(ChildWork work) {
		if (works == null) {
			works = new ArrayList<ChildWork>();
		}
		works.add(work);
	}

	public void addChildInfo(ChildInfo info) {
		if (infos == null) {
			infos = new ArrayList<ChildInfo>();
		}
		infos.add(info);
	}

	public void addChildMedia(ChildMedia media) {
		if (medias == null) {
			medias = new ArrayList<ChildMedia>();
		}
		medias.add(media);
	}
}
