package com.cd.bbh.school.punch.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cd.bbh.school.punch.vo.ClassVO;
import com.cd.bbh.school.punch.vo.ParentVO;
import com.cd.bbh.school.punch.vo.PupilVO;
import com.cd.bbh.school.punch.vo.RelationVO;
import com.cd.bbh.school.punch.vo.TeacherVO;

public interface SchoolSyncDao {
	public List<ClassVO> selectClass(@Param("punch") String punchSerial, @Param("syncDate") Date syncDate);

	public List<TeacherVO> selectTeacher(@Param("punch") String punchSerial, @Param("syncDate") Date syncDate);

	public List<PupilVO> selectPupil(@Param("punch") String punchSerial, @Param("syncDate") Date syncDate);

	public List<ParentVO> selectParent(@Param("punch") String punchSerial, @Param("syncDate") Date syncDate);

	public List<RelationVO> selectRelation(@Param("punch") String punchSerial, @Param("syncDate") Date syncDate);

	public Long selectSchoolByPunch(String punchSerial);
}
