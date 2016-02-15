package com.cd.bbh.parent.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.sys.vo.DeviceVO;

@Repository
public interface DeviceDao {
	List<DeviceVO> selectDevices(@Param("page") Integer page, @Param("pagesize") Integer pagesize);
}
