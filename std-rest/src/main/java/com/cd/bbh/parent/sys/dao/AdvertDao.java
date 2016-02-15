package com.cd.bbh.parent.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.parent.sys.vo.AdvertVO;

public interface AdvertDao {

	AdvertVO selectAdvert(Long id);

	List<AdvertVO> selectAdverts(@Param("cityname") String cityname, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	List<AdvertVO> selectAdvertsByName(@Param("cityname") String cityname, @Param("name") String name, @Param("page") Integer page, @Param("pagesize") Integer pagesize);
}