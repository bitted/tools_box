package com.cyou.hithere.center.service.impl;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.push;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.cyou.hithere.center.dao.CourseDao;
import com.cyou.hithere.center.dao.TeacherDao;
import com.cyou.hithere.center.dao.UserDAO;
import com.cyou.hithere.center.domain.Course;
import com.cyou.hithere.center.domain.OutSb;
import com.cyou.hithere.center.domain.Sb;
import com.cyou.hithere.center.domain.Teacher;
import com.cyou.hithere.center.domain.User;
import com.cyou.hithere.center.service.UserService;
import com.cyou.hithere.center.vo.BaseRet;
import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;

@Component("userService")
public class UserServiceImpl implements UserService {

	// 系统日志
	private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Resource
	private UserDAO userDao;

	@Resource
	private TeacherDao teacherDao;

	@Resource
	private CourseDao courseDao;

	@Override
	public String save() {
		long start = System.currentTimeMillis();
		List<User> list = Lists.newArrayList();
		BaseRet br = new BaseRet();
		Random r = new Random();
		for (int i = 1; i <= 1000000; i++) {
			User user = new User();
			user.setName("zhangsan" + i);
			user.setAge(20 + r.nextInt(10));
			user.setBirthday(new Date());
//			list.add(user);
			userDao.getDS().save(user);
			logger.info("====user=i=" + i);

		}
		long end = System.currentTimeMillis();
//		userDao.batchAdd(list);
//		logger.info("====user=" + list.size() + ",time=" + (end - start));
//		saveStudent();
//		saveCourse();
		return br.toString();
	}

	void saveStudent() {
		long start = System.currentTimeMillis();
		List<Teacher> slist = Lists.newArrayList();
		Random r = new Random();
		for (int i = 1; i <= 10; i++) {
			Teacher s = new Teacher();
			s.setName("s_" + i);
			s.setAge(r.nextInt(10) + 30);
			slist.add(s);
		}
		long end = System.currentTimeMillis();

		logger.info("====Teacher list=" + slist.size() + ",time=" + (end - start));
		teacherDao.batchAdd(slist);
	}

	void saveCourse() {
		long start = System.currentTimeMillis();
		List<Course> clist = Lists.newArrayList();
		Random r = new Random();
		for (int i = 1; i <= 5; i++) {
			Course c = new Course();
			c.setName("s_" + i);
			c.setScore(r.nextInt(20) + 80);
			clist.add(c);
		}
		long end = System.currentTimeMillis();

		logger.info("====Course list=" + clist.size() + ",time=" + (end - start));
		courseDao.batchAdd(clist);
	}

	@Override
	public String list(String id) {
		BaseRet br = new BaseRet();
		List<User> list = Lists.newArrayList();

		Query<User> query = userDao.getDS().createQuery(User.class);
		if (StringUtils.isNoneEmpty(id)) {
			query.field("_id").equal(new ObjectId(id));
		}
		list = query.asList();
		br.putData("list", list);
		return br.toString();
	}

	@Override
	public String del(String id) {
		BaseRet br = new BaseRet();
		Query<User> query = userDao.getDS().createQuery(User.class);
		if (StringUtils.isNoneEmpty(id)) {
			query.field("_id").equal(new ObjectId(id));
		}
		userDao.delete(query);
		br.putData("flag", 1);
		return br.toString();
	}

	@Override
	public String update(String id, String name) {
		BaseRet br = new BaseRet();

		Query<User> query = userDao.getDS().createQuery(User.class);
		if (StringUtils.isNoneEmpty(id)) {
			query.field("_id").equal(new ObjectId(id));
		}
		UpdateOperations<User> op = userDao.getDS().createUpdateOperations(User.class);
		op.set("name", name);

		userDao.update(query, op);
		br.putData("status", 1);
		return br.toString();
	}

	@Override
	public String aggregate() {
		Long start = System.currentTimeMillis();
		BaseRet br = new BaseRet();
		AggregationOptions options = AggregationOptions.builder().allowDiskUse(true).batchSize(100000)
				.outputMode(AggregationOptions.OutputMode.CURSOR).build();
		MorphiaIterator<OutSb, OutSb> aggregate = userDao.getDS().<Sb, OutSb> createAggregation(Sb.class)
				.group("c", grouping("cs", push("c")), grouping("pads", push("pad"))).out(OutSb.class, options);
		Long end1 = System.currentTimeMillis();
		logger.info("time1 is =" + (end1 - start) / 1000);

		// MorphiaIterator<OutUser, OutUser> aggregate = userDao.getDS().<User, OutUser> createAggregation(User.class)
		// .group("age", grouping("names", push("name")), grouping("birthdays", push("birthday"))).out(OutUser.class, options);

		// while (aggregate.hasNext()) {
		// OutSb out = aggregate.next();
		// br.putData(out.getK(), out);
		// }
		// br.putData("count", aggregate.getCursor().count());
		br.putData("count", userDao.getDS().createQuery(OutSb.class).countAll());
		Long end2 = System.currentTimeMillis();
		logger.info("time2 is =" + (end2 - start) / 1000);
		return br.toString();
	}
	// public String aggregate() {
	// BaseRet br = new BaseRet();
	// AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
	// MorphiaIterator<OutUser, OutUser> aggregate = userDao.getDS().<User, OutUser> createAggregation(User.class)
	// .group("age", grouping("names", push("name")), grouping("birthdays", push("birthday"))).out(OutUser.class, options);
	// // MorphiaIterator<OutUser, OutUser> aggregate = userDao.getDS().<User, OutUser> createAggregation(User.class)
	// // .group("age").out(OutUser.class, options);
	//
	// while (aggregate.hasNext()) {
	// OutUser out = aggregate.next();
	// br.putData(out.getAge(), out);
	// }
	//
	// return br.toString();
	// }
}
