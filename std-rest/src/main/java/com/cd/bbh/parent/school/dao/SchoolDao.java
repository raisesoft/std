package com.cd.bbh.parent.school.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.SchoolVO;

@Repository
public interface SchoolDao {
	SchoolVO selectSchool(Long id);

	List<SchoolVO> selectStarSchool(@Param("cityname") String cityname, @Param("longtitude") Float longtitude, @Param("latitude") Float latitude, //
			@Param("page") Integer page, @Param("pagesize") Integer pagesize);

	List<SchoolVO> selectSchoolByName(@Param("cityname") String cityname, @Param("longtitude") Float longtitude, @Param("latitude") Float latitude, //
			@Param("condition") String condition, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

}