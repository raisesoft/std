package com.cd.bbh.parent.mine.webapi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.JPushEnum;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.mine.service.GrowingService;
import com.cd.bbh.parent.mine.vo.GrowingVO;
import com.cd.bbh.parent.sys.service.SysMessageService;
import com.cd.bbh.parent.sys.vo.MessageUserVO;
import com.cd.bbh.parent.sys.vo.NotificationVO;

@RestController
@RequestMapping(value = "api/grow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineGrowingApi extends BaseApi {

	@Autowired
	private GrowingService growingService;

	@Autowired
	private SysMessageService sysMessageService;

	@Autowired
	private ChildrenService childrenService;

	@BBHPerm
	@RequestMapping("home")
	public Result growthRecord(@RequestParam("params") String params) {
		List<GrowingVO> growings = growingService.searchGrowingRecordByChildid(//
				getParamLongValue(params, "childid"), //
				DateUtils.getDayOfEndTime(getParamDateValue(params, "searchDate", new Date())).getTime(), //
				getPageable(params));

		Result result = new Result(ResultEnum.QUERY_SUCCESS);
		result.setData(growings);
		return result;
	}

	@BBHPerm
	@RequestMapping("record")
	public Result growth(@RequestParam("params") String params, HttpServletRequest request) {
		try {
			Result result = new Result(ResultEnum.SUCCESS);
			GrowingVO growing = mapper.readValue(getParams(params).toString(), GrowingVO.class);
			growing.setCreator(getCurrentUsername(params));
			growing.setCreateDate(getParamDateValue(params, "shootDate", new Date()));
			growing.setShootDate(getParamDateValue(params, "shootDate", null));// 对象设置Jsonignore的时候转化数据
			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				growingService.createGrowingRecord(growing, getCurrentUserID(params), multipartRequest.getFiles("file").toArray(new MultipartFile[] {}));
			} else {
				growingService.createGrowingRecord(growing, getCurrentUserID(params), new MultipartFile[] {});
			}
			saveAdvice(params, growing);
			result.setData(growing);
			return result;
		} catch (IOException cause) {
			throw new ApplicationException(ResultEnum.GROW_RECORD_ERROR, cause);
		}
	}

	@BBHPerm
	@RequestMapping("comment")
	public Result comment(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		growingService.createGrowingComment(//
				getParamLongValue(params, "growingid"), //
				getParamTextValue(params, "comments"));
		return result;
	}

	@BBHPerm
	@RequestMapping("picture")
	public Result picture(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);

		return result;
	}

	@BBHPerm
	@RequestMapping("vedio")
	public Result vedio(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping("events")
	public Result event(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping("basic")
	public Result basic(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	private void saveAdvice(String params, GrowingVO growing) {
		List<MessageUserVO> messageUserVOs = sysMessageService.searchMessageUserByChild(getParamLongValue(params, "childid"));
		if (messageUserVOs == null || messageUserVOs.size() <= 0) {
			return;
		}
		Child child = childrenService.findChild(getParamLongValue(params, "childid"));
		String title = String.format(constant.follow, getCurrentUsername(params),//
				StringUtils.isNotBlank(child.getNickname()) ? child.getNickname() : child.getName());
		List<String> regids = new ArrayList<String>();
		for (MessageUserVO messageUserVO : messageUserVOs) {
			regids.add(messageUserVO.getRegid());
		}

		NotificationVO notification = new NotificationVO(title, null, "Y", "GROWING", JPushEnum.PERSON.siginal(), //
				regids.toString().substring(1, regids.toString().length() - 1), getCurrentUserID(params));

		sysMessageService.createNotification(notification);
	}
}
