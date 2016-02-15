package com.cd.bbh.parent.mine.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cd.bbh.common.Constant;
import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.enums.JPushEnum;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.common.utils.FileUploadUtils;
import com.cd.bbh.parent.mine.dao.ChildrenDao;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.vo.ChildStarVO;
import com.cd.bbh.parent.sys.dao.MessageDao;
import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.MessageVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;

@Service
public class ChildrenService {

	@Autowired
	private Constant constant;

	@Autowired
	private ChildrenDao childrenDao;

	@Autowired
	private MessageDao messageDao;

	/**
	 * 根据年龄阶段查询优秀宝贝
	 * 
	 * @param pageable
	 * @param maxage
	 * @param minage
	 * @return
	 */
	public List<ChildStarVO> findStarChildren(Pageable pageable, Integer minage, Integer maxage, String condition) {
		Date minBirthday = DateUtils.getDayOfEndTime(DateUtils.getBirthday(maxage)).getTime();
		Date maxBirthday = DateUtils.getDayOfBeginTime(DateUtils.getBirthday(minage)).getTime();
		return childrenDao.findStarChildren(condition, minBirthday, maxBirthday, pageable.getPage(), pageable.getPagesize());
	}

	/**
	 * 查询自己的孩子
	 * 
	 * @param parentid
	 * @return
	 */
	public List<Child> findMyChildren(Long parentid) {
		return childrenDao.findMyChildren(parentid);
	}

	/**
	 * 移除
	 *
	 * @param parentid
	 * @return
	 */
	public Child findChild(Long id) {
		return childrenDao.search(id);
	}

	/**
	 * 移除
	 *
	 * @param parentid
	 * @return
	 */
	public int deleteChild(Long id) {
		String headimg = childrenDao.search(id).getHeadimg();
		if (StringUtils.isNotBlank(headimg)) {
			File headFile = new File(constant.fileUploadDir + headimg.substring(headimg.indexOf(("head"))));
			if (headFile.exists()) {
				headFile.delete();
			}
		}
		return childrenDao.remove(id);
	}

	/**
	 * 修改小孩信息或更换头像。
	 *
	 * @param id
	 * @param name
	 * @param gender
	 * @param age
	 * @param birthday
	 * @param bloodType
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	public Child updateChildInfo(Long id, String name, String nickName, String gender, Integer age, Date birthday, String bloodType, MultipartFile file, Long parentid) {
		String headimg = (file != null) ? (FileUploadUtils.updateHead(file, childrenDao.search(id).getHeadimg(), id.toString())) : (childrenDao.search(id).getHeadimg());
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		params.put("name", name);
		params.put("nickname", nickName);
		params.put("gender", gender);
		params.put("age", age);
		params.put("birthday", birthday);
		params.put("bloodtype", bloodType);
		params.put("headimg", headimg);
		childrenDao.update(params);
		return new Child(headimg, name, nickName, age, gender, birthday, bloodType, parentid);
	}

	public Child addChild(String name, String nickName, String gender, Integer age, Date birthday, String bloodType, Long parentid, String relation, MultipartFile file) {
		Child child = new Child(FileUploadUtils.uploadHead(file, null), name, nickName, age, gender, birthday, bloodType, parentid);
		childrenDao.add(child);
		childrenDao.addParentChild(child.getId(), parentid, relation);
		return child;
	}

	public int addParentChildRel(Long childid, Long elderid, String relation) {
		return childrenDao.addParentChild(childid, elderid, relation);
	}

	public List<Map<String, Object>> findElders(Long childid) {
		return childrenDao.selectElders(childid);
	}

	public void createInvation(Long childid, Long userId, String relation, String username, Long creatorId) {
		Child child = childrenDao.search(childid);
		List<MessageUserVO> messageUsers = messageDao.selectMessageUserByUserid(userId);
		if (messageUsers == null || messageUsers.size() <= 0) {
			return;
		}
		List<String> regids = new ArrayList<String>();
		for (MessageUserVO messageUserVO : messageUsers) {
			regids.add(messageUserVO.getRegid());
		}

		String title = String.format(constant.invitation, username, StringUtils.isBlank(child.getNickname()) ? child.getName() : child.getNickname());
		String content = String.format(constant.invitation, username, StringUtils.isBlank(child.getNickname()) ? child.getName() : child.getNickname());
		MessageVO message = new MessageVO(title, content, "INVITATION", creatorId, "Y", relation, "N", childid);

		// 插入消息数据库
		messageDao.insertMessage(message);

		// 插入消息状态数据库
		messageDao.insertMessageStatus(message);

		// 准备推送信息
		NotificationVO notification = new NotificationVO(title, message.getMessageId(), "N", "INVITATION", JPushEnum.PERSON.siginal(), //
				regids.toString().substring(1, regids.toString().length() - 1), creatorId);
		messageDao.insertNotification(notification);
	}
}