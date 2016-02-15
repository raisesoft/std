package com.cd.bbh.common.enums;

public enum JPushEnum implements ValueEnum {

	AREA("AREA", "区域"), CLASS("CLASS", "班级"), SCHOOL("SCHOOL", "学校"), PERSON("PERSON", "个人");

	private String siginal;
	private String desc;

	private JPushEnum(String siginal, String desc) {
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
