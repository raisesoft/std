package com.cd.bbh.common.enums;

public enum PayChannelEnum implements ValueEnum {
	VOUCHER("V", "兑换券"), BALANCE("V", "宝贝币"), ALIPAY("V", "支付宝"), WECHAT("V", "微信");

	private String siginal;
	private String desc;

	private PayChannelEnum(String siginal, String desc) {
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
