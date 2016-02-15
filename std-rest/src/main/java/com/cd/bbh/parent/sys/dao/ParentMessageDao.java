package com.cd.bbh.parent.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.parent.sys.vo.ParentMessage;

public interface ParentMessageDao {

	/**
	 * 家长端   获取消息列表
	 * @param search
	 * @return
	 */
	List<ParentMessage> selectMessage(@Param("search") Map<String, Object> search);
	
	/**
	 *更新消息状态
	 * @param id
	 * @param status
	 * @return
	 */
	int updateMessageStatus(@Param("id") Long id, @Param("status") String status);
	
}
