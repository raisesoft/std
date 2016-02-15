package com.cd.bbh.parent.mine.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.parent.mine.model.Dream;
import com.cd.bbh.parent.mine.service.DreamService;

@RestController
@RequestMapping(value = "api/dream", produces = MediaType.APPLICATION_JSON_VALUE)
public class MineDreamApi extends BaseApi {

	@Autowired
	private DreamService dreamService;

	@BBHPerm
	@RequestMapping(value = "home")
	public Result dreams(@RequestParam("params") String params) {
		List<Dream> dreams = dreamService.findMyDreams(getParamLongValue(params, "childid"));
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(dreams);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "history")
	public Result dreamHistory(@RequestParam("params") String params) {
		List<Dream> dreams = dreamService.searchDreamHistory(getParamLongValue(params, "childid"));
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(dreams);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "create")
	public Result addDream(@RequestParam("params") String params) {
		dreamService.addDream(getParamLongValue(params, "cargoid"),//
				getCurrentUserID(params), //
				getParamLongValue(params, "childid"));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "realize")
	public Result exchangeDream(@RequestParam("params") String params) {
		dreamService.updateDream(getParamLongValue(params, "dreamid"), "S", 1, getParamTextValue(params, "paytype"));
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}
}
