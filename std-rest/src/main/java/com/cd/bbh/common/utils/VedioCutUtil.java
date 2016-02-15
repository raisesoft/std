package com.cd.bbh.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cd.bbh.common.Constant;

@Component
public class VedioCutUtil {

	private static Constant configuration;

	@Autowired(required = true)
	public void setConfiguration(Constant configuration) {
		VedioCutUtil.configuration = configuration;
	}

	public static String videoResolution(String vedioPath) throws IOException {
		List<String> params = new ArrayList<String>();
		params.add(configuration.ffmpegPath);
		params.add("-i");
		params.add(vedioPath);

		ProcessBuilder builder = new ProcessBuilder();
		builder.command(params);
		builder.redirectErrorStream(true);
		BufferedReader br = new BufferedReader(new InputStreamReader(builder.start().getInputStream()));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = br.readLine()) != null) {
			buffer.append(line);
		}
		br.close();
		Pattern pattern = Pattern.compile("(\\d*)x(\\d*), (\\d*) kb\\/s");
		Matcher matcher = pattern.matcher(buffer);
		if (matcher.find()) {
			return matcher.group(2) + "*" + (matcher.group(1));
		}
		return configuration.resolution;
	}

	/**
	 * 视频截图
	 * 
	 * @param vedioPath
	 *            要截图的视频源文件
	 * @param picturePath
	 *            截图保存路径
	 * @return
	 * @throws IOException
	 */
	public static void executeCut(String vedioPath, String picturePath) throws IOException {
		ProcessBuilder builder = new ProcessBuilder();
		builder.command(buildParams(videoResolution(vedioPath), vedioPath, picturePath));
		builder.redirectErrorStream(true);
		builder.start();
	}

	private static List<String> buildParams(String resolution, String vedioPath, String picturePath) {
		List<String> cutpic = new ArrayList<String>();
		cutpic.add(configuration.ffmpegPath);
		cutpic.add("-i");
		cutpic.add(vedioPath);
		cutpic.add("-y");
		cutpic.add("-f");
		cutpic.add("image2");
		cutpic.add("-ss"); // 添加参数＂-ss＂，该参数指定截取的起始时间
		cutpic.add("0"); // 添加起始时间为第1秒
		cutpic.add("-vframes");
		cutpic.add("1");
		cutpic.add("-s"); // 添加参数＂-s＂，该参数指定截取的图片大小
		cutpic.add(resolution);
		cutpic.add(picturePath); // 添加截取的图片的保存路径
		return cutpic;
	}
}
