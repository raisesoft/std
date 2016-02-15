package com.cd.bbh.common.exception;

import com.cd.bbh.common.enums.ResultEnum;

public class JsonException extends ApplicationException {
	private static final long serialVersionUID = 1L;

	/**
	 * JSON格式化异常构造函数
	 * 
	 * @param code
	 *            错误代码
	 * @param errorMsg
	 *            错误提示信息，用于提示输入用户
	 */
	public JsonException(ResultEnum validation) {
		super(validation);
	}

	/**
	
	 * 
	 */
	public JsonException(String message, ResultEnum validation) {
		super(message, validation);
	}

	/**
	 * JSON格式化异常构造函数
	 * 
	 * @param code
	 *            错误代码
	 * @param errorMsg
	 *            错误提示信息，用于提示输入用户
	 * @param message
	 *            用于错误日志
	 * @param cause
	 */
	public JsonException(String message, Throwable cause, ResultEnum validation) {
		super(message, cause, validation);
	}

	/**
	 * JSON格式化异常构造函数
	 * 
	 * @param code
	 *            错误代码
	 * @param errorMsg
	 *            错误提示信息，用于提示输入用户
	 * @param cause
	 */
	public JsonException(ResultEnum validation, Throwable cause) {
		super(validation, cause);
	}

	/**
	 * JSON格式化异常构造函数
	 * 
	 * @param code
	 *            错误代码
	 * @param errorMsg
	 *            错误提示信息，用于提示输入用户
	 * @param message
	 *            用于错误日志
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	protected JsonException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResultEnum validation) {
		super(message, cause, enableSuppression, writableStackTrace, validation);
	}
}
