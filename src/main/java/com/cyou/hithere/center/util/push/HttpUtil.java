package com.cyou.hithere.center.util.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类名称：HttpUt
 * 类描述：(http请求的工具类 用于对外进行http的请求)
 *
 * 创建人：litaijun
 * 创建时间：2016年9月21日 上午9:35:55
 * 修改人：
 * 修改时间：2016年9月21日 上午9:35:55
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class HttpUtil {

	private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	/**
	 * POST
	 * 
	 * @param url
	 * @param map
	 * @param timeout
	 * @return
	 */
	public static String post(String url, Map<String, String> map, int timeout) {
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.addRequestHeader("Content-Type", "utf-8");
		Set<String> keySet = map.keySet();
		NameValuePair[] nvps = new NameValuePair[map.size()];
		int i = 0;
		for (String key : keySet) {
			nvps[i++] = new NameValuePair(key, map.get(key));
		}
		method.setQueryString(nvps);
		timeout = timeout <= 1000 ? 1000 : timeout;
		client.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, timeout);
		String res = null;
		BufferedReader br = null;
		InputStream inputStream = null;
		try {
			client.executeMethod(method);
			int statusCode = method.getStatusCode();

			if (statusCode == 200) {
				inputStream = method.getResponseBodyAsStream();
				br = new BufferedReader(new InputStreamReader(inputStream));
				StringBuffer stringBuffer = new StringBuffer();

				String str = "";
				while ((str = br.readLine()) != null) {
					stringBuffer.append(str);
				}

				res = stringBuffer.toString();
			}
			if (method != null) {
				try {
					method.releaseConnection();
					client.getHttpConnectionManager().closeIdleConnections(0);
				} catch (Exception e) {
					logger.error("close http connetion failure", e);
				}
			}
		} catch (HttpException e) {
			e.printStackTrace();
			logger.error("http异常:", e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("IO异常", e);
		} finally {
			if (method != null) {
				try {
					method.releaseConnection();
					client.getHttpConnectionManager().closeIdleConnections(0);
					if (br != null) {
						br.close();
					}
					if (inputStream != null) {
						inputStream.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("close http connetion failure", e);
				}
			}
		}
		logger.error("res =" + res);

		return res;
	}

	/**
	 * 获取ip地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = "0.0.0.0";
		} else {
			String[] ips = ip.split(",");
			for (int j = ips.length - 1; j >= 0; j--) {
				String tempIp = ips[j];
				if (!tempIp.equalsIgnoreCase("unknown")) {
					ip = tempIp;
					break;
				}
			}
		}
		return ip.trim();
	}
}
