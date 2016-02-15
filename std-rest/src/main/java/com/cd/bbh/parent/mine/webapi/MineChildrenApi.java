package com.cd.bbh.parent.mine.webapi;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cd.bbh.common.BaseApi;
import com.cd.bbh.common.Result;
import com.cd.bbh.common.enums.ResultEnum;
import com.cd.bbh.common.exception.ApplicationException;
import com.cd.bbh.common.section.annotation.BBHPerm;
import com.cd.bbh.common.utils.DateUtils;
import com.cd.bbh.parent.login.service.LoginService;
import com.cd.bbh.parent.login.vo.LoginInfoVO;
import com.cd.bbh.parent.mine.model.Child;
import com.cd.bbh.parent.mine.model.UserService;
import com.cd.bbh.parent.mine.service.ChildrenService;
import com.cd.bbh.parent.sys.service.SysMessageService;

@RestController
@RequestMapping(value = "api/settings", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineChildrenApi extends BaseApi {

	@Autowired
	private ChildrenService childService;

	@Autowired
	private UserService userService;

	@Autowired
	private SysMessageService sysMessageService;

	@Autowired
	private LoginService loginService;

	@BBHPerm
	@RequestMapping(value = "elders")
	public Result elders(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(childService.findElders(getParamLongValue(params, "childid")));
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "mchild")
	public Result child(@RequestParam("params") String params) {
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(childService.findMyChildren(getCurrentUserID(params)));
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "mchild/c")
	public Result addChild(@RequestParam("params") String params, HttpServletRequest request) {

		MultipartFile file = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile[] files = multipartRequest.getFiles("file").toArray(new MultipartFile[] {});
			if (files != null && files.length > 0) {
				file = files[0];
			}
		}

		Date birthday = getParamDateValue(params, "birthday");
		Child child = childService.addChild(getParamTextValue(params, "name"), //
				getParamTextValue(params, "nickName"), //
				getParamTextValue(params, "gender"), //
				DateUtils.getAge(birthday), //
				birthday, //
				getParamTextValue(params, "bloodtype"), //
				getCurrentUserID(params), //
				getParamTextValue(params, "relation", "爸爸"),//
				file);

		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(child);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "mchild/d")
	public Result deleteChild(@RequestParam("params") String params) {
		long childId = getParamLongValue(params, "childid");
		childService.deleteChild(childId);
		Result result = new Result(ResultEnum.SUCCESS);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "mchild/u")
	public Result updateChild(@RequestParam("params") String params, HttpServletRequest request) {
		MultipartFile file = null;
		if (request instanceof MultipartHttpServletRequest) {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile[] files = multipartRequest.getFiles("file").toArray(new MultipartFile[] {});
			if (files != null && files.length > 0) {
				file = files[0];
			}
		}

		Date birthday = getParamDateValue(params, "birthday", null);
		getParamDateValue(params, "birthday");
		Child child = childService.updateChildInfo(getParamLongValue(params, "childid"), //
				getParamTextValue(params, "name", null), //
				getParamTextValue(params, "nickName", null), //
				getParamTextValue(params, "gender", null), //
				DateUtils.getAge(birthday), //
				birthday, //
				getParamTextValue(params, "bloodtype", null), //
				file,//
				getCurrentUserID(params));
		Result result = new Result(ResultEnum.SUCCESS);
		result.setData(child);
		return result;
	}

	@BBHPerm
	@RequestMapping(value = "invite")
	public Result invitionRel(@RequestParam("params") String params) {
		LoginInfoVO userinfo = loginService.findUserByUnique(getParamTextValue(params, "cellphone"));
		if (userinfo == null) {
			throw new ApplicationException(ResultEnum.USER_NOT_EXISTS_ERROR);
		}
		childService.createInvation(getParamLongValue(params, "childid"), //
				userinfo.getUserid(), //
				getParamTextValue(params, "relation"),//
				getCurrentUsername(params),//
				getCurrentUserID(params));
		return new Result(ResultEnum.SUCCESS);
	}

	@BBHPerm
	@RequestMapping(value = "invite/confirm")
	public Result invitionRelConfirm(@RequestParam("params") String params) {
		try {
			childService.addParentChildRel(//
					getParamLongValue(params, "childid"), //
					getCurrentUserID(params),//
					getParamTextValue(params, "relation"));
		} catch (DuplicateKeyException e) {
			return new Result(ResultEnum.INVITATION_CONFIRM_ERROR);
		}

		return new Result(ResultEnum.SUCCESS);
	}

	// String content = String.format(constant.adviceFollowTips, //
	// UUID.randomUUID().toString().replace("-", ""),//
	// DateUtils.formatDateTime(new Date()),//
	// getCurrentUsername(params),//
	// constant.adviceInvitationTitle,//
	// getCurrentUsername(params),//
	// child.getNickname() == null ? child.getName() :
	// child.getNickname(),//
	// getParamTextValue(params, "relation"),//
	// child.getId());
	// MessageVO message = new MessageVO(constant.adviceFollowTitle,
	// content, MessageStatus.PENDDING, //
	// regids.toString().substring(1, regids.toString().length() - 1), //
	// JPushEnum.PERSON, getCurrentUserID(params));

}
