package com.cyou.hithere.center.util.kafka;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class TestProducer {
	public static void main(String[] args) {
		long events = 10000l;
		Random rnd = new Random();

		Properties props = new Properties();
		props.put("metadata.broker.list", "172.16.254.8:9093,172.16.254.8:9094");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "com.cyou.hithere.center.util.kafka.SimplePartitioner");
		props.put("request.required.acks", "1");

		ProducerConfig config = new ProducerConfig(props);

		Producer<String, String> producer = new Producer<String, String>(config);

		for (long nEvents = 0; nEvents < events; nEvents++) {
			long runtime = new Date().getTime();
			String ip = "172.16.254." + rnd.nextInt(255);
			String msg = runtime + ",www.example.com," + ip;
			KeyedMessage<String, String> data = new KeyedMessage<String, String>("page_visits", ip, msg);
			producer.send(data);
		}
		producer.close();
	}
}