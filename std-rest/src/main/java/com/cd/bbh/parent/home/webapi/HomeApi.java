package com.cd.bbh.parent.home.webapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Pageable;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.parent.home.vo.SearchEnum;
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.school.service.ParentSchoolService;
import com.cd.bbh.parent.school.vo.SchoolVO;
import com.cd.bbh.parent.sys.service.SysAdvertService;
import com.cd.bbh.parent.sys.vo.AdvertVO;

@RestController
@RequestMapping(value = "api/home", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeApi extends BaseApi {

	@Autowired
	private ParentSchoolService schoolService;

	@Autowired
	private ChildrenService childrenService;

	@Autowired
	private SysAdvertService sysAdvertService;

	@RequestMapping("search")
	public Result wildSearch(@RequestParam("params") String params) {
		String condition = getParamTextValue(params, "condition", null);
		String searchType = getParamTextValue(params, "searchType", SearchEnum.PUPIL.siginal());
		String cityname = getParamTextValue(params, "cityname");

		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		if (SearchEnum.GOODS.siginal().equals(searchType)) {
			result.setData(sysAdvertService.findAdvertsByName(getPageable(params), condition, cityname));
		} else if (SearchEnum.SCHOOL.siginal().equals(searchType)) {
			result.setData(schoolService.findSchoolByName(getPageable(params), condition, cityname));
		} else if (SearchEnum.PUPIL.siginal().equals(searchType)) {
			result.setData(childrenService.findStarChildren(getPageable(params), 2, 18, condition));
		}
		return result;
	}

	// /////////////////////////首页广告//////////////////////////////////////////////////
	/**
	 * 首页活动广告
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping("advert")
	public Result advert(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<AdvertVO> offers = sysAdvertService.findAdverts(getPageable(params, new Pageable(6, 1)), getParamTextValue(params, "cityname"));
		result.setData(offers);
		return result;
	}

	// /////////////////////////首页学校//////////////////////////////////////////////////
	/**
	 * 首页明星学校
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "school", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Result schoolStar(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		List<SchoolVO> schoolList = schoolService.findStarSchool(//
				getPageable(params, new Pageable(6, 1)),//
				getParamTextValue(params, "cityname", null),//
				getParamFloatValue(params, "longtitude", null),//
				getParamFloatValue(params, "latitude", null));
		result.setData(schoolList);
		return result;
	}

	// ////////////////////////////////////首页小孩///////////////////////////////////////
	/**
	 * 首页明星小朋友
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "children")
	public Result chilStar(@RequestParam("params") String params) {
		Integer minage = getParamIntValue(params, "minage", 2);
		Integer maxage = getParamIntValue(params, "maxage", 3);
		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(childrenService.findStarChildren(getPageable(params), minage, maxage, null));
		return result;
	}
}
