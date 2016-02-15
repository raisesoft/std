package com.cd.bbh.school.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.school.app.dao.AbsenceRecordDao;
import com.cd.bbh.school.app.dao.SchoolTeacherClassDao;
import com.cd.bbh.school.app.vo.AbsenceRecord;
import com.cd.bbh.school.app.vo.AbsenceRecordVO;
import com.cd.bbh.school.app.vo.StudentClassSchool;

@Service
public class AbsenceRecordService {
	
	@Autowired
	private AbsenceRecordDao ard;
	@Autowired
	private SchoolTeacherClassDao stcd;

	/**
	 * 獲取請假列表
	 * @param search
	 * @return
	 */
	public List<AbsenceRecordVO> findAbsenceRecord(Map<String, Object> search) {	
		long num = (long) search.get("dataNum");
		long page = (long) search.get("page");
		if(num > 0) {
			long start = num * (page - 1); 
			search.put("start", start);
		}
		return ard.selectAbsenceRecordList(search);
	}
	
	/**
	 * 审核请假
	 * @param userId
	 * @param state
	 */
	public boolean audit(long userId,String roles,long dataId, String state) {
		boolean flag = false;
		String userName = stcd.selectUserNameById(userId);
		if(state.equals("A") || state.equals("R")) {
			String type = ard.selectApplicantTypeById(dataId);
			if(!(roles.equals("TEACHER") && type.equals("T"))) {
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", dataId);
				data.put("user", userName);
				data.put("date", DateUtils.getCurrDate());
				data.put("state", state);
				ard.updateAbsenceRecord(data);
				flag = true;
			}
		}
		return flag;
	}
	
	/**
	 * 添加缺席信息
	 * @param ar
	 * @param userList
	 */
	public boolean createAbsence(AbsenceRecord ar, String userList) {
		boolean flag = false;
		//班級列表處理
		String[] tempU = userList.split(",");
		List<Long> userIds = new ArrayList<Long>();
		for (String s : tempU) {
			userIds.add(Long.valueOf(s));
		}
		if(userIds.size() == 0) {
			return flag;
		}
		List<AbsenceRecord> ars = new ArrayList<AbsenceRecord>();
		String applicantType = ar.getApplicantType();
		long createUserId = Long.valueOf(ar.getCreateUser());
		ar.setCreateUser(stcd.selectUserNameById(createUserId));
		Map<Long, StudentClassSchool> scsMap = null;
		if(applicantType.equals("S")) {
			scsMap = new HashMap<Long, StudentClassSchool>();
			//獲取學生id-classid-schoolid信息列表
			List<StudentClassSchool> scsList = stcd.selectStudentClassSchool(userIds);
			for (StudentClassSchool scs : scsList) {
				scsMap.put(scs.getId(), scs);
			}
		}
		//數據填充
		for (Long u : userIds) {
			AbsenceRecord tempar = new AbsenceRecord();
			tempar.setApplicantId(u);
			tempar.setApplicantType(applicantType);
			tempar.setLeaveTime(ar.getLeaveTime());
			tempar.setBackTime(ar.getBackTime());
			tempar.setCreateDate(ar.getCreateDate());
			tempar.setRemark(ar.getRemark());
			tempar.setCreateUser(ar.getCreateUser());
			tempar.setIsApplied(ar.getIsApplied());
			if(applicantType.equals("S")) {
				StudentClassSchool scs = scsMap.get(u);
				tempar.setClassId(scs.getClassId());
				tempar.setSchoolId(scs.getSchoolId());
				
			} else if(applicantType.equals("T")) {
				tempar.setSchoolId(createUserId);
			}
			ars.add(tempar);
		}
		//添加數據
		if(ard.insertAbsenceRecord(ars) > 0) {
			flag = true;
		}
		return flag;
	}
}
