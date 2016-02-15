package com.cd.bbh.parent.school.vo;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.cd.bbh.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShuttleVO {

	private Date admissionTime;
	private Date leaveTime;
	@JsonIgnore
	private String sender;
	private String senderHeadImg;
	@JsonIgnore
	private String senderRelation;
	private String senderRemark;

	@JsonIgnore
	private String receiver;
	@JsonIgnore
	private String receiverHeadImg;
	@JsonIgnore
	private String receiverRelation;
	private String receiverRemark;

	// public void setNames(String names) {
	// if (StringUtils.isNotBlank(names) && names.contains(",")) {
	// String[] nameArray = names.split(",");
	// this.sender = nameArray[0];
	// this.receiver = nameArray[1];
	// }
	// }

	public void setShuttleTimes(String times) {
		if (StringUtils.isNotBlank(times) && times.contains(",")) {
			String[] timesArray = times.split(",");
			this.admissionTime = DateUtils.parseDateTime(timesArray[0]);
			this.leaveTime = DateUtils.parseDateTime(timesArray[1]);
		}
	}

	//
	// public void setHeadImages(String headimages) {
	// if (StringUtils.isNotBlank(headimages) && headimages.contains(",")) {
	// String[] headimageArray = headimages.split(",");
	// this.senderHeadImg = "-".equals(headimageArray[0]) ? null :
	// headimageArray[0];
	// this.receiverHeadImg = "-".equals(headimageArray[1]) ? null :
	// headimageArray[1];
	// }
	// }
	//
	// public void setRelations(String relations) {
	// if (StringUtils.isNotBlank(relations) && relations.contains(",")) {
	// String[] relationArray = relations.split(",");
	// this.senderRelation = "-".equals(relationArray[0]) ? null :
	// relationArray[0];
	// this.receiverRelation = "-".equals(relationArray[1]) ? null :
	// relationArray[1];
	// }
	// }

	public void setRemarks(String remarks) {
		if (StringUtils.isNotBlank(remarks) && remarks.contains(",")) {
			String[] remarkArray = remarks.split(",");
			this.senderRemark = "-".equals(remarkArray[0]) ? null : remarkArray[0];
			this.receiverRemark = "-".equals(remarkArray[1]) ? null : remarkArray[1];
		}
	}

	public String getSender() {
		return sender;
	}

	public Date getAdmissionTime() {
		return admissionTime;
	}

	public String getSenderHeadImg() {
		return senderHeadImg;
	}

	public String getSenderRemark() {
		return senderRemark;
	}

	public String getSenderRelation() {
		return senderRelation;
	}

	public String getReceiver() {
		return receiver;
	}

	public Date getLeaveTime() {
		return leaveTime;
	}

	public String getReceiverHeadImg() {
		return receiverHeadImg;
	}

	public String getReceiverRelation() {
		return receiverRelation;
	}

	public String getReceiverRemark() {
		return receiverRemark;
	}

}
