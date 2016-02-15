package com.cd.bbh.parent.mine.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cd.bbh.common.dao.BaseDao;
import com.cd.bbh.parent.mine.model.ChildSitter;
import com.cd.bbh.parent.mine.model.Device;

@Repository
public interface ChildSitterDao extends BaseDao<ChildSitter> {

	List<ChildSitter> searchSitters(Long parentid);

	List<Device> searchAllDevice();

	Device searchDevice(Long deviceid);

	int addChildDevice(Map<String, Object> params);

	int bindChildDevice(Map<String, Object> params);
}
