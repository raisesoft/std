package com.cd.bbh.parent.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.MessageVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;

@Repository
public interface MessageDao {

	/**
	 * 根据用户id查询推送用户信息
	 *
	 * @param userid
	 * @return
	 */
	List<MessageUserVO> selectMessageUserByUserid(Long userid);

	/**
	 * 根据用户极光推送标识和区域类型，查询推送用户信息
	 *
	 * @param regid
	 * @param groupType
	 * @return
	 */
	MessageUserVO selectMessageUser(@Param("regid") String regid, @Param("groupType") String groupType);

	/**
	 * 插入推送用户信息， 同时插入分组
	 *
	 * @param params
	 * @return
	 */
	int insertMessageUser(@Param("cityname") String cityname, @Param("regid") String regid, @Param("stat") String stat, @Param("userid") Long userid);

	/**
	 * 插入推送用户信息， 同时插入分组
	 *
	 * @param params
	 * @return
	 */
	int updateMessageUser(@Param("regid") String regid, @Param("userid") Long userid);

	/**
	 * 插入推送用户信息， 同时插入分组
	 *
	 * @param params
	 * @return
	 */
	int insertMessageTagUser(@Param("cityname") String cityname, @Param("regid") String regid, @Param("stat") String stat);

	/**
	 * 插入推送用户信息， 同时插入分组
	 *
	 * @param params
	 * @return
	 */
	int insertMessageTagUsers(List<Map<String, Object>> params);

	/**
	 * 插入推送TAG
	 *
	 * @param params
	 * @return
	 */
	int insertMessageTag(List<Map<String, Object>> params);

	/**
	 * 根据用户注册id，删除推送用户
	 *
	 * @param regid
	 * @return
	 */
	int deleteMessageUserTagByRegid(String regid);

	/**
	 * 根据用户TAG，删除推送用户
	 *
	 * @param regid
	 * @return
	 */
	int deleteMessageUserTagByid(Long userTagid);

	/**
	 * 根据用户注册id，删除推送用户
	 * 
	 * @param regid
	 * @return
	 */
	int deleteMessageUserByRegid(String regid);

	/**
	 * 检查该认证用户是否已经进入对应分组
	 * 
	 * @param userid
	 * @param groupType
	 * @return
	 */
	List<Map<String, Object>> selectNotGroupMessageUserByTypeAndUserid(@Param("userid") Long userid, @Param("regid") String regid, @Param("groupType") String groupType);

	/**
	 * 获取当前为分组账户的信息（学校）
	 * 
	 * @return
	 */
	Map<String, Object> selectNotSyncSchoolTypeMessageUser();

	/**
	 * 获取当前为分组账户的信息（班级）
	 * 
	 * @return
	 */
	Map<String, Object> selectNotSyncClassTypeMessageUser();

	/**
	 * 根据小孩ID获取关联长辈的极光推送号
	 * 
	 * @param childid
	 * @return
	 */
	List<MessageUserVO> selectMessageUserByChild(Long childid);

	/**
	 * 插入待发送消息
	 * 
	 * @param advice
	 * @return
	 */
	int insertMessage(MessageVO message);
	
	/**
	 * 插入待发送消息
	 * 
	 * @param advice
	 * @return
	 */
	int insertMessageStatus(MessageVO message);

	/**
	 * 插入待发送消息
	 * 
	 * @param notification
	 * @return
	 */
	int insertNotification(NotificationVO notification);
}
