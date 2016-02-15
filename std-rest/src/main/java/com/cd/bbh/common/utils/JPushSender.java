//package com.cd.bbh.common.utils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.annotation.PostConstruct;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//
//import cn.jpush.api.JPushClient;
//import cn.jpush.api.common.resp.APIConnectionException;
//import cn.jpush.api.common.resp.APIRequestException;
//import cn.jpush.api.push.model.Message;
//import cn.jpush.api.push.model.Platform;
//import cn.jpush.api.push.model.PushPayload;
//import cn.jpush.api.push.model.PushPayload.Builder;
//import cn.jpush.api.push.model.audience.Audience;
//import cn.jpush.api.push.model.notification.AndroidNotification;
//import cn.jpush.api.push.model.notification.IosNotification;
//import cn.jpush.api.push.model.notification.Notification;
//
//import com.cd.bbh.common.Configuration;
//import com.cd.bbh.common.enums.ResultEnum;
//import com.cd.bbh.common.exception.ApplicationException;
//
//@Component("jpush")
//public final class JPushSender {
//	private static final Logger logger = LoggerFactory.getLogger(JPushSender.class);
//	private JPushClient pushClient;
//
//	@Autowired
//	private Configuration configuration;
//
//	@PostConstruct
//	public void init() {
//		String[] temp = new String(Base64.decodeBase64(configuration.pushkey)).split(":");
//		pushClient = new JPushClient(temp[1], temp[0], Integer.parseInt(temp[2]));
//	}
//
//	/**
//	 * 获取极光上所有的Tags
//	 * 
//	 * @return
//	 * @throws APIConnectionException
//	 * @throws APIRequestException
//	 */
//	public final List<String> queryTags() {
//		try {
//			return pushClient.getTagList().tags;
//		} catch (APIConnectionException | APIRequestException e) {
//			logger.error("Query jpush tag failed...", e);
//			throw new ApplicationException(ResultEnum.MESSAGE_SEND_ERROR, e);
//		}
//	}
//
//	/**
//	 * 
//	 * 全推送
//	 * 
//	 * @param message
//	 *            不能为空
//	 * @param title
//	 * @param alert
//	 * @throws APIConnectionException
//	 * @throws APIRequestException
//	 */
//	public final void sendMessageAll(String message, String title) {
//		Builder builder = buildPayload(message, title, Audience.all());
//		try {
//			pushClient.sendPush(builder.build());
//		} catch (APIConnectionException | APIRequestException e) {
//			logger.error("Query jpush tag failed...", e);
//			throw new ApplicationException(ResultEnum.MESSAGE_SEND_ERROR, e);
//		}
//	}
//
//	/**
//	 * @param tags
//	 *            用户分组
//	 * @param message
//	 *            透传的消息
//	 * @param title
//	 * @param alert
//	 *            提示信息
//	 * @throws APIConnectionException
//	 * @throws APIRequestException
//	 */
//	public final void sendMessageByTags(List<String> tags, String message, String title) {
//		List<String> validTags = validTags(tags);
//		if (validTags.size() == 0) {
//			return;
//		}
//		Builder builder = buildPayload(message, title, Audience.tag(validTags));
//		try {
//			pushClient.sendPush(builder.build());
//		} catch (APIConnectionException | APIRequestException e) {
//			logger.error("Query jpush tag failed...", e);
//			throw new ApplicationException(ResultEnum.MESSAGE_SEND_ERROR, e);
//		}
//	}
//
//	/**
//	 * @param message
//	 *            透传的消息
//	 * @param title
//	 * @param alert
//	 *            提示信息
//	 * @param regids
//	 *            用户在极光注册的ID
//	 * @throws APIConnectionException
//	 * @throws APIRequestException
//	 */
//	public final void sendMessageByRegids(String message, String title, String... regids) {
//		if (regids.length <= 0) {
//			return;
//		}
//		Builder builder = buildPayload(message, title, Audience.registrationId(regids));
//		try {
//			pushClient.sendPush(builder.build());
//		} catch (APIConnectionException | APIRequestException e) {
//			logger.error("Query jpush tag failed...", e);
//			throw new ApplicationException(ResultEnum.MESSAGE_SEND_ERROR, e);
//		}
//	}
//
//	/**
//	 * 筛选出合理的标签
//	 * 
//	 * @param tags
//	 * @return
//	 */
//	public List<String> validTags(List<String> tags) {
//		List<String> existTags = queryTags();
//		List<String> validTags = new ArrayList<String>();
//		for (String string : tags) {
//			if (existTags.contains(string)) {
//				validTags.add(string);
//			}
//		}
//		return validTags;
//	}
//
//	private Builder buildPayload(String message, String title, Audience audience) {
//		if (StringUtils.isBlank(message)) {
//			throw new ApplicationException(ResultEnum.MESSAGE_SEND_ERROR);
//		}
//		Builder builder = PushPayload.newBuilder()//
//				.setPlatform(Platform.all())//
//				.setAudience(audience)//
//				.setMessage(Message.newBuilder()//
//						.setContentType(MediaType.APPLICATION_JSON_VALUE)//
//						.setMsgContent(message)//
//						.setTitle(title)//
//						.build())//
//				.setNotification(Notification.newBuilder()//
//						.setAlert(title)//
//						.addPlatformNotification(AndroidNotification.newBuilder()//
//								.setBuilderId(3)//
//								.build())//
//						.addPlatformNotification(IosNotification.newBuilder()//
//								.setContentAvailable(true)//
//								.setSound("sound.caf")//
//								.addExtra("content", message)//
//								.incrBadge(1)//
//								.build())//
//						.build());
//		return builder;
//	}
//}
