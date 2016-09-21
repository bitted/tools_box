package com.cyou.hithere.center.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyou.hithere.center.service.UserService;
import com.cyou.hithere.center.util.BaseController;
import com.cyou.hithere.center.vo.BaseRet;

/**
 * 类名称：UserController
 * 类描述：(user)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-23 下午03:56:36
 * 修改人：
 * 修改时间：2015-3-23 下午03:56:36
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	protected static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping(value = "test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		BaseRet br = new BaseRet();
		String name = request.getParameter("name");
		br.putData("name", name);
		// this.writeResponseMsg(response, JsonMapper.nonEmptyMapper().toJson(br));
		// return JsonMapper.nonEmptyMapper().toJson(br);
		return br.toString();
	}

	/**
	 * save(保存用户)
	 * http://localhost:8080/user/save.htm
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "save")
	public void save(HttpServletRequest request, HttpServletResponse response, Model model) {
		String json = userService.save();
		this.writeResponseMsg(response, json);
	}

	/**
	 * list(查询列表)
	 * http://localhost:8088/user/list.htm?id=550bda5bbbe2433614398eea
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "list")
	public void list(HttpServletRequest request, HttpServletResponse response, Model model) {
		String json = userService.list(request.getParameter("id"));
		this.writeResponseMsg(response, json);
	}

	/**
	 * update(更新)
	 * http://localhost:8088/user/update.htm?id=550bda5bbbe2433614398eea&name=wangwu
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "update")
	public void update(HttpServletRequest request, HttpServletResponse response, Model model) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String json = userService.update(id, name);
		this.writeResponseMsg(response, json);
	}

	/**
	 * del(删除)
	 * http://localhost:8088/user/del.htm?id=550bda5bbbe2433614398eea
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "del")
	public void del(HttpServletRequest request, HttpServletResponse response, Model model) {
		String json = userService.del(request.getParameter("id"));
		this.writeResponseMsg(response, json);
	}

	/**
	 * aggregate(聚合统计)
	 * http://localhost:8088/user/aggregate.htm
	 * 
	 * @param request
	 * @param response
	 * @param model
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "aggregate")
	public void aggregate(HttpServletRequest request, HttpServletResponse response, Model model) {
		String json = userService.aggregate();
		this.writeResponseMsg(response, json);
	}
}
