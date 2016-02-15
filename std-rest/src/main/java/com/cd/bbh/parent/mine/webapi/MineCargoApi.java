package com.cd.bbh.parent.mine.webapi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.cd.bbh.parent.mine.model.Cargo;
import com.cd.bbh.parent.mine.model.CargoCategory;
import com.cd.bbh.parent.mine.service.CargoService;

@RestController
@RequestMapping(value = "api/cargo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineCargoApi extends BaseApi {

	@Autowired
	private CargoService cargoService;

	@BBHPerm
	@RequestMapping(value = "list")
	public Result cargo(@RequestParam("params") String params) {
		List<Cargo> cargoBoy = cargoService.findAllCargos(getPageable(params), CargoCategory.BOY.key());
		List<Cargo> cargoGirl = cargoService.findAllCargos(getPageable(params), CargoCategory.GIRL.key());
		List<Map<String, Object>> valueMaps = new ArrayList<Map<String, Object>>();
		Map<String, Object> cargoBoyMap = new HashMap<String, Object>();
		Map<String, Object> cargoGirlMap = new HashMap<String, Object>();
		cargoBoyMap.put("catogory", CargoCategory.BOY.desc());
		cargoBoyMap.put("cargos", cargoBoy);
		cargoGirlMap.put("gender", CargoCategory.GIRL.desc());
		cargoGirlMap.put("cargos", cargoGirl);

		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		valueMaps.add(cargoBoyMap);
		valueMaps.add(cargoGirlMap);
		result.setData(valueMaps);
		return result;
	}

}
