package com.cyou.hithere.center.util.push;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

/**
 * 和网关通讯工具,以后会修改为长连接
 * 
 */
public class PushUtils {

	private static Logger logger = LoggerFactory.getLogger(PushUtils.class);

	public static void sendAsync(final Map<String, String> map) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// post("http://192.168.100.5:8082/pushsys/dopush.htm", map, 3000);
			}
		}, "sendAsync");
		thread.start();
	}

	public static void main(String[] args) {
		// String json = "{\"f\":2,\"tp\":50,\"tg\":-1,\"ct\":\"“张柏芝”？？？惊艳光圈直播中！\",\"stp\":80,\"time\":"
		// + new Date().getTime() + "}";
		String ct = "演员陈雪正在直播“星座”占卜，想要知道自己2016年“爱情、事业、运势”的朋友，快进直播间！";
		Map<String, Object> jsonmap = new HashMap<String, Object>();

		jsonmap.put("f", 2); // 来源系统
		jsonmap.put("tp", 50);
		jsonmap.put("tg", -1);
		jsonmap.put("stp", 80);
		jsonmap.put("time", new Date().getTime());
		jsonmap.put("ct", ct);// 传送到route的push service处理1.1版本

		String json = JSONObject.fromObject(jsonmap).toString();

		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("json", json);
		// logger.debug("addToGroup json ------> " + json);
		PushUtils.sendAsync(pmap);
	}

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

}
