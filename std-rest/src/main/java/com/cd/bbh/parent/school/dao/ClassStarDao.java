package com.cd.bbh.parent.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.parent.mine.vo.ChildStarVO;

public interface ClassStarDao {

	ChildStarVO searchMyChildWeekStar(Long childid);

	ChildStarVO searchMyChildMonthStar(Long childid);

	List<ChildStarVO> searchClassWeekStars(@Param("childid") Long childid, @Param("page") Integer start, @Param("pagesize") Integer end);

	List<ChildStarVO> searchClassMonthStars(@Param("childid") Long childid, @Param("page") Integer start, @Param("pagesize") Integer end);

	List<ChildStarVO> searchTeacherClassWeekStars(@Param("teacherid") Long teacherid, @Param("page") Integer start, @Param("pagesize") Integer end);

	List<ChildStarVO> searchTeacherClassMonthStars(@Param("teacherid") Long teacherid, @Param("page") Integer start, @Param("pagesize") Integer end);
}