package com.cd.bbh.common.enums;


public enum RoleEnum implements ValueEnum {
	PNORMAL("R", "一般家长（未到幼儿园注册）"), PAUTH("C", "认证家长（已经到幼儿园注册）"), TEACHER("40", "老师"), SCHOOL("30", "学校");

	private String siginal;
	private String desc;

	private RoleEnum(String siginal, String desc) {
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
