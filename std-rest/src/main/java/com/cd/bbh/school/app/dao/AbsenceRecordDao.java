package com.cd.bbh.school.app.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.app.vo.AbsenceRecord;
import com.cd.bbh.school.app.vo.AbsenceRecordVO;

public interface AbsenceRecordDao {

	List<AbsenceRecordVO> selectAbsenceRecordList(@Param("search") Map<String, Object> search);
	
	String selectApplicantTypeById(@Param("id") long id);
	
	int insertAbsenceRecord(@Param("ars") List<AbsenceRecord> ars);
	
	int updateAbsenceRecord(@Param("data") Map<String, Object> data);
	
}
