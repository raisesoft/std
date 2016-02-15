package com.cd.bbh.parent.mine.webapi;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.MD5Utils;
import com.cd.bbh.parent.mine.model.ChildDevice;
import com.cd.bbh.parent.mine.model.ChildSitter;
import com.cd.bbh.parent.mine.model.Device;
import com.cd.bbh.parent.mine.service.SitterService;

@RestController
@RequestMapping(value = "api/sitter", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineSitterApi extends BaseApi {

	@Autowired
	private SitterService sitterService;

	@BBHPerm
	@RequestMapping("home")
	public Result list(@RequestParam("params") String params) {
		List<ChildSitter> sitters = sitterService.findSitters(getParamLongValue(params, "parentid"));
		List<Device> devicesList = sitterService.findDevices();

		for (ChildSitter childSitter : sitters) {
			List<ChildDevice> childDevices = childSitter.getDevices();
			Set<Long> deviceKeys = new HashSet<Long>();
			for (ChildDevice childDevice : childDevices) {
				deviceKeys.add(childDevice.getDeviceid());
			}
			for (Device device : devicesList) {
				if (!deviceKeys.contains(device.getId())) {
					ChildDevice childDevice = new ChildDevice(device.getId(), device.getImgpath());
					childSitter.addOtherDevice(childDevice);
				}
			}
		}
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(sitters);
		return result;
	}

	@BBHPerm
	@RequestMapping("device")
	public Result goods(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(sitterService.findDevice(getParamLongValue(params, "deviceid")));
		return result;
	}

	@BBHPerm
	@RequestMapping("device/b")
	public Result binding(@RequestParam("params") String params) {

		sitterService.createChildDevice(getParamLongValue(params, "childid"),//
				getParamLongValue(params, "deviceid"), //
				getCurrentUserID(params), //
				getParamTextValue(params, "account"), //
				getParamTextValue(params, "devicesn"), //
				MD5Utils.getMD5(getParamTextValue(params, "password")));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
