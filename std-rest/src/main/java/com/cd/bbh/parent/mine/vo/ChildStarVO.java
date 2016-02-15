package com.cd.bbh.parent.mine.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.cd.bbh.common.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_EMPTY)
public class ChildStarVO {
	private Long childid;
	private String headimg;
	private String nickname;
	private String ageDetail;
	private BigDecimal weekdoller;
	private BigDecimal monthdoller;

	public Long getChildid() {
		return childid;
	}

	public void setChildid(Long childid) {
		this.childid = childid;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAgeDetail() {
		return ageDetail;
	}

	public void setBirthday(Date dtbirthday) {
		this.ageDetail = DateUtils.getAgeDetail(dtbirthday);
	}

	public BigDecimal getWeekdoller() {
		return weekdoller;
	}

	public void setWeekdoller(BigDecimal weekdoller) {
		this.weekdoller = new BigDecimal(weekdoller.intValue()).setScale(2);
	}

	public BigDecimal getMonthdoller() {
		return monthdoller;
	}

	public void setMonthdoller(BigDecimal monthdoller) {
		this.monthdoller = monthdoller;
	}
}
