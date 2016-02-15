package com.cd.bbh.parent.mine.dao;

import java.util.List;
import java.util.Map;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.ChildInfo;
import com.cd.bbh.parent.mine.model.ChildRecord;
import com.cd.bbh.parent.mine.model.ChildWork;

public interface ChildRecordDao extends BaseDao<ChildRecord> {

	/**
	 * 
	 * 获取小孩详细信息（身高，体重）
	 * 
	 * @param params
	 * @return
	 */
	public List<ChildInfo> searchInfo(Map<String, Object> params);

	/**
	 * 获取小孩工作记录
	 * 
	 * @param params
	 * @return
	 */
	public List<ChildWork> searchWork(Map<String, Object> params);

	/**
	 * 
	 * 获取小孩详细信息（身高，体重）
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> searchInfoCounts(Map<String, Object> params);

	/**
	 * 获取小孩工作记录
	 * 
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> searchWorkCounts(Map<String, Object> params);
}