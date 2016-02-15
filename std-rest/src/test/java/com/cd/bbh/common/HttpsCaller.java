package com.cd.bbh.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpsCaller {

	private static ObjectMapper mapper = new ObjectMapper();
	private static final String CONTENT_TYPE = "application/json";
	private static final String APP_KEY = "03ce1022ac106c3cd4dd611e";
	private static final String MASTER_SECRET = "b6990b7722908ae753825003";
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	private RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();

	private static final String PUSH_URL = "https://api.jpush.cn/v3/push";

	public static void send() throws Exception {
		HttpPost post = buildPost(new HttpPost(PUSH_URL));

		StringEntity entity = new StringEntity(buildTestData(), "UTF-8");
		entity.setContentType(CONTENT_TYPE);
		post.setEntity(entity);

		HttpResponse response = httpclient.execute(post);
		String result = read(response.getEntity().getContent());
		System.out.println(result);
	}

	private static HttpPost buildPost(HttpPost post) throws NoSuchAlgorithmException, KeyManagementException {
		post.setHeader(HttpHeaders.CONTENT_TYPE, CONTENT_TYPE);
		post.setHeader(HttpHeaders.AUTHORIZATION, buildBase64());
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		};

		SSLContext ctx = SSLContext.getInstance("SSL");

		// 使用TrustManager来初始化该上下文，TrustManager只是被SSL的Socket所使用
		ctx.init(null, new TrustManager[] { xtm }, null);

		return post;
	}

	private static String read(InputStream stream) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
		StringBuffer buf = new StringBuffer();
		String line;
		while (null != (line = br.readLine())) {
			buf.append(line).append("\n");
		}
		return buf.toString();
	}

	private static String buildTestData() throws JsonProcessingException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, Object> contentMap = new HashMap<String, Object>();
		contentMap.put("msg_content", "Hi,YoHo");
		contentMap.put("content_type", "text");
		contentMap.put("title", "test");
		contentMap.put("extras", "{\"key\":\"value\"}");
		params.put("platform", "android");
		params.put("audience", "all");
		params.put("message", contentMap);
		return mapper.writeValueAsString(params);
	}

	private static String buildBase64() {
		return new String(Base64.encodeBase64Chunked((APP_KEY + ":" + MASTER_SECRET).getBytes()));
	}

	public static void main(String[] args) throws Exception {

		System.out.println();

		send();
	}
}
