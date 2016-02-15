package com.cd.bbh.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constant {

	@Value("#{configProperties['session.expire.time']}")
	public long sessionExpireTime;

	@Value("#{configProperties['smscode.expire.time']}")
	public long smscodeExpireTime;

	@Value("#{configProperties['smscode.send.needed']}")
	public boolean smscodeSendNeeded;

	@Value("#{configProperties['smscode.send.tips']}")
	public String smscodeSendTips;

	@Value("#{configProperties['page.size']}")
	public int pageSize;

	@Value("#{configProperties['file.upload.dir']}")
	public String fileUploadDir;

	@Value("#{configProperties['file.server.addr']}")
	public String fileServerAddr;

	@Value("#{configProperties['picture.type']}")
	public String pictureType;

	@Value("#{configProperties['vedio.type']}")
	public String vedioType;

	@Value("#{configProperties['pushkey']}")
	public String pushkey;

	@Value("#{configProperties['ffmpeg.path']}")
	public String ffmpegPath;

	@Value("#{configProperties['ffmpeg.cut.picture.resolution']}")
	public String resolution;

	@Value("#{configProperties['message.invitation']}")
	public String invitation;

	@Value("#{configProperties['message.shuttle']}")
	public String shuttle;

	@Value("#{configProperties['message.absence']}")
	public String absence;

	@Value("#{configProperties['message.follow']}")
	public String follow;

	/**
	 * 手机端用户类型，'G' - 游客
	 */
	public static final String PARENT_STAT_GUEST = "G";
	/**
	 * 手机端用户类型，‘R’ - 注册用户
	 */
	public static final String PARENT_STAT_REGIST = "R";
	/**
	 * 手机端用户类型，‘C’ - 认证用户
	 */
	public static final String PARENT_STAT_AUTH = "C";
	/**
	 * 手机端用户类型，‘C’ - 已删除
	 */
	public static final String PARENT_STAT_DEL = "D";
	/**
	 * 手机端用户类型，‘C’ - 禁用
	 */
	public static final String PARENT_STAT_FORB = "F";

	/**
	 * 消息类型。'S' - 系统消息
	 */
	public static final String MSG_SYS_TYPE = "S";
	/**
	 * 消息类型。'U' - 用户消息
	 */
	public static final String MSG_ADVIS_TYPE = "U";
	/**
	 * 消息类型。'P' - 公告
	 */
	public static final String MSG_PUB_TYPE = "P";

	/**
	 * 系统管理员
	 */
	public static final Long ROLE_ADMIN = 10l;
	/**
	 * 普通管理员
	 */
	public static final Long ROLE_MANAGER = 100l;
	/**
	 * 代理商
	 */
	public static final Long ROLE_AGENT = 20l;
	/**
	 * 学校校长
	 */
	public static final Long ROLE_SCHOOL_MASTER = 30l;
	/**
	 * 教师
	 */
	public static final Long ROLE_TEACHER = 40l;
	/**
	 * 家长
	 */
	public static final Long ROLE_PARENT = 50l;
	/**
	 * 家长&教师
	 */
	public static final Long ROLE_TEACHER_PARENT = 4050l;

}
