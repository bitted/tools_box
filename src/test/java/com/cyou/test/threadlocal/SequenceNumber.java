package com.cyou.test.threadlocal;

/**
 * 类名称：SequenceNumber
 * 类描述：(ThreadLocal使用实例)
 *
 * 创建人：litaijun
 * 创建时间：2016年1月6日 下午7:38:18
 * 修改人：
 * 修改时间：2016年1月6日 下午7:38:18
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class SequenceNumber {
	// ①通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		@Override
		public Integer initialValue() {
			return 0;
		}
	};

	// ②获取下一个序列值
	public Integer getNextNum() {
		seqNum.set(seqNum.get() + 1);
		return seqNum.get();
	}

	public static void main(String[] args) {
		SequenceNumber sn = new SequenceNumber();
		// ③ 3个线程共享sn，各自产生序列号
		TestClient t1 = new TestClient(sn);
		TestClient t2 = new TestClient(sn);
		TestClient t3 = new TestClient(sn);
//		new Thread(t1).start();
//		new Thread(t2).start();
//		new Thread(t3).start();
		
		for (int i = 0; i < 100; i++) {
			System.out.println(sn.getNextNum()-1);
		}
	}
}

class TestClient implements Runnable {
	private SequenceNumber sn;

	public TestClient(SequenceNumber sn) {
		super();
		this.sn = sn;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			// ④每个线程打出3个序列值
			System.out.println("thread[" + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
		}
	}
}