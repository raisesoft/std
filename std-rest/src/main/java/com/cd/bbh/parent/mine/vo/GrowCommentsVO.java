package com.cd.bbh.parent.mine.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class GrowCommentsVO {
	private Long commentid;
	private Long growingid;
	private String description;
	private String icon;
	private String commentCreator;
	private Long commentCreatorid;
	private List<MediaItemVO> medias;
	private List<GrowCommentsVO> subComments;

	public Long getCommentid() {
		return commentid;
	}

	public void setCommentid(Long commentid) {
		this.commentid = commentid;
	}

	public Long getGrowingid() {
		return growingid;
	}

	public void setGrowingid(Long growingid) {
		this.growingid = growingid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getCommentCreator() {
		return commentCreator;
	}

	public void setCommentCreator(String commentCreator) {
		this.commentCreator = commentCreator;
	}

	public Long getCommentCreatorid() {
		return commentCreatorid;
	}

	public void setCommentCreatorid(Long commentCreatorid) {
		this.commentCreatorid = commentCreatorid;
	}

	public List<MediaItemVO> getMedias() {
		return medias;
	}

	public void setMedias(List<MediaItemVO> medias) {
		this.medias = medias;
	}

	public List<GrowCommentsVO> getSubComments() {
		return subComments;
	}

	public void setSubComments(List<GrowCommentsVO> subComments) {
		this.subComments = subComments;
	}

}
