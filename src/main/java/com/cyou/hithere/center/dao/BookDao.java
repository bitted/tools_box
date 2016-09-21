
/**
 * 项目名称：hithere-center2
 * 包名：com.cyou.hithere.center.dao
 * 文件名：BookDao.java
 * 版本信息：V1.0
 * 日期：2015-3-30-下午05:18:47
 * 作者：litaijun
 * Copyright (c) 2015畅游天下-版权所有
 */
 
package com.cyou.hithere.center.dao;

import org.springframework.stereotype.Repository;

import com.cyou.hithere.center.domain.Book;
import com.cyou.hithere.center.util.mongodb.MongoBaseDAO;


/**
 * 类名称：BookDao
 * 类描述：(描述此类所在架构中层次，如：某某服务接口实现、某某实体模型、某某工具类等----必填)
 *		   
 * 创建人：litaijun
 * 创建时间：2015-3-30 下午05:18:47
 * 修改人：
 * 修改时间：2015-3-30 下午05:18:47
 * 修改备注：
 * @version 1.0.0
 */
@Repository
public class BookDao extends MongoBaseDAO<Book> {

}
