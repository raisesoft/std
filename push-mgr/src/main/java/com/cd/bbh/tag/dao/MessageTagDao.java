package com.cd.bbh.tag.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.tag.vo.Message;
import com.cd.bbh.tag.vo.MessageTag;
import com.cd.bbh.tag.vo.MessageUserTag;

public interface MessageTagDao {
	List<MessageTag> selectMessageTag();

	List<MessageUserTag> selectMessageUserTag();

	int deleteMessageUserTag(List<Long> ids);

	int updateMessageUserTag(List<MessageUserTag> userTags);

	List<Message> selectMessageByStat(String stat);

	int updateMessage(@Param("id") Long id, @Param("state") String state);
}
