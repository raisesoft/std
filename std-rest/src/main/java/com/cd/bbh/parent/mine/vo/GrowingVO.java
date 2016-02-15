package com.cd.bbh.parent.mine.vo;

import java.util.Date;
import java.util.List;

import com.cd.bbh.common.enums.JSONCollectionSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonInclude(Include.NON_EMPTY)
public class GrowingVO {
	private Long growid;
	private Long childid;
	private String description;
	private Integer weight;
	private Integer height;
	private String icon;
	@JsonIgnore
	private Date shootDate;
	private String creator;
	private Date createDate;
	private String recordType;
	@JsonSerialize(using = JSONCollectionSerializer.class)
	private List<MediaItemVO> medias;
	private String comments;

	public Long getGrowid() {
		return growid;
	}

	public void setGrowid(Long growid) {
		this.growid = growid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Date getShootDate() {
		return shootDate;
	}

	public void setShootDate(Date shootDate) {
		this.shootDate = shootDate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}

	public List<MediaItemVO> getMedias() {
		return medias;
	}

	public void setMedias(List<MediaItemVO> medias) {
		this.medias = medias;
	}

	public Long getChildid() {
		return childid;
	}

	public void setChildid(Long childid) {
		this.childid = childid;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getComments() {
		return comments;
	}
}
