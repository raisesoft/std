package com.cd.bbh.parent.sys.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.enums.JPushEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.parent.login.dao.LoginDao;
import com.cd.bbh.parent.sys.dao.MessageDao;
import com.cd.bbh.parent.sys.dao.ParentMessageDao;
import com.cd.bbh.parent.sys.vo.MessageTagVO;
import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.MessageVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;
import com.cd.bbh.parent.sys.vo.ParentMessage;

@Service
public class SysMessageService {

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private ParentMessageDao pmd;

	@Autowired
	private LoginDao loginDao;

	public List<MessageUserVO> searchMessageUser(Long userid) {
		return messageDao.selectMessageUserByUserid(userid);
	}

	public int createNotification(NotificationVO notification) {
		return messageDao.insertNotification(notification);
	}

	/**
	 * 更新推送用户信息和所在推送组(区域组，学校组，班级组)
	 * 
	 * @param regid
	 * @param cityName
	 *            市
	 * @param imei
	 * @param phone
	 * @param stat
	 * @param userid
	 */
	public void updateMessageUser(String regid, String cityName, String imei, String phone, String stat, Long userid) {
		processAreaTag(regid, cityName, stat, userid);
		if (RoleEnum.PAUTH.siginal().equals(stat) && userid != null) {
			processSchoolTag(regid, stat, userid);
			processClassTag(regid, stat, userid);
		}
	}

	/**
	 * 根据小孩ID获取关联长辈的极光推送号
	 * 
	 * @param childid
	 * @return
	 */
	public List<MessageUserVO> searchMessageUserByChild(Long childid) {
		return messageDao.selectMessageUserByChild(childid);
	}

	/**
	 * 插入推送内容
	 * 
	 * @param childid
	 * @return
	 */
	public int createMessage(MessageVO message) {
		return messageDao.insertMessage(message);
	}

	/**
	 * <STRONG>添加极光推送ID到数据库</STRONG> <br/>
	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;查询该设备所使用的极光ID是否存在，若不存在则加入数据库，
	 * 并添加到对应区域组；若存在，则查看对应区域组是否存在，若不存在则插入区域组，若存在区域组则做是否更新操作
	 * 
	 * @param cityName
	 * @param stat
	 */
	private void processAreaTag(String regid, String cityName, String stat, Long userid) {
		MessageUserVO messageUser = messageDao.selectMessageUser(regid, JPushEnum.AREA.siginal());
		if (messageUser == null) {
			messageDao.insertMessageUser(cityName, regid, stat, userid);
			return;
		}
		if (messageUser.getUserid() == null) {
			messageUser.setUserid(userid);
			messageDao.updateMessageUser(regid, userid);
		}

		List<MessageTagVO> userTags = messageUser.getTags();
		if (userTags == null || userTags.size() == 0) {
			messageDao.insertMessageTagUser(cityName, regid, stat);
		} else {
			boolean hasCurrentCityTags = false;
			for (MessageTagVO messageTagVO : userTags) {
				if (StringUtils.isNotBlank(messageTagVO.getRemark()) && messageTagVO.getRemark().equals(cityName)) {
					hasCurrentCityTags = true;
				} else {
					messageDao.deleteMessageUserTagByid(messageTagVO.getUserTagid());
				}
			}
			if (!hasCurrentCityTags) {
				messageDao.insertMessageTagUser(cityName, regid, stat);
			}
		}
	}

	private void processSchoolTag(String regid, String stat, Long userid) {
		List<Map<String, Object>> resultRows = messageDao.selectNotGroupMessageUserByTypeAndUserid(userid, regid, JPushEnum.SCHOOL.siginal());
		List<Map<String, Object>> needToAddTag = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> needToAdd = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> resultRow : resultRows) {
			Object groupName = resultRow.get("groupName");
			if (groupName == null || StringUtils.isBlank(groupName.toString())) {
				groupName = String.format("SCHOOL_%d_%d", resultRow.get("agentid"), resultRow.get("schoolid"));
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("groupName", groupName);
				row.put("groupType", "SCHOOL");
				row.put("remark", resultRow.get("schoolName"));
				needToAddTag.add(row);
			}

			if (resultRow.get("group") == null) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("groupName", groupName);
				row.put("regid", regid);
				row.put("stat", stat);
				needToAdd.add(row);
			}
		}
		if (needToAddTag.size() > 0) {
			messageDao.insertMessageTag(needToAddTag);
		}

		if (needToAdd.size() > 0) {
			messageDao.insertMessageTagUsers(needToAdd);
		}
	}

	private void processClassTag(String regid, String stat, Long userid) {
		List<Map<String, Object>> resultRows = messageDao.selectNotGroupMessageUserByTypeAndUserid(userid, regid, JPushEnum.CLASS.siginal());
		List<Map<String, Object>> needToAddTag = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> needToAdd = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> resultRow : resultRows) {
			Object groupName = resultRow.get("groupName");
			if (groupName == null || StringUtils.isBlank(groupName.toString())) {
				groupName = String.format("CLASS_%d_%d_%d", resultRow.get("agentid"), resultRow.get("schoolid"), resultRow.get("classid"));
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("groupName", groupName);
				row.put("groupType", "CLASS");
				row.put("remark", resultRow.get("className"));
				needToAddTag.add(row);
			}

			if (resultRow.get("group") == null) {
				Map<String, Object> row = new HashMap<String, Object>();
				row.put("groupName", groupName);
				row.put("regid", regid);
				row.put("stat", stat);
				needToAdd.add(row);
			}
		}
		if (needToAddTag.size() > 0) {
			messageDao.insertMessageTag(needToAddTag);
		}

		if (needToAdd.size() > 0) {
			messageDao.insertMessageTagUsers(needToAdd);
		}
	}

	/**
	 * 消息中心数据获取
	 * 
	 * @param data
	 * @return
	 */
	public List<ParentMessage> getMessage(Map<String, Object> data) {
		int num = (int) data.get("pageSize");
		int page = (int) data.get("page");
		if (num > 0) {
			int start = num * (page - 1);
			data.put("start", start);
		}

		return pmd.selectMessage(data);
	}

	/**
	 * 消息中心更新状态
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public boolean updateMessageStatus(Long id, String status) {
		boolean flag = false;
		if (pmd.updateMessageStatus(id, status) > 0) {
			flag = true;
		}
		return flag;
	}
}
