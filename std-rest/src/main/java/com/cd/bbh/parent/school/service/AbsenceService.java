package com.cd.bbh.parent.school.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.parent.school.dao.AbsenceDao;
import com.cd.bbh.parent.school.vo.AbsenceStatus;
import com.cd.bbh.parent.school.vo.AbsenceType;
import com.cd.bbh.parent.school.vo.AbsenceVO;
import com.cd.bbh.parent.sys.dao.MessageDao;
import com.cd.bbh.parent.sys.service.SysMessageService;
import com.cd.bbh.parent.sys.vo.MessageUserVO;

@Service
public class AbsenceService {

	@Autowired
	private AbsenceDao absenceDao;

	@Autowired
	private MessageDao messageDao;

	@Autowired
	private SysMessageService sysMessageService;

	public int createAbsence(Date leaveTime, Date backTime, String currUsername, String remark, Long childid, Long teacher, AbsenceType type) {
		List<MessageUserVO> messageUserVOs = sysMessageService.searchMessageUser(teacher);
		String[] regids = new String[messageUserVOs.size()];
		for (int i = 0; i < regids.length; i++) {
			regids[i] = messageUserVOs.get(i).getRegid();
		}
		Map<String, Long> valueMap = absenceDao.selectClassAndSchoolByPupil(childid);
		Long classId = valueMap.get("classid");
		Long schoolid = valueMap.get("schoolid");
		AbsenceVO absence = new AbsenceVO(leaveTime, backTime, type, AbsenceStatus.WAIT, remark, currUsername, childid, classId, schoolid);
		return absenceDao.insertAbsence(absence);
	}

	public List<AbsenceVO> searchAbsences(Long childid, Date absenceDate) {
		return absenceDao.selectAbsences(childid, absenceDate);
	}

	public List<Map<String, Object>> searchTeachers(Long childid) {
		return absenceDao.selectTeachers(childid);
	}

}
