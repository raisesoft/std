package com.cd.bbh.parent.mine.webapi;

import java.util.Date;

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
import com.cd.bbh.parent.mine.model.Address;
import com.cd.bbh.parent.mine.service.AddressService;

@RestController
@RequestMapping(value = "api/settings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineAddressApi extends BaseApi {

	@Autowired
	private AddressService addressService;

	@BBHPerm
	@RequestMapping(value = "addr")
	public Result address(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(addressService.findMyAddress(getCurrentUserID(params)));
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "addr/c")
	public Result createAddress(@RequestParam("params") String params) {
		Address address = new Address(//
				getParamLongValue(params, "countryid"), //
				getParamLongValue(params, "provinceid"), //
				getParamLongValue(params, "cityid"), //
				getParamLongValue(params, "countyid"), //
				getParamTextValue(params, "addr"), //
				getParamTextValue(params, "postcode"), //
				getParamTextValue(params, "phone"), //
				new Date(), //
				getCurrentUserID(params).toString(), //
				getCurrentUserID(params), //
				getParamTextValue(params, "isdefault"), //
				getParamTextValue(params, "receiver"));

		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(addressService.createAddress(address));
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "addr/u")
	public Result editAddress(@RequestParam("params") String params) {
		Address address = new Address(//
				getParamLongValue(params, "addressid"),//
				getParamLongValue(params, "countryid"), //
				getParamLongValue(params, "provinceid"), //
				getParamLongValue(params, "cityid"), //
				getParamLongValue(params, "countyid"), //
				getParamTextValue(params, "addr"), //
				getParamTextValue(params, "postcode"), //
				getParamTextValue(params, "phone"), //
				new Date(), //
				getCurrentUserInfo(params).get("username").textValue(), //
				getCurrentUserID(params), //
				getParamTextValue(params, "isdefault"), //
				getParamTextValue(params, "receiver"));
		addressService.updateAddress(address);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "addr/d")
	public Result delAddress(@RequestParam("params") String params) {
		addressService.deleteAddress(getParamLongValue(params, "addressid"));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
