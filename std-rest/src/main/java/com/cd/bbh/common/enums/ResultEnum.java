package com.cd.bbh.common.enums;

public enum ResultEnum {

	SUCCESS("200", "操作成功"), //
	QUERY_SUCCESS("200", "查询成功"), //
	UNKNOW_ERROR("500", "内部错误"), //
	OPERATION_ERROR("501", "操作失败"), //

	// 权限校验失败
	NOT_AUTH_ERROR("401", "该用户未获得授权"), //
	NOT_ALLOWED_ERROR("402", "该用户已被禁用"), //
	NOT_LOGIN_ERROR("403", "该用户未登录"), //

	// 公共参数错误代码
	PARAM_MISSING("2000", "请求参数%s缺失"), //
	PARAM_PATTERN_ERROR("2001", "请求参数格式错误"), //
	PARAM_NULL_ERROR("2002", "请求参数%s不能为空"), //
	DATE_PATTERN_ERROR("2003", "日期字符串格式错误"), //
	FILE_SIZE_EXCEEDS_ERROR("2004", "上传单个文件的大小超过10m限制"), //
	FILE_TYPE_ERROR("2005", "上传文件格式错误.允许的格式有:%s"), //

	// 用户登录
	USER_LOGIN_SUCCESS("200", "登录成功"), //
	USER_REGISTOR_SUCCESS("200", "注册成功"), //
	USER_LOGIN_ERROR("4001", "用户和密码不正确"), //
	USER_LOGIN_PARAM_ERROR("4002", "手机号或密码不能为空"), //
	USER_LOGIN_ROLE_ERROR("4003", "用户不能登录"), //
	USER_REGISTOR_EXISTS_ERROR("4004", "该号码已是注册用户"), //
	USER_NOT_EXISTS_ERROR("4005", "用户不存在"), //
	USER_MONEY_NOT_ENOUGH_ERROR("4006", "用户账户兑换券或宝贝数不够"), //
	USER_PHONE_NOT_EXISTS_ERROR("4019", "手机号不存在，请前往注册"),
	
	
	// 密码修改
	PASSWORD_UPDATE_SUCCESS("200", "密码修改成功"), //
	PASSWORD_UPDATE_ORG_PASS_ERROR("4007", "原密码错误"), //
	PASSWORD_UPDATE_SAME_PASS_ERROR("4008", "新密码和旧密码不能相同"), //

	// 验证码发送
	SMSCODE_SEND_SUCCESS("200", "获取验证码成功"), //
	SMSCODE_SEND_ERROR("4009", "获取验证码失败，请在%s分钟之后再试"), //
	SMSCODE_EXPIRE_ERROR("4010", "验证失败,该验证码已过期或不存在"), //

	// 用户查询
	CHILD_QUERY_BY_OTHER_PARENT("4011", "不能查询其他用户的孩子"), //

	// 父母未选择子女上传文件
	UPLOAD_WITHOUT_CHILD_ERROR("4012", "未选择小孩"), //
	UPLOAD_SHOOT_TIME_ERROR("4013", "未添加拍摄时间"), //
	UPLOAD_NO_FILE_ERROR("4014", "没有发现上传的文件"), //

	//
	CARGO_NOT_ENOUGH_ERROR("4015", "商品库存不足"),

	// 工作
	WORK_ADD_LIMITE_ERROR("4016", "添加失败,小孩未完成工作超过限制"),

	// 家长邀请
	INVITATION_ERROR("4017", "邀请关注失败"), //
	INVITATION_CONFIRM_ERROR("4020", "已经关注该小孩"),

	// 家长学校错误提示
	NO_CHILD_IN_SCHOOL_ERROR("4018", "该小孩还没有入学"),

	// 家长添加成长记录
	GROW_RECORD_ERROR("4019", "添加成长记录失败"),
	
	// 消息发送
	MESSAGE_SEND_ERROR("4020", "消息发送失败"),

	// 监控付费问题
	MONITOR_PERMISSION_ERROR("4500", "请付费使用摄像头");

	private String desc;
	private String code;

	private ResultEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String desc() {
		return desc;
	}

	public String code() {
		return code;
	}
}
