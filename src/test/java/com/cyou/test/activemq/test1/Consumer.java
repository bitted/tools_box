package com.cyou.test.activemq.test1;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * 类名称：Consumer
 * 类描述：(消息消费者)
 * 
 * 创建人：litaijun
 * 创建时间：2015-4-17 下午02:57:48
 * 修改人：
 * 修改时间：2015-4-17 下午02:57:48
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class Consumer implements MessageListener {

	private static final Logger LOGGER = LoggerFactory.getLogger(Consumer.class);

	private JmsTemplate jmsTemplate;

	/**
	 * @return the jmsTemplate
	 */
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	/**
	 * @param jmsTemplate
	 *            the jmsTemplate to set
	 */
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage textMessage = (TextMessage) message;
			try {
				final String request = textMessage.getText();
				LOGGER.info(request);
				Destination destination = textMessage.getJMSReplyTo();
				final String jmsCorrelationID = textMessage.getJMSCorrelationID();
				jmsTemplate.send(destination, new MessageCreator() {

					@Override
					public Message createMessage(Session session) throws JMSException {
						Message msg = session.createTextMessage(request + "的应答！");
						msg.setJMSCorrelationID(jmsCorrelationID);
						return msg;
					}
				});
			} catch (JMSException e) {
				LOGGER.error("接收信息出错", e);
			}
		}
	}
}
