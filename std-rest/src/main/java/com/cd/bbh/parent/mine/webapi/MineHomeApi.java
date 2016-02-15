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
import com.cd.bbh.parent.mine.service.MineHomeService;
import com.fasterxml.jackson.databind.JsonNode;

@RestController
@RequestMapping(value = "api/mine", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineHomeApi extends BaseApi {

	@Autowired
	private MineHomeService mimeHomeService;

	@BBHPerm
	@RequestMapping(value = "home")
	public Result headIcon(@RequestParam("params") String params) {
		Long parentid = getCurrentUserID(params);
		JsonNode node = redis.getInfoByAccessToken(getAccessToken(params));
		if (Long.parseLong(node.get("gid").textValue()) == parentid) {
			Result result = new Result(ResultEnum.QUERY_SUCCESS);
			result.setData(mimeHomeService.findMineHome(parentid));
			return result;
		} else {
			Result result = new Result(ResultEnum.CHILD_QUERY_BY_OTHER_PARENT);
			return result;
		}
	}
}
