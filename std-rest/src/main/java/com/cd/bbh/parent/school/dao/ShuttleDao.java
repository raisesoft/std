package com.cd.bbh.parent.school.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.Shuttle;

@Repository
public interface ShuttleDao {

	/**
	 * 根据查询打卡记录
	 * 
	 * @param time
	 * @param child
	 * @param start
	 * @param dataNum
	 * @return
	 */
	List<Shuttle> searchShuttles(@Param("shuttleTime") Date time, //
			@Param("child") Long child, //
			@Param("start") int start, //
			@Param("dataNum") int dataNum);

	List<Map<String, Object>> searchElders(Long params);
	
	/**
	 * 验证是打卡到学校还是打卡离开
	 * @return
	 */
	Long searchIsShuttle(@Param("data") Map<String, Object> data);

	/**
	 * 插入打卡记录
	 * 
	 * @param cardNo
	 * @param child
	 * @param punchSerial
	 * @param headImg
	 * @param remark
	 * @param headImgKey
	 * @return
	 */
	int insertShuttle(@Param("data") Map<String, Object> data);
	
	/**
	 * 更新打卡记录
	 * @param data
	 * @return
	 */
	int updateShuttle(@Param("id") long id);
}
