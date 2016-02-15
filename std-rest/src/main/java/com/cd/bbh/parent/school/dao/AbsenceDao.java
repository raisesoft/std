package com.cd.bbh.parent.school.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cd.bbh.parent.school.vo.AbsenceVO;

@Repository
public interface AbsenceDao {

	public int insertAbsence(AbsenceVO absence);

	public List<AbsenceVO> selectAbsences(@Param("childid") Long childid, @Param("absenceDate") Date absenceDate);

	public Map<String, Long> selectClassAndSchoolByPupil(Long childid);

	public List<Map<String, Object>> selectTeachers(Long childid);
}
