package com.cd.bbh.parent.home.vo;

import com.cd.bbh.common.enums.ValueEnum;

public enum SearchEnum implements ValueEnum {
	GOODS("G", "商品"), PUPIL("P", "学生"), SCHOOL("S", "学校");

	private String siginal;
	private String desc;

	private SearchEnum(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

	public String siginal() {
		return siginal;
	}
}
