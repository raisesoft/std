package com.cd.bbh.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;

public class SmsUtil {
	private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);

	private static final String CONTENT_CHARSET = "UTF-8";
	private static final String SMS_SERVICE_PLATFORM = "http://smsapi.c123.cn/OpenPlatform/OpenApi";
	private static final String SMS_SERVICE_ACCOUNT = "1001@501081940001";
	private static final String SMS_SERVICE_TYPE = "sendOnce";
	private static final String SMS_SERVICE_SECURITY_KEY = "6D89DED23C407B22324731CB6074570E";
	private static final String SMS_SERVICE_CHANNEL = "3377";
	private static final Integer SMS_SERVICE_TIMEOUT = 2000;
	public static final Integer SMS_VERIFY_CODE_VALID_DURING = 600;
	public static final Integer SMS_VERIFY_CODE_MAX = 10000;

	public static String genVerifyCode() {
		Random r = new Random();
		Integer ret = r.nextInt(SMS_VERIFY_CODE_MAX);
		if (ret < SMS_VERIFY_CODE_MAX / 10) {
			ret += SMS_VERIFY_CODE_MAX / 10;
		}
		return ret + "";
	}

	public static void sendSMS(String phone, String msg) {
		try {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("action", SMS_SERVICE_TYPE));
			params.add(new BasicNameValuePair("ac", SMS_SERVICE_ACCOUNT));
			params.add(new BasicNameValuePair("authkey", SMS_SERVICE_SECURITY_KEY));
			params.add(new BasicNameValuePair("cgid", SMS_SERVICE_CHANNEL));
			params.add(new BasicNameValuePair("c", msg));
			params.add(new BasicNameValuePair("m", phone));

			HttpPost request = new HttpPost(SMS_SERVICE_PLATFORM);
			request.setEntity(new UrlEncodedFormEntity(params, CONTENT_CHARSET));
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SMS_SERVICE_TIMEOUT).setConnectTimeout(SMS_SERVICE_TIMEOUT).build();// 设置请求和传输超时时间
			request.setConfig(requestConfig);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			HttpResponse response = httpclient.execute(request);
			if (response.getStatusLine().getStatusCode() != 200) {
				logger.error("Failed to trigger sending verify code. Failed to get success status");
				throw new ApplicationException(ResultEnum.UNKNOW_ERROR);
			}
		} catch (Exception e) {
			logger.error("Failed to trigger sending verify code", e);
			throw new ApplicationException(ResultEnum.UNKNOW_ERROR, e);
		}
	}

	public static String createRandomVcode() {
		// 验证码生成
		String vcode = "";
		for (int i = 0; i < 6; i++) {
			vcode = vcode + (int) (Math.random() * 9);
		}
		return vcode;
	}
}
