package com.cd.bbh.parent.mine.vo;

import java.util.List;

public class MediaVO {
	private Long mediaid;
	private String mediaName;
	private String remark;
	private String creator;
	private String createDate;
	private List<MediaItemVO> items;

	public Long getMediaid() {
		return mediaid;
	}

	public void setMediaid(Long mediaid) {
		this.mediaid = mediaid;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public List<MediaItemVO> getItems() {
		return items;
	}

	public void setItems(List<MediaItemVO> items) {
		this.items = items;
	}

}