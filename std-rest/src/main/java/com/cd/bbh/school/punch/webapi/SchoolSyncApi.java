package com.cd.bbh.school.punch.webapi;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.school.punch.service.SchoolSyncService;
import com.cd.bbh.school.punch.vo.ClassVO;
import com.cd.bbh.school.punch.vo.TeacherVO;

@RestController
@RequestMapping(value = "api/school")
public class SchoolSyncApi extends BaseApi {

	@Autowired
	private SchoolSyncService syncService;

	@RequestMapping("sync/class")
	public Result syncClass(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		List<ClassVO> classes = syncService.searchClasses(//
				getParamTextValue(params, "punchSerial"), //
				getParamDateValue(params, "syncDate", null));
		result.setData(prepareData(classes));
		return result;
	}

	@RequestMapping("sync/teacher")
	public Result syncTeacher(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		List<TeacherVO> teachers = syncService.searchTeacher(//
				getParamTextValue(params, "punchSerial"), //
				getParamDateValue(params, "syncDate", null));
		result.setData(prepareData(teachers));
		return result;
	}

	@RequestMapping("sync/students")
	public Result syncStudent(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		Map<String, Object> valueMap = syncService.syncStudents(//
				getParamTextValue(params, "punchSerial"), //
				getParamDateValue(params, "syncDate", null));
		result.setData(prepareData(valueMap));
		return result;
	}

	private Map<String, Object> prepareData(Object value) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("syncDate", new Date());
		result.put("result", value);
		return result;
	}
}
