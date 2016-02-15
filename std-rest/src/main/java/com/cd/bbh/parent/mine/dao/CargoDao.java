package com.cd.bbh.parent.mine.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.Cargo;

public interface CargoDao extends BaseDao<Cargo> {
	List<Cargo> searchAll(@Param("category") String category, @Param("page") int page, @Param("pagesize") int pagesize);

	Map<String, Object> searchCargoByDreamId(Long childid);

	int updateCargoAmount(Map<String, Object> params);
}