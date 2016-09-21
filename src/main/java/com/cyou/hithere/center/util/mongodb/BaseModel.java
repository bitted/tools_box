package com.cyou.hithere.center.util.mongodb;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Id;

public class BaseModel {

	//主键
	@Id
	private ObjectId id;

	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}
	
}
