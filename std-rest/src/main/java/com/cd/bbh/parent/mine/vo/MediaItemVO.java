package com.cd.bbh.parent.mine.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class MediaItemVO {
	private Long itemid;
	private String mediaPath;
	private String mediaType;
	private String thumbnailPath;
	private String comments;
	@JsonIgnore
	private String thumbnailKey;
	@JsonIgnore
	private String mediaKey;
	@JsonIgnore
	private String iscover;
	@JsonIgnore
	private Date shootDate;
	@JsonIgnore
	private Long growingid;
	@JsonIgnore
	private Long growCommentid;
	@JsonIgnore
	private Long itemCreatorid;
	@JsonIgnore
	private Date itemCreateDate;

	public Long getItemid() {
		return itemid;
	}

	public void setItemid(Long itemid) {
		this.itemid = itemid;
	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getThumbnailPath() {
		return thumbnailPath;
	}

	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getThumbnailKey() {
		return thumbnailKey;
	}

	public void setThumbnailKey(String thumbnailKey) {
		this.thumbnailKey = thumbnailKey;
	}

	public String getMediaKey() {
		return mediaKey;
	}

	public void setMediaKey(String mediaKey) {
		this.mediaKey = mediaKey;
	}

	public Date getShootDate() {
		return shootDate;
	}

	public void setShootDate(Date shootDate) {
		this.shootDate = shootDate;
	}

	public Long getGrowingid() {
		return growingid;
	}

	public void setGrowingid(Long growingid) {
		this.growingid = growingid;
	}

	public Long getGrowCommentid() {
		return growCommentid;
	}

	public void setGrowCommentid(Long growCommentid) {
		this.growCommentid = growCommentid;
	}

	public Date getItemCreateDate() {
		return itemCreateDate;
	}

	public void setItemCreateDate(Date itemCreateDate) {
		this.itemCreateDate = itemCreateDate;
	}

	public Long getItemCreatorid() {
		return itemCreatorid;
	}

	public void setItemCreatorid(Long itemCreatorid) {
		this.itemCreatorid = itemCreatorid;
	}

	public String getIscover() {
		return iscover;
	}

	public void setIscover(String iscover) {
		this.iscover = iscover;
	}
}
