package com.cd.bbh.parent.mine.model;

public enum CargoPayModel {

	VOUCHER("V", "兑换券"), DOLLER("C", "宝贝数");

	private String key;
	private String desc;

	private CargoPayModel(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

	public String key() {
		return key;
	}
}
