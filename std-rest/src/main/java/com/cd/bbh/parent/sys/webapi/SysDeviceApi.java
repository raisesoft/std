package com.cd.bbh.parent.sys.webapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.parent.sys.service.SysDeviceService;

@RestController
@RequestMapping(value = "api/sys/device", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class SysDeviceApi extends BaseApi {

	@Autowired
	private SysDeviceService sysDeviceService;

	@RequestMapping(value = "list")
	public Result list(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		
		getCurrentUserID(params);
		result.setData(sysDeviceService.findDevices(getPageable(params)));
		return result;
	}
}
