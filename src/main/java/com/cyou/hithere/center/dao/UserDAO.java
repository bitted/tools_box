package com.cyou.hithere.center.dao;

import org.springframework.stereotype.Repository;

import com.cyou.hithere.center.domain.User;
import com.cyou.hithere.center.util.mongodb.MongoBaseDAO;

@Repository
public class UserDAO extends MongoBaseDAO<User> {

}
