package com.cd.bbh.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpCaller {
	private static Map<String, Object> params = new HashMap<String, Object>();
	private static final CloseableHttpClient httpclient = HttpClients.createDefault();
	private static ObjectMapper mapper = new ObjectMapper();

	static {
		params.put("imei", "863516028676055");
		params.put("access_token", "");
		params.put("sysversion", "5.0");
		params.put("appversion", "1.0");
		params.put("encryptkey", "EFB4DEC65C25431EA16EACD3515E9D91");
	}

	public static String get(String url) {
		try {
			HttpGet get = new HttpGet(url);

			get.setHeader("content-type", "application/json");
			get.setHeader("accept", "application/json");

			long start = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(get);
			long end = System.currentTimeMillis();

			String result = read(response.getEntity().getContent());
			System.out.print("cast::: " + (end - start) + ";			result:::" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String post(String url, Object content, String accessToken) {
		try {
			params.put("access_token", accessToken);
			params.put("data", content);
			HttpPost post = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			builder.addPart("params", new StringBody(mapper.writeValueAsString(params), ContentType.APPLICATION_JSON));//
			post.setEntity(builder.build());

			long start = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(post);
			long end = System.currentTimeMillis();

			String result = read(response.getEntity().getContent());
			System.out.print("cast::: " + (end - start) + ";			result:::" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String post(String url, Object content, String accessToken, List<File> files) {
		try {
			params.put("access_token", accessToken);
			params.put("data", content);
			HttpPost post = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();

			builder.addPart("params", new StringBody(mapper.writeValueAsString(params), ContentType.APPLICATION_JSON));//
			if (files != null) {
				for (File file : files) {
					builder.addPart("file", new FileBody(file));
				}
			}
			post.setEntity(builder.build());

			long start = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(post);
			long end = System.currentTimeMillis();

			String result = read(response.getEntity().getContent());
			System.out.print("cast::: " + (end - start) + ";			result:::" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String post(String url, Object content, List<File> files) {
		try {
			params.put("data", content);
			HttpPost post = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addPart("params", new StringBody(mapper.writeValueAsString(params), ContentType.APPLICATION_JSON));//
			if (files != null) {
				for (File file : files) {
					builder.addPart("file", new FileBody(file));
				}
			}
			post.setEntity(builder.build());

			long start = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(post);
			long end = System.currentTimeMillis();

			String result = read(response.getEntity().getContent());
			System.out.print("cast::: " + (end - start) + ";			result:::" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public static String post(String url, Object content) {
		try {
			params.put("data", content);
			HttpPost post = new HttpPost(url);
			MultipartEntityBuilder builder = MultipartEntityBuilder.create();
			builder.addPart("params", new StringBody(mapper.writeValueAsString(params), ContentType.APPLICATION_JSON));//
			post.setEntity(builder.build());

			long start = System.currentTimeMillis();
			HttpResponse response = httpclient.execute(post);
			long end = System.currentTimeMillis();

			String result = read(response.getEntity().getContent());
			System.out.print("cast::: " + (end - start) + ";			result:::" + result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
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
}
