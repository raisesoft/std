package com.cd.bbh.parent.mine.vo;

import com.cd.bbh.common.enums.ValueEnum;

public enum GrowingEnum implements ValueEnum {

	GROWING("G", "成长记"), THING("T", "大事记"), PICTURE("P", "图片记"), VIDEO("V", "视频记");

	private String siginal;
	private String desc;

	private GrowingEnum(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	public String siginal() {
		return this.siginal;
	}

	public String desc() {
		return this.desc;
	}

}
