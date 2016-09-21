package com.cyou.test.activemq.test3;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ActiveMQ的Queue消息队列的
 * 
 * @author longgangbai
 *
 */
public final class SimpleQueueSender {

	private static final Logger LOG = LoggerFactory.getLogger(SimpleQueueSender.class);

	private SimpleQueueSender() {
	}

	public static void main(String[] args) {
		String queueName = "activemqqueue";
		QueueConnectionFactory queueConnectionFactory = null;
		QueueConnection queueConnection = null;
		QueueSession queueSession = null;
		Queue queue = null;
		QueueSender queueSender = null;
		TextMessage message = null;
		final int numMsgs = 10;
		try {
			// 创建链接工厂
			queueConnectionFactory = new ActiveMQConnectionFactory();
			// 创建连接
			queueConnection = queueConnectionFactory.createQueueConnection();
			// 创建会话
			queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			// 创建队列
			queue = queueSession.createQueue(queueName);
			// 创建消息发送者
			queueSender = queueSession.createSender(queue);
			message = queueSession.createTextMessage();
			for (int i = 0; i < numMsgs; i++) {
				message.setText("This is message " + (i + 1));
				LOG.info("Sending message: " + message.getText());
				queueSender.send(message);
			}

			// 发送消息
			queueSender.send(queueSession.createMessage());
		} catch (JMSException e) {
			LOG.info("Exception occurred: " + e.toString());
		} finally {
			if (queueConnection != null) {
				try {
					queueConnection.close();
				} catch (JMSException e) {
				}
			}
		}
	}
}
