package com.cd.bbh.common.section;

import com.cd.bbh.common.enums.ValueEnum;


public enum UserStateEnum implements ValueEnum {
	ACTIVE("A", "激活"), CREATED("C", "创建"), INACTIVE("I", "未激活");

	private String siginal;
	private String desc;

	private UserStateEnum(String siginal, String desc) {
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
