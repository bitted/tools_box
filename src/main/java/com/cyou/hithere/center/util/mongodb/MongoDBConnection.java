package com.cyou.hithere.center.util.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.cyou.hithere.center.util.CustomProperty;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ReadPreference;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoDatabase;

/**
 * 类名称：MongoDBConnection
 * 类描述：(Mongodb连接工具类，使用了mongodb-java-driver的3.0版本，支持主节点自动切换和新增删节点监控)
 * 
 * 创建人：litaijun
 * 创建时间：2015-3-20 下午03:50:37
 * 修改人：
 * 修改时间：2015-3-20 下午03:50:37
 * 修改备注：
 * 
 * @version 1.0.0
 */
public class MongoDBConnection {
	protected static Logger logger = Logger.getLogger(MongoDBConnection.class);

	private static MongoClient instance = null;

	public static Datastore getDataStore(String dbname) {
		Morphia morphia = new Morphia();
		AdvancedDatastore ads = (AdvancedDatastore) morphia.createDatastore(getMongo(), dbname);
		return ads;
	}

	public static MongoDatabase getDB(String dbname) {
		return getMongo().getDatabase(dbname);
	}

	/**
	 * instance
	 * 
	 * @return the instance
	 * @since 1.0.0
	 */

	public static Mongo getInstance() {
		return instance;
	}

	public static synchronized MongoClient getMongo() {
		if (instance == null) {

			List<IPlistVO> iplist = IPlistUtil.getIPlist();

			try {
				List<ServerAddress> saList = new ArrayList<ServerAddress>();

				for (int i = 0; i < iplist.size(); i++) {
					IPlistVO iplistVO = iplist.get(i);
					String ip = iplistVO.getIp();
					Integer port = iplistVO.getPort();
					logger.info("add mongodb ip : " + ip + " port : " + port);
					saList.add(new ServerAddress(ip, port));
				}

				List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();

				String mongoUserName = String.valueOf(CustomProperty.getContextProperty("chat.mongoUserName"));
				String mongoPassWord = String.valueOf(CustomProperty.getContextProperty("chat.mongoPassWord"));

				if (!StringUtils.isEmpty(mongoUserName) && !StringUtils.isEmpty(mongoPassWord)) {
					MongoCredential credentials = MongoCredential.createScramSha1Credential(mongoUserName,
							String.valueOf(CustomProperty.getContextProperty("chat.mongoDBName")), mongoPassWord.toCharArray());
					credentialsList.add(credentials);
				}

				MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
				MongoClientOptions options = builder.build();

				builder.requiredReplicaSetName(String.valueOf(CustomProperty.getContextProperty("chat.mongo.rsname")));
				/**
				 * mongo描述
				 */
				builder.description("CYOU Mongo ！");
				/**
				 * 连接数
				 */
				builder.connectionsPerHost(Integer.valueOf(String.valueOf(CustomProperty
						.getContextProperty("chat.mongo.mongodbConnCount"))));
				/**
				 * 连接超时时间
				 */
				builder.connectTimeout(30000);
				/**
				 * 最大等待时间
				 */
				builder.maxWaitTime(120000);
				/**
				 * 保持连接
				 */
				builder.socketKeepAlive(true);
				/**
				 * 0，不限时间
				 */
				builder.socketTimeout(0);

				/**
				 * 这个乘数,乘以这个connectionsPerHost,来设置一个连接的最大连接池等待数量例如,
				 * 如果connectionsPerHost是10和threadsAllowedToBlockForConnectionMultiplier是5,那么多达50个线程可以等待连接。
				 * 默认值是5。
				 */
				builder.threadsAllowedToBlockForConnectionMultiplier(50);
				builder.cursorFinalizerEnabled(true);
				/**
				 * 设置MongoClient，包括集群地址和数据库连接参数配置
				 */
				if (credentialsList.size() != 0) {
					instance = new MongoClient(saList, credentialsList, options);
				} else {
					instance = new MongoClient(saList, options);
				}

				/**
				 * 设置优先从库读取数据
				 */
				instance.setReadPreference(ReadPreference.secondaryPreferred());
				/**
				 * 设置主库CUD操作
				 */
				instance.setWriteConcern(WriteConcern.ACKNOWLEDGED);
			} catch (Exception e) {
				logger.error("Mongon数据库服务器连接失败！", e);
				return null;
			}

		}
		return instance;
	}

	/**
	 * @param instance
	 *            the instance to set
	 */

	public static void setInstance(MongoClient instance) {
		MongoDBConnection.instance = instance;
	}
}
