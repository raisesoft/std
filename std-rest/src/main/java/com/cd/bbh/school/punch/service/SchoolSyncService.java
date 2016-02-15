package com.cd.bbh.school.punch.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.school.punch.dao.SchoolSyncDao;
import com.cd.bbh.school.punch.vo.ClassVO;
import com.cd.bbh.school.punch.vo.TeacherVO;

@Service(value = "syncService")
public class SchoolSyncService {

	@Autowired
	private SchoolSyncDao schoolSyncDao;

	public List<ClassVO> searchClasses(String punchSerial, Date syncDate) {
		return schoolSyncDao.selectClass(punchSerial, syncDate);
	}

	public List<TeacherVO> searchTeacher(String punchSerial, Date syncDate) {
		return schoolSyncDao.selectTeacher(punchSerial, syncDate);
	}

	public Map<String, Object> syncStudents(String punchSerial, Date syncDate) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("pupils", schoolSyncDao.selectPupil(punchSerial, syncDate));
		result.put("relation", schoolSyncDao.selectRelation(punchSerial, syncDate));
		result.put("parents", schoolSyncDao.selectParent(punchSerial, syncDate));
		return result;
	}
}
