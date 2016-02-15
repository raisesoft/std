package com.cd.bbh.common.enums;

public enum ClientTypeEnum implements ValueEnum {

	SCHOOL("T", "校园端"), PARENT("P", "家长端"), PUNCH("S", "打卡机");

	private String siginal;
	private String desc;

	private ClientTypeEnum(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	public String siginal() {
		return siginal;
	}

	public String desc() {
		return desc;
	}
}
