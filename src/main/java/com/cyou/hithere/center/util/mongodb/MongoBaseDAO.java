package com.cyou.hithere.center.util.mongodb;

import java.util.List;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.stereotype.Component;

import com.cyou.hithere.center.util.CustomProperty;


/**
 * 
 * 类名称：MongoBaseDAO
 * 类描述：(Mongodb的CRUD工具类)
 * 创建人：litaijun
 * 创建时间：2013-7-9 上午09:49:29
 * 修改人：
 * 修改时间：2013-7-9 上午09:49:29
 * 修改备注：
 * 
 * @version 1.0.0
 */
@Component
public class MongoBaseDAO<T> {

	private Datastore ds = null;
	private String mongoName = String.valueOf(CustomProperty.getContextProperty("chat.mongoDBName"));

	public MongoBaseDAO() {
		//Long start = System.currentTimeMillis() / 1000;
		ds = MongoDBConnection.getDataStore(mongoName);
		ds.ensureIndexes();
		ds.ensureCaps();
		//Long end = System.currentTimeMillis() / 1000;		
	}

	public void batchAdd(List<T> t) {
		ds.save(t);
	}
	
	public void add(T t) {
		ds.save(t);
	}

	public void delete(T t) {
		ds.delete(t);
	}

	public void delete(Query<T> query) {
		ds.delete(query);
	}

	public int update(T t, UpdateOperations<T> op) {
		return ds.update(t, op).getUpdatedCount();
	}

	public void update(Query<T> query, UpdateOperations<T> op) {
		ds.update(query, op);
	}

	public List<T> query(Query<T> query) {
		return query.asList();
	}

	public long count(Query<T> query) {
		return query.countAll();
	}

	public Datastore getDS() {
		return ds;
	}
}
