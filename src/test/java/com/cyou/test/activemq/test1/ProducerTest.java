package com.cyou.test.activemq.test1;

import javax.annotation.Resource;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 消息生产者测试类
 * 
 * @author zhaiyz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-jms.xml")
public class ProducerTest {

	@Resource
	private Producer producer;

	/**
	 * @return the producer
	 */
	public Producer getProducer() {
		return producer;
	}

	/**
	 * @param producer
	 *            the producer to set
	 */
	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	/**
	 * Test method for {@link com.cyou.hithere.center.util.concurrent.abq.zhaiyz.activemq.Producer#sendMessage(java.lang.String)}.
	 * 
	 * @throws Throwable
	 */
	@Test
	public void testSendMessage() throws Throwable {
		// Rnner数组，想当于并发多少个。
		TestRunnable[] trs = new TestRunnable[10];
		for (int i = 0; i < trs.length; i++) {
			// 构造一个Runner
			trs[i] = new SendMessageThread();
		}
		// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
		// 开发并发执行数组里定义的内容
		mttr.runTestRunnables();
	}

	/**
	 * 类名称：SendMessageThread
	 * 类描述：(创建多线程的执行类)
	 * 
	 * 创建人：litaijun
	 * 创建时间：2015-4-17 下午03:01:40
	 * 修改人：
	 * 修改时间：2015-4-17 下午03:01:40
	 * 修改备注：
	 * 
	 * @version 1.0.0
	 */
	private class SendMessageThread extends TestRunnable {

		@Override
		public void runTest() throws Throwable { // 测试内容
			String request = "第" + this.hashCode() + "条信息";
			Assert.assertEquals(request + "的应答！", producer.sendMessage(request));
		}

	}

}