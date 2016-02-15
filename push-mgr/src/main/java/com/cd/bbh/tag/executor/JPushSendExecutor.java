package com.cd.bbh.tag.executor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.PushPayload.Builder;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import com.cd.bbh.tag.service.MessageTagService;
import com.cd.bbh.tag.vo.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("send")
public class JPushSendExecutor implements Executor {

	private static final Logger logger = LoggerFactory.getLogger(JPushSendExecutor.class);

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private JPushClient pushClient;
	@Autowired
	private MessageTagService messageTagService;

	public final void execute() {
		com.cd.bbh.tag.vo.Message message = null;
		try {
			logger.debug("begin send message...");
			List<com.cd.bbh.tag.vo.Message> advices = messageTagService.getMessageByStat("W");
			if (advices == null || advices.size() <= 0) {
				logger.error("No message need to send. Exit executor...");
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			for (com.cd.bbh.tag.vo.Message advice : advices) {
				message = advice;
				String adviceType = advice.getPushType();
				if (StringUtils.isBlank(adviceType)) {
					logger.error("Can't send message without adviceType:  " + mapper.writeValueAsString(advice));
					messageTagService.updateMessageById(advice.getId().longValue(), "F");
				}
				if ("ALL".equals(adviceType)) {// 发送全部
					sendMessageAll(advice);
					logger.debug("Send JPush message to all users");
				} else if ("PERSON".equals(adviceType)) {// 根据用户的registration
															// id来发送
					String[] temp = advice.getPushTags().split(",");
					String[] regids = new String[temp.length];
					for (int i = 0; i < regids.length; i++) {
						regids[i] = temp[i].trim();
					}
					sendMessageByRegids(advice, regids);
					logger.debug("Send JPush message by user registoration id");
				} else {// 根据TAG来发送
					String[] tags = advice.getPushTags().split(",");
					sendMessageByTags(advice, Arrays.asList(tags));
					logger.debug("Send JPush message by tags");
				}
				messageTagService.updateMessageById(advice.getId().longValue(), "S");
			}
		} catch (JsonProcessingException | APIConnectionException | APIRequestException e) {
			logger.error("send message failed", e);
			messageTagService.updateMessageById(message.getId().longValue(), "F");
		}
	}

	/**
	 *
	 * 全推送
	 *
	 * @param message
	 *            不能为空
	 * @param title
	 * @param alert
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 * @throws JsonProcessingException
	 */
	private void sendMessageAll(Message message) throws APIConnectionException, APIRequestException, JsonProcessingException {
		Builder builder = buildPayload(message, Audience.all());
		pushClient.sendPush(builder.build());
	}

	/**
	 * @param message
	 *            透传的消息
	 * @param title
	 * @param alert
	 *            提示信息
	 * @param regids
	 *            用户在极光注册的ID
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 * @throws JsonProcessingException
	 */
	private void sendMessageByRegids(Message message, String... regids) throws APIConnectionException, APIRequestException, JsonProcessingException {
		Builder builder = buildPayload(message, Audience.registrationId(regids));
		pushClient.sendPush(builder.build());
	}

	/**
	 * @param tags
	 *            用户分组
	 * @param message
	 *            透传的消息
	 * @param title
	 * @param alert
	 *            提示信息
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 * @throws JsonProcessingException
	 */
	private void sendMessageByTags(Message message, List<String> tags) throws APIConnectionException, APIRequestException, JsonProcessingException {
		List<String> validTags = validTags(tags);
		if (validTags.size() == 0) {
			return;
		}
		Builder builder = buildPayload(message, Audience.tag(validTags));
		pushClient.sendPush(builder.build());
	}

	private Builder buildPayload(Message message, Audience audience) throws JsonProcessingException {

		Builder builder = initBaseBuilder().setAudience(audience);
		if ("Y".equals(message.getIsAlert())) {
			builder.setNotification(buildNotification(message));
		}
		builder.setMessage(cn.jpush.api.push.model.Message.newBuilder().setMsgContent(mapper.writeValueAsString(message)).build());
		return builder;
	}

	private Builder initBaseBuilder() {
		return PushPayload.newBuilder()//
				.setPlatform(Platform.all())//
				.setOptions(Options.newBuilder().setTimeToLive(86400 * 3).build());
	}

	/**
	 * 筛选出合理的标签
	 *
	 * @param tags
	 * @return
	 * @throws APIConnectionException
	 * @throws APIRequestException
	 */
	private List<String> validTags(List<String> tags) throws APIConnectionException, APIRequestException {
		List<String> existTags = pushClient.getTagList().tags;
		List<String> validTags = new ArrayList<String>();
		for (String string : tags) {
			if (existTags.contains(string)) {
				validTags.add(string);
			}
		}
		return validTags;
	}

	/**
	 * 创建弹出提示
	 * 
	 * @param content
	 * @return
	 * @throws JsonProcessingException
	 */
	private Notification buildNotification(Message message) throws JsonProcessingException {
		return Notification.newBuilder()//
				.setAlert(message.getTitle())//
				.addPlatformNotification(AndroidNotification.newBuilder().build())//
				.addPlatformNotification(IosNotification.newBuilder()//
						.setContentAvailable(true)//
						.setSound("sound.caf")//
						.addExtra("message", mapper.writeValueAsString(message))//
						.incrBadge(1)//
						.build())//
				.build();
	}
}
