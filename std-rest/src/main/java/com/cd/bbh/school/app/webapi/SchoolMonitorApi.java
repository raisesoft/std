package com.cd.bbh.school.app.webapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;

/**
 * 监控
 * 
 * @author aowin
 *
 */
@RestController
@RequestMapping(value = "api/school")
public class SchoolMonitorApi extends BaseApi {

	@RequestMapping(value = "monitor")
	public Result monitor(@RequestParam("params") String params) {
		return new Result(ResultEnum.SUCCESS);
	}
}
