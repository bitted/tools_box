package com.cyou.hithere.center.util;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;




public class BaseController {
	
	
	private static Logger log = Logger.getLogger(BaseController.class);
	//private static Logger log = LoggerFactory.getLogger(BaseController.class);
	
	protected static final String SUCCESS = "success";
	
	protected static final String ERROR = "error";
	
	/**
	 * 向response中写回字符串消息
	 * @param response
	 * @param msg
	 * @throws IOException
	 * @author zhoujun
	 * @time 2013-1-18 下午07:43:16
	 * @email zhoujun@cyou-inc.com
	 */
	protected void writeResponseMsg(HttpServletResponse response, String msg){
		response.setContentType("application/json; charset=utf-8");
		response.setHeader("Cache-Control", "no-cache"); // 取消浏览器缓存
		Writer writer = null;
		try {
			writer = response.getWriter();
			if (StringUtils.isEmpty(msg)) {
				msg = "";
			}
			writer.write(msg);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			log.error("向reponse写入消息失败",e);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					log.error(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

}
