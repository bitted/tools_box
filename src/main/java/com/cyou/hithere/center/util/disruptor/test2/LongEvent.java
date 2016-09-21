package com.cyou.hithere.center.util.disruptor.test2;

/**
 * 定义事件
 *事件(Event)就是通过 Disruptor 进行交换的数据类型
 * @author totyuZWL
 */
public class LongEvent {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
