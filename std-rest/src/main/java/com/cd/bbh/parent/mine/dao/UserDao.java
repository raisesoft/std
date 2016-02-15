package com.cd.bbh.parent.mine.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.Personal;
import com.cd.bbh.parent.mine.model.User;

@Repository
public interface UserDao extends BaseDao<User> {

	int addBaseUserInfo(Map<String, Object> params);

	/**
	 * 根据用手机号或者email查找用户
	 * 
	 * @param column
	 * @return
	 */
	User searchByUnique(String column);

	int updatePassword(Map<String, Object> params);

	/**
	 * 获取父母类型(认证，创建，删除，禁用)
	 * 
	 * @param deviceNumber
	 * @return
	 */
	String searchParentType(String deviceNumber);

	/**
	 * 根据用户类别和id，查看用户是否存在详细信息,并返回用户头像地址和
	 * 
	 * @param params
	 * @return
	 * 
	 */
	Map<String, Object> checkUserDetail(Map<String, Object> params);

	/**
	 * 根据用户类别和id，查看用户详细信息
	 * 
	 * @param params
	 * @return
	 */
	Personal searchUserDetail(Map<String, Object> params);

	/**
	 * 更新用户信息（用户详细信息存在则更新，不存在则创建）
	 * 
	 * @param params
	 * @return
	 */
	int updateUserDetail(Map<String, Object> params);

	int checkHasParentRole(Long userid);

}