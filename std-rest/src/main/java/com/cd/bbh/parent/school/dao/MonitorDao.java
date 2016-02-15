package com.cd.bbh.parent.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.MonitorVO;

@Repository
public interface MonitorDao {

	List<MonitorVO> searchCameraInfo(@Param("child") Long child, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

	Map<String, String> searchCamera(Map<String, Object> params);

	int insertCameraRel(List<Map<String, Object>> params);
}
