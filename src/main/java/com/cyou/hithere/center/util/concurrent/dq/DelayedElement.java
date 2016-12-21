/**
 * 项目名称：tools_box
 * 包名：com.cyou.hithere.center.util.concurrent.dq
 * 文件名：DelayedElement.java
 * 版本信息：V1.0
 * 日期：2016年12月20日-下午4:59:54
 * 作者：litaijun
 * Copyright (c) 2016北京费米子信息技术有限公司-版权所有
 */

package com.cyou.hithere.center.util.concurrent.dq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：DelayedElement
 * 类描述：(描述此类所在架构中层次)
 *
 * 创建人：litaijun
 * 创建时间：2016年12月20日 下午4:59:54
 * 修改人：
 * 修改时间：2016年12月20日 下午4:59:54
 * 修改备注：
 * 
 * @version 1.0.0
 */

public class DelayedElement implements Delayed {

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.concurrent.Delayed#getDelay(java.util.concurrent.TimeUnit)
	 */

	@Override
	public long getDelay(TimeUnit unit) {
		// TODO Auto-generated method stub
		return 0;
	}

}
