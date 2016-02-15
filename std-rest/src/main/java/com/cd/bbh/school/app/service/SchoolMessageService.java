package com.cd.bbh.school.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.school.app.dao.SchoolMessageDao;
import com.cd.bbh.school.app.dao.SchoolTeacherClassDao;
import com.cd.bbh.school.app.vo.Message;

/**
 * 
 * @author weijing
 *
 */
@Service
public class SchoolMessageService {

	@Autowired
	private SchoolTeacherClassDao stcd;
	@Autowired
	private SchoolMessageDao smd;

	/**
	 * 消息等级列表
	 * 
	 * @return
	 */
	public List<String> getMessageLevelList() {
		List<String> levels = new ArrayList<>();
		levels.add("A");
		levels.add("B");
		levels.add("C");

		return levels;
	}

	/**
	 * 发送通知
	 * 
	 * @param msg
	 * @return
	 */
	public void sendMsg(Message msg, String roles, String sendType) {

		Long schoolId = null, agentId = null;
		List<String> pushList = null;

		if (roles.equals("S")) { // 校长入口
			// 按校长查学校(学校ID=校长ID)
			schoolId = msg.getCreateUser();
			if (sendType.equals("C")) { // 按班级发送
				String[] classIdArray = msg.getPushTags().split(",");
				pushList = Arrays.asList(classIdArray);
			}
		} else if (roles.equals("T")) { // 教师入口
			schoolId = stcd.selectSchoolIdByTeacher(msg.getCreateUser());
			String[] classIdArray = msg.getPushTags().split(",");
			pushList = Arrays.asList(classIdArray);
		}
		agentId = stcd.selectAgentIdBySchool(schoolId);
		StringBuilder pushTags = new StringBuilder();
		// 按班级发送的push
		if (sendType.equals("C")) {
			for (String classId : pushList) {
				pushTags.append(msg.getType());
				pushTags.append("_");
				pushTags.append(agentId);
				pushTags.append("_");
				pushTags.append(schoolId);
				pushTags.append("_");
				pushTags.append(classId);
				pushTags.append(",");
			}
			// 去掉最后一个“，”
			pushTags.deleteCharAt(pushTags.length() - 1);
		} else if (sendType.equals("S")) {
			pushTags.append(msg.getType());
			pushTags.append("_");
			pushTags.append(agentId);
			pushTags.append("_");
			pushTags.append(schoolId);
		}
		msg.setPushTags(pushTags.toString());
		// 添加一条数据
		smd.insertMsgByTeacher(msg);
	}
}
