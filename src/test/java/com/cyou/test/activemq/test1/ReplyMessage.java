package com.cyou.test.activemq.test1;

import java.util.concurrent.Semaphore;

import javax.jms.Message;

/**
 * 类名称：ReplyMessage
 * 类描述：(应答报文)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-17 下午02:58:11
 * 修改人：
 * 修改时间：2015-4-17 下午02:58:11
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class ReplyMessage {

	private Semaphore semaphore = new Semaphore(0);

	private Message message;

	public Semaphore getSemaphore() {
		return semaphore;
	}

	public void setSemaphore(Semaphore semaphore) {
		this.semaphore = semaphore;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
