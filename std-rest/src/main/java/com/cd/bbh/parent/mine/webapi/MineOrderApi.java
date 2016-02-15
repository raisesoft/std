package com.cd.bbh.parent.mine.webapi;

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
import com.cd.bbh.parent.mine.service.OrderService;

@RestController
@RequestMapping(value = "api/order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineOrderApi extends BaseApi {

	@Autowired
	private OrderService orderService;

	@BBHPerm
	@RequestMapping(value = "list")
	public Result orders(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(orderService.findOrders(getParamLongValue(params, "childid", null),//
				getCurrentUsername(params), getPageable(params)));
		return result;
	}
}
