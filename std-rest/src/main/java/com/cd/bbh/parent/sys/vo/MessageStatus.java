package com.cd.bbh.parent.sys.vo;

import com.cd.bbh.common.enums.ValueEnum;

public enum MessageStatus implements ValueEnum {
	SEND("S", "发送成功"), FAIED("F", "发送失败"), PENDDING("W", "等待发送");

	private String siginal;
	private String desc;

	private MessageStatus(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	@Override
	public String siginal() {
		return siginal;
	}

	@Override
	public String desc() {
		return desc;
	}
}
