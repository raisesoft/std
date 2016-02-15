package com.cd.bbh.common.exception;

import com.cd.bbh.common.enums.ResultEnum;

public class RedisOperationException extends ApplicationException {
	private static final long serialVersionUID = 1L;

	public RedisOperationException(String message, ResultEnum validation) {
		super(message, validation);
	}

	public RedisOperationException(ResultEnum validation, Throwable cause) {
		super(validation, cause);
	}

	public RedisOperationException(ResultEnum validation) {
		super(validation);
	}

	public RedisOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResultEnum validation) {
		super(message, cause, enableSuppression, writableStackTrace, validation);
	}

	public RedisOperationException(String message, Throwable cause, ResultEnum validation) {
		super(message, cause, validation);
	}

}
