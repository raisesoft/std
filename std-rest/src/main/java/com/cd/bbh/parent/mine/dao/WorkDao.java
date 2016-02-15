package com.cd.bbh.parent.mine.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.ChildWork;
import com.cd.bbh.parent.mine.model.Work;

@Repository
public interface WorkDao extends BaseDao<Work> {

	Work searchWork(Long workid);

	List<Work> searchAllWork(@Param("page") Integer page, @Param("pagesize") Integer pagesize);

	Integer searchUndoWorkCount(Long childid);

	List<Work> searchUndoWork(@Param("childid") Long childid, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	List<ChildWork> searchWorkHistory(@Param("childid") Long childid, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	int insertChildWork(List<Map<String, Object>> params);

	int updateChildWork(Map<String, Object> params);

	int removeChildWork(Long workid);
}