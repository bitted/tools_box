/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.controller
 * 文件名：BookController.java
 * 版本信息：V1.0
 * 日期：2015-3-30-下午05:17:33
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */

package com.cyou.hithere.center.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyou.hithere.center.service.BookService;
import com.cyou.hithere.center.util.BaseController;
import com.cyou.hithere.center.vo.BaseRet;

/**
 * 类名称：BookController
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午05:17:33
 * 修改人：
 * 修改时间：2015-3-30 下午05:17:33
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Controller
@RequestMapping(value = "/book")
public class BookController extends BaseController {

	@Autowired
	private BookService bookService;

	/**
	 * test(http://localhost:8080/book/test.htm)
	 * 装饰器可以加拦截处理
	 * 
	 * @param request
	 * @param response
	 * @return
	 *         返回类型：String
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "test")
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response) {
		BaseRet br = new BaseRet();
		String name = request.getParameter("name");
		br.putData("name", name);
		return br.toString();
	}

	/**
	 * saveBook(http://localhost:8080/book/save.htm)
	 * 
	 * @param request
	 * @param response
	 *            返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@RequestMapping(value = "save")
	public void saveBook(HttpServletRequest request, HttpServletResponse response) {
		bookService.saveBook();
		this.writeResponseMsg(response, "done");
	}
}
