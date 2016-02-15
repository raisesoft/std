package com.cd.bbh.parent.mine.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.utils.FileUploadUtils;
import com.cd.bbh.parent.mine.dao.UserDao;

@Service
public class UserService {

	@Autowired
	private Constant configuration;

	@Autowired
	private UserDao userDao;

	public User find(Long userid) {
		return userDao.search(userid);
	}

	/**
	 * 获取父母类型(认证，创建，删除，禁用)
	 *
	 * @param deviceNumber
	 * @return
	 */
	public String findParentType(String deviceNumber) {
		return userDao.searchParentType(deviceNumber);
	}

	/**
	 * 查看用户是否添加详细信息
	 *
	 * @param userid
	 * @param usertype
	 * @return
	 */
	public Map<String, Object> findDetailCount(Long userid, Long usertype) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userid);
		params.put("roleid", usertype);
		return userDao.checkUserDetail(params);
	}

	public Personal findUserDetail(Long userid, Long usertype) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", userid);
		params.put("roleid", usertype);
		return userDao.searchUserDetail(params);
	}

	/**
	 *
	 * 更新用户信息。当用户已经有头像，则删除原来头像。
	 *
	 * @param personal
	 * @param file
	 * @return
	 * @throws IOException
	 * @throws IllegalStateException
	 */
	public int updateUserDetail(Personal personal, MultipartFile file) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", personal.getUserid());
		params.put("name", personal.getUsername());
		params.put("deviceno", personal.getDevice());
		params.put("idtype", null);
		params.put("idno", null);
		params.put("stat", Constant.PARENT_STAT_REGIST);
		params.put("gender", personal.getGender());
		params.put("age", personal.getAge());
		params.put("birthday", personal.getBirthday());
		params.put("roleid", personal.getRoleid());
		params.put("exist", personal.getExist());
		params.put("headimg", FileUploadUtils.updateHead(file, personal.getHeadimg(), personal.getUserid() + ""));
		return userDao.updateUserDetail(params);
	}

	public int checkHasParentRole(Long userid) {
		return userDao.checkHasParentRole(userid);
	}
}
