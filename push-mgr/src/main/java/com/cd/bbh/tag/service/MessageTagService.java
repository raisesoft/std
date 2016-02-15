package com.cd.bbh.tag.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.tag.dao.MessageTagDao;
import com.cd.bbh.tag.vo.Message;
import com.cd.bbh.tag.vo.MessageTag;
import com.cd.bbh.tag.vo.MessageUserTag;

@Service
public class MessageTagService {
	@Autowired
	private MessageTagDao messageTagDao;

	public List<MessageTag> searchMessageTag() {
		return messageTagDao.selectMessageTag();
	}

	public List<MessageUserTag> searchMessageUserTag() {
		return messageTagDao.selectMessageUserTag();
	}

	public int updateMessageUserTag(List<MessageUserTag> userTags) {
		return messageTagDao.updateMessageUserTag(userTags);
	}

	public int deleteMessageUserTag(List<Long> ids) {
		return messageTagDao.deleteMessageUserTag(ids);
	}

	public List<Message> getMessageByStat(String stat) {
		return messageTagDao.selectMessageByStat(stat);
	}

	public Integer updateMessageById(Long id, String state) {
		return messageTagDao.updateMessage(id, state);
	}
}
