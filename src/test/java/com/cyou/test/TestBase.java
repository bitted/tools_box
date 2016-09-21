package com.cyou.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Assume;
import org.junit.Before;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public abstract class TestBase {
	private final MongoClient mongoClient;
	private DB db;
	private Datastore ds;
	private AdvancedDatastore ads;
	private final Morphia morphia = new Morphia();

	protected TestBase() {
		try {
			List<ServerAddress> saList = new ArrayList<ServerAddress>();

			saList.add(new ServerAddress("192.168.100.12", 27017));
			saList.add(new ServerAddress("192.168.100.13", 27017));
			saList.add(new ServerAddress("192.168.100.15", 27017));
			List<MongoCredential> credentialsList = new ArrayList<MongoCredential>();

			String mongoUserName = String.valueOf("chatserver");
			String mongoPassWord = String.valueOf("123456");
			if (!StringUtils.isEmpty(mongoUserName) && !StringUtils.isEmpty(mongoPassWord)) {
				MongoCredential credentials = MongoCredential.createScramSha1Credential(mongoUserName, String.valueOf("chatserver"),
						mongoPassWord.toCharArray());
				credentialsList.add(credentials);
			}
			MongoClientOptions.Builder builder = new MongoClientOptions.Builder();
			MongoClientOptions options = builder.build();

			builder.requiredReplicaSetName(String.valueOf("foba"));

			mongoClient = new MongoClient(saList, credentialsList, options);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Before
	public void setUp() {
		setDb(getMongoClient().getDB("chatserver"));
		setDs(getMorphia().createDatastore(getMongoClient(), getDb().getName()));
		setAds((AdvancedDatastore) getDs());
	}

	protected void cleanup() {
		DB db = getDb();
		if (db != null) {
			db.dropDatabase();
		}
	}

	@After
	public void tearDown() {
//		cleanup();
		getMongoClient().close();
	}

	/**
	 * @param version
	 *            must be a major version, e.g. 1.8, 2,0, 2.2
	 * @return true if server is at least specified version
	 */
	protected boolean serverIsAtLeastVersion(final double version) {
		String serverVersion = (String) getMongoClient().getDB("admin").command("serverStatus").get("version");
		return Double.parseDouble(serverVersion.substring(0, 3)) >= version;
	}

	protected void checkMinServerVersion(final double version) {
		Assume.assumeTrue(serverIsAtLeastVersion(version));
	}

	/**
	 * @param version
	 *            must be a major version, e.g. 1.8, 2,0, 2.2
	 * @return true if server is at least specified version
	 */
	protected boolean serverIsAtMostVersion(final double version) {
		String serverVersion = (String) getMongoClient().getDB("admin").command("serverStatus").get("version");
		return Double.parseDouble(serverVersion.substring(0, 3)) <= version;
	}

	protected void checkMaxServerVersion(final double version) {
		Assume.assumeTrue(serverIsAtMostVersion(version));
	}

	public AdvancedDatastore getAds() {
		return ads;
	}

	public DB getDb() {
		return db;
	}

	public Datastore getDs() {
		return ds;
	}

	public MongoClient getMongoClient() {
		return mongoClient;
	}

	public Morphia getMorphia() {
		return morphia;
	}

	public void setDb(final DB db) {
		this.db = db;
	}

	public void setDs(final Datastore ds) {
		this.ds = ds;
	}

	public void setAds(final AdvancedDatastore ads) {
		this.ads = ads;
	}

	public boolean isReplicaSet() {
		return runIsMaster().get("setName") != null;
	}

	private CommandResult runIsMaster() {
		// Check to see if this is a replica set... if not, get out of here.
		return mongoClient.getDB("admin").command(new BasicDBObject("ismaster", 1));
	}

}
