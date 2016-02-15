package com.cd.bbh.parent.mine.model;

public enum CargoCategory {

	OLDWOMEN("SW", "老年女性"), OLDMAN("SM", "老年男性"), WOMEN("YW", "年轻女性"), MAN("YM", "年轻男性"), GIRL("CW", "小女孩"), BOY("CM", "小男孩");
	private String key;
	private String desc;

	private CargoCategory(String key, String desc) {
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
