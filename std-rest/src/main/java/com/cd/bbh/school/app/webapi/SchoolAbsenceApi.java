package com.cd.bbh.school.app.webapi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.enums.RoleEnum;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.school.app.service.AbsenceRecordService;
import com.cd.bbh.school.app.vo.AbsenceRecord;

/**
 * 考勤
 * 
 * @author aowin
 *
 */
@RestController
@RequestMapping("api/school")
public class SchoolAbsenceApi extends BaseApi {

	@Autowired
	private AbsenceRecordService ars;
	
	/**
	 * 請假名單
	 * @param params
	 * @return
	 */
	@RequestMapping("absencerecord")
	public Result absencerecord(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		RoleEnum roles = getCurrentUserRoleEnum(params);
		if(roles.equals(RoleEnum.TEACHER) || roles.equals(RoleEnum.SCHOOL)) {
			Map<String, Object> search = new HashMap<String, Object>();
			search.put("userId", getCurrentUserID(params));
			search.put("userRoles", roles.toString());
			search.put("date", DateUtils.parse(DateUtils.format(DateUtils.getCurrDate())));
			search.put("state", getParamTextValue(params, "state", null));
			search.put("isApplied", getParamTextValue(params, "isApplied", null));
			search.put("dataNum", getParamLongValue(params, "pageSize", 0L));
			search.put("page", getParamLongValue(params, "page", 1L));
			result.setData(ars.findAbsenceRecord(search));
		}
		return result;
	}
	
	/**
	 * 記錄缺席人員
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "absence", method = { RequestMethod.POST, RequestMethod.PUT })
	public Result absence(@RequestParam("params") String params) {
		ResultEnum result = ResultEnum.OPERATION_ERROR;
		RoleEnum roles = getCurrentUserRoleEnum(params);
		long createUserId = getCurrentUserID(params);
		if(roles.equals(RoleEnum.TEACHER) || roles.equals(RoleEnum.SCHOOL)) {
			String userIdList = getParamTextValue(params, "userId");
			AbsenceRecord ar = new AbsenceRecord();
			if(roles.equals(RoleEnum.TEACHER)) {
				ar.setApplicantType("S");
			} else {
				ar.setApplicantType("T");
				ar.setSchoolId(createUserId);
			}
			ar.setCreateUser(Long.toString(createUserId));
			ar.setCreateDate(DateUtils.getCurrDate());
			ar.setLeaveTime(DateUtils.getCurrDate());
			ar.setBackTime(DateUtils.getDayOfEndTime(DateUtils.getCurrDate()).getTime());
			ar.setRemark(getParamTextValue(params, "rebark"));
			ar.setIsApplied("N");
			if(ars.createAbsence(ar, userIdList)) {
				result = ResultEnum.SUCCESS;
			}
		}
	
		return new Result(result);
	}
	
	/**
	 * 请假审核
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "audit", method = { RequestMethod.POST, RequestMethod.PUT })
	public Result audit(@RequestParam("params") String params) {
		ResultEnum result = ResultEnum.OPERATION_ERROR;
		RoleEnum roles = getCurrentUserRoleEnum(params);
		if(roles.equals(RoleEnum.TEACHER) || roles.equals(RoleEnum.SCHOOL)){
			if(ars.audit(getCurrentUserID(params),roles.toString(),
					getParamLongValue(params, "id"),
					getParamTextValue(params, "state"))) {
				result = ResultEnum.SUCCESS;
			}
		}
		return new Result(result);
	}
}
