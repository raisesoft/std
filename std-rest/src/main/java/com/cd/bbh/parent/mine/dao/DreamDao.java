package com.cd.bbh.parent.mine.dao;

import java.util.List;
import java.util.Map;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.Dream;

/**
 * 
 * @author aowin
 *
 */
public interface DreamDao extends BaseDao<Dream> {

	List<Dream> searchDreams(Long childid);

	int add(Map<String, Object> params);

	int update(Map<String, Object> params);

	List<Dream> searchDreamHistory(Long childid);
}