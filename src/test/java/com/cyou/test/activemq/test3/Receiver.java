package com.cyou.test.activemq.test3;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Receiver {
	public static void main(String[] args) throws JMSException {
		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.100.5:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();

		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("myQueue");
		for (int i = 0; i < 10; i++) {

			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message message) {
					TextMessage tm = (TextMessage) message;
					try {
						System.out.println("Received message: " + tm.getText());
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});
		}

		// session.close();
		// connection.stop();
		// connection.close();
	}
}
