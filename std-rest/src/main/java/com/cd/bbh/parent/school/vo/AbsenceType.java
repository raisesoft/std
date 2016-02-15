package com.cd.bbh.parent.school.vo;

import org.apache.commons.lang3.StringUtils;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.ValueEnum;
import com.cd.bbh.common.exception.ApplicationException;

public enum AbsenceType implements ValueEnum {
	SICKLEAVE("SL", "病假"), ANNUALLEAVE("AL", "年假"), T_LEAVE("TL", "事假");

	private String siginal;
	private String desc;

	private AbsenceType(String siginal, String desc) {
		this.siginal = siginal;
		this.desc = desc;
	}

	public String siginal() {
		return siginal;
	}

	public String desc() {
		return desc;
	}

	public static AbsenceType value(String value) {
		if (StringUtils.isBlank(value)) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR);
		}
		if (value.equals(SICKLEAVE.desc)) {
			return SICKLEAVE;
		}
		if (value.equals(ANNUALLEAVE.desc)) {
			return ANNUALLEAVE;
		}
		if (value.equals(T_LEAVE.desc)) {
			return T_LEAVE;
		}
		throw new ApplicationException(ResultEnum.OPERATION_ERROR);
	}
}
