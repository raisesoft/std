package com.cd.bbh.common.exception;

import com.cd.bbh.common.enums.ResultEnum;

public class ApplicationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorMsg;

	/**
	 * JSON格式化异常构造函数
	 * 
	 * @param code
	 *            错误代码
	 * @param errorMsg
	 *            错误提示信息，用于提示输入用户
	 */
	public ApplicationException(ResultEnum validation) {
		super();
		this.errorCode = validation.code();
		this.errorMsg = validation.desc();
	}

	/**
	
	 * 
	 */
	public ApplicationException(String message, ResultEnum validation) {
		super(message);
		this.errorCode = validation.code();
		this.errorMsg = validation.desc();
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
	public ApplicationException(String message, Throwable cause, ResultEnum validation) {
		super(message, cause);
		this.errorCode = validation.code();
		this.errorMsg = validation.desc();
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
	public ApplicationException(ResultEnum validation, Throwable cause) {
		super(cause);
		this.errorCode = validation.code();
		this.errorMsg = validation.desc();
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
	public ApplicationException(String errorCode, String errorDesc, Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		this.errorMsg = errorDesc;
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
	protected ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ResultEnum validation) {
		super(message, cause, enableSuppression, writableStackTrace);
		this.errorCode = validation.code();
		this.errorMsg = validation.desc();
	}

	/**
	 * 获取错误代码
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return this.errorCode;
	}

	/**
	 * 获取错误提示信息
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		return this.errorMsg;
	}
}
