package com.cd.bbh.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;

@Component
public class FileUploadUtils {

	private static Constant configuration;

	@Autowired(required = true)
	public void setConfiguration(Constant configuration) {
		FileUploadUtils.configuration = configuration;
	}

	/**
	 * 上传文件，返回文件访问地址
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static String uploadFile(MultipartFile file, String dateStr, String userid) {
		try {
			if (file == null) {
				return null;
			}
			checkFileType(file);
			File headDir = new File(configuration.fileUploadDir + dateStr + "/" + userid);
			if (!headDir.exists()) {
				headDir.mkdirs();
			}

			String original = file.getOriginalFilename();
			String newname = UUID.randomUUID().toString().replace("-", "") + original.substring(original.indexOf("."));
			String movedFilePath = configuration.fileUploadDir + dateStr + "/" + userid + "/" + newname;
			file.transferTo(new File(movedFilePath));
			if (checkFileType(file).equals("A")) {
				VedioCutUtil.executeCut(movedFilePath, movedFilePath.substring(0, movedFilePath.lastIndexOf(".")) + ".png");
			}
			return configuration.fileServerAddr + dateStr + "/" + userid + "/" + newname;
		} catch (IllegalStateException | IOException ex) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR, ex);
		}
	}

	/**
	 * 上传头像，返回文件访问地址
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public static String uploadHead(MultipartFile file, String userid) {
		try {
			if (file == null) {
				return null;
			}
			checkFileType(file);
			File headDir = new File(configuration.fileUploadDir + "heads/");
			if (!headDir.exists()) {
				headDir.mkdirs();
			}

			String original = file.getOriginalFilename();
			String newname = UUID.randomUUID().toString().replace("-", "") + original.substring(original.indexOf("."));

			file.transferTo(new File(configuration.fileUploadDir + "heads/" + newname));

			return configuration.fileServerAddr + "heads/" + newname;
		} catch (IllegalStateException | IOException ex) {
			throw new ApplicationException(ResultEnum.OPERATION_ERROR, ex);
		}
	}

	/**
	 * 更新上传头像，返回文件访问地址
	 * 
	 * @param file
	 * @return
	 */
	public static String updateHead(MultipartFile file, String origianlPath, String userid) {
		if (file == null) {
			return origianlPath;
		}
		checkFileType(file);
		File headDir = new File(configuration.fileUploadDir + "heads/");
		if (!headDir.exists()) {
			headDir.mkdirs();
		}
		if (StringUtils.isBlank(origianlPath)) {
			return uploadHead(file, userid);
		} else {
			new File(configuration.fileUploadDir + origianlPath.substring(origianlPath.lastIndexOf("heads"))).delete();
			return uploadHead(file, userid);
		}
	}

	/**
	 * 检查文件类型。具体类型请参考配置文件
	 * 
	 * @param file
	 */
	public static String checkFileType(MultipartFile file) {

		if (file == null) {
			throw new ApplicationException(ResultEnum.FILE_TYPE_ERROR.code(), //
					String.format(ResultEnum.FILE_TYPE_ERROR.desc(), //
							configuration.pictureType + "," + configuration.vedioType), null);
		}
		if (!file.getOriginalFilename().contains(".")) {
			throw new ApplicationException(ResultEnum.FILE_TYPE_ERROR.code(), //
					String.format(ResultEnum.FILE_TYPE_ERROR.desc(), //
							configuration.pictureType + "," + configuration.vedioType), null);
		}

		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));

		if (!configuration.pictureType.contains(suffix) && !configuration.vedioType.contains(suffix)) {
			throw new ApplicationException(ResultEnum.FILE_TYPE_ERROR.code(), //
					String.format(ResultEnum.FILE_TYPE_ERROR.desc(), //
							configuration.pictureType + "," + configuration.vedioType), null);
		}

		if (configuration.pictureType.contains(suffix)) {
			return "I";
		} else if (configuration.vedioType.contains(suffix)) {
			return "A";
		} else {
			throw new ApplicationException(ResultEnum.FILE_TYPE_ERROR.code(), //
					String.format(ResultEnum.FILE_TYPE_ERROR.desc(), //
							configuration.pictureType + "," + configuration.vedioType), null);
		}
	}
}
