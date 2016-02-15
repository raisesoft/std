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
import com.cd.bbh.parent.mine.model.Order;
import com.cd.bbh.parent.mine.model.Work;
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.mine.service.OrderService;
import com.cd.bbh.parent.mine.service.WorkService;
import com.cd.bbh.parent.mine.vo.ProductEnum;

@RestController
@RequestMapping(value = "api/work", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineWorkApi extends BaseApi {

	@Autowired
	private WorkService workService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private ChildrenService childService;

	@BBHPerm
	@RequestMapping("home")
	public Result home(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(workService.findUndoWork(getParamLongValue(params, "childid"), getPageable(params)));
		return result;
	}

	// /////////////////////////////添加工作/////////////////////////////////////////////
	@BBHPerm
	@RequestMapping("list")
	public Result workList(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(workService.findAllWork(getPageable(params)));
		return result;
	}

	@BBHPerm
	@RequestMapping("order")
	public Result workCreate(@RequestParam("params") String params) {
		Work work = workService.findWork(getParamLongValue(params, "workid"));
		Order order = orderService.createOrder(//
				getParamLongValue(params, "childid"), //
				getCurrentUserInfo(params).get("username").textValue(), //
				getParamIntValue(params, "count"), //
				work.getWorkid(), //
				work.getWorkcost(), //
				ProductEnum.WORK.siginal(), //
				work.getWorkname(), //
				work.getWorkimg());
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(order);
		return result;
	}

	@BBHPerm
	@RequestMapping("create")
	public Result workPay(@RequestParam("params") String params) {
		workService.createChildWork(//
				getParamLongValue(params, "orderid"), //
				getCurrentUserID(params));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping("discard")
	public Result workDiscard(@RequestParam("params") String params) {
		workService.deleteChildWork(getParamLongValue(params, "workid"));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping("update")
	public Result workUpdate(@RequestParam("params") String params) {
		workService.updateChildWork(//
				getParamLongValue(params, "workid"),//
				getParamTextValue(params, "comments"), //
				getCurrentUserID(params), //
				getParamDateValue(params, "finishdate"), //
				getParamFloatValue(params, "score"));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	// /////////////////////////////查看工作历史/////////////////////////////////////////////
	@BBHPerm
	@RequestMapping("history")
	public Result workHistory(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(workService.findWorkHistory(getParamLongValue(params, "childid"), getPageable(params)));
		return result;
	}
}
