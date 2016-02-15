package com.cd.bbh.parent.mine.vo;

import com.cd.bbh.common.enums.ValueEnum;

public enum ProductEnum implements ValueEnum {
	ACTIVITY("A", "活动"), GOODS("G", "商品"), DEVICE("D", "设备"), WORK("W", "工作");
	private ProductEnum(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	private String siginal;
	private String desc;

	public String siginal() {
		return siginal;
	}

	public String desc() {
		return desc;
	}
}
