package com.cd.bbh.parent.school.vo;

import org.apache.commons.lang3.StringUtils;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.ValueEnum;
import com.cd.bbh.common.exception.ApplicationException;

public enum AbsenceStatus implements ValueEnum {
	WAIT("W", "等待"), AGREE("A", "同意"), REJECT("R", "不同意");

	private String siginal;
	private String desc;

	private AbsenceStatus(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	public String siginal() {
		return siginal;
	}

	public String desc() {
		return desc;
	}

	public static AbsenceStatus value(String value) {
		if (StringUtils.isBlank(value)) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR);
		}
		if (value.equals(WAIT.desc)) {
			return WAIT;
		}
		if (value.equals(AGREE.desc)) {
			return AGREE;
		}
		if (value.equals(REJECT.desc)) {
			return REJECT;
		}
		throw new ApplicationException(ResultEnum.OPERATION_ERROR);
	}
}
