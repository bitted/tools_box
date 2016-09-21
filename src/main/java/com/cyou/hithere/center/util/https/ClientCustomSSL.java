package com.cyou.hithere.center.util.https;

import java.io.Closeable;
import java.io.File;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;


/**
 * This example demonstrates how to create secure connections with a custom SSL
 * context.
 */
public class ClientCustomSSL {

	public final static void main(String[] args) throws Exception {
		// Trust own CA and all self-signed certs
		SSLContext sslcontext = SSLContexts
				.custom()
				.loadTrustMaterial(new File("/Users/admin/Desktop/im.dev/tclient1.keystore"), "123456".toCharArray(),
						new TrustSelfSignedStrategy())
				.loadKeyMaterial(new File("/Users/admin/Desktop/im.dev/guangquanerdev.keystore"), "123456".toCharArray(),
						"123456".toCharArray()).build();
		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1" }, null,
				SSLConnectionSocketFactory.getDefaultHostnameVerifier());
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		try {
			String json = "{\"a\":1}";
			System.out.println(json);
			HttpPost httppost = new HttpPost(
					"https://openapi.tim.qq.com/v3/openim/videorelay?usersig=eJxNjFFPwjAUhf9Ln41AtzpqwoNlzFgdEVCQvTRlbcnN0lm6wjTG-*5cIOG*3fOd8-2gt5fVbaMqIZ0Dhe7RKB52F9FxjG56qL8ceC2kCdp3HBOKu8IZ9ishg4j8--gSB7C6dxEa42Q0HJ9zULoOYKA37Q9CKgv1RVWWn8c6iPDt9JWqgX335bPF9Cl1euDJoKAH*8yZDjFvdYnTeWQraeZLk7KC7Kab3XqLH4DxdpbxRdGcVGMsyzP26t9Xm*NjJk8fCSnKHNq7bVLxJW0nE-T7BwF1USk_&apn=0&identifier=gq_admin&sdkappid=1400003984&contenttype=json");
			// 设置参数
			StringEntity entity = new StringEntity(json, "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			httppost.setEntity(entity);
			HttpResponse response = httpclient.execute(httppost);
			System.out.println("Executing request " + httppost.getRequestLine());

			try {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					String result = EntityUtils.toString(resEntity, "utf-8");
					System.out.println("result="+result);
				}
				System.out.println(response.getStatusLine());
				EntityUtils.consume(entity);
			} finally {
				((Closeable) response).close();
			}
		} finally {
			httpclient.close();
		}
	}

}