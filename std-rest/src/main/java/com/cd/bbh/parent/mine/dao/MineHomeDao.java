package com.cd.bbh.parent.mine.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.MineHome;

@Repository
public interface MineHomeDao extends BaseDao<MineHome> {

	List<MineHome> searchByParentId(Long parentid);
}