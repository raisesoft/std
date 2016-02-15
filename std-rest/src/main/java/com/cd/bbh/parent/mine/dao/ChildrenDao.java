package com.cd.bbh.parent.mine.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.vo.ChildStarVO;

public interface ChildrenDao extends BaseDao<Child> {
	List<Child> findMyChildren(Long parentid);

	int add(Map<String, Object> params);

	int update(Map<String, Object> params);

	int updateByRealizeDream(Child child);

	int addParentChild(@Param("childid") Long childid, @Param("elderid") Long elderid, @Param("relation") String relation);

	int checkHasParentRole(Long parentid);

	List<ChildStarVO> findStarChildren(@Param("condition") String condition, @Param("minBirthday") Date minBirthday, //
			@Param("maxBirthday") Date maxBirthday, @Param("page") int page, @Param("pagesize") int pagesize);

	String selectRelation(@Param("childid") Long childid, @Param("parentid") Long parentid);

	List<Map<String, Object>> selectElders(@Param("childid") Long childid);
}