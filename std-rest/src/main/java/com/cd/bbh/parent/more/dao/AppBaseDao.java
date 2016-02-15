package com.cd.bbh.parent.more.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AppBaseDao {

	List<Map<String, String>> searchAppVersion(Map<String, String> params);
}
