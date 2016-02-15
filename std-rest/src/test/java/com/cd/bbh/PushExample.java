package com.cd.bbh;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushExample {
	protected static final Logger LOG = LoggerFactory.getLogger(PushExample.class);

	private static final String appKey = "11213f82c89754c3f716a7b4";
	private static final String masterSecret = "230fb4cac4555978d948a137";

	public static final String TITLE = "Test from API example";
	public static final String ALERT = "Test from API Example - alert";
	public static final String MSG_CONTENT = "Test from API Example - msgContent";
	public static final String REGISTRATION_ID = "0011acd3957";

	public static void main(String[] args) throws APIConnectionException, APIRequestException, InterruptedException {
		JPushClient jpushClient = new JPushClient(masterSecret, appKey, 3);
		PushPayload pushPayload = new PushExample().build();
		jpushClient.sendPush(pushPayload);
	}

	public PushPayload build() {
		Notification.Builder notificationBuilder = Notification.newBuilder()//
//				.setAlert("通知提醒")//
//				.addPlatformNotification(AndroidNotification.newBuilder().build())
				.addPlatformNotification(IosNotification.newBuilder()//
						.setSound("sound.caf")//
						.setContentAvailable(true)//
						.incrBadge(1)//
						.addExtra("content", "通知提醒通知提醒通知提醒通知提醒通知提醒通知提醒通知提醒通知提醒通知提醒通知提醒")//
						.build());

		return PushPayload.newBuilder()//
				.setPlatform(Platform.all())//
				.setAudience(Audience.registrationId(REGISTRATION_ID))//
				.setNotification(notificationBuilder.build())//
				.build();
	}
}
