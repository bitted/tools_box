package com.cyou.test;

import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.push;
import static org.mongodb.morphia.aggregation.Group.sum;
import static org.mongodb.morphia.aggregation.Projection.projection;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.aggregation.Group;
import org.mongodb.morphia.aggregation.Projection;
import org.mongodb.morphia.aggregation.Sort;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.query.MorphiaIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cyou.hithere.center.util.ThreadPoolExecutorTaskUtils;
import com.google.common.collect.Lists;
import com.mongodb.AggregationOptions;
import com.mongodb.DBCursor;

public class AggregationTest extends TestBase {
	Logger logger = LoggerFactory.getLogger("AggregationTest");

	@Test
	public void testSaveBook2() {
		ThreadPoolExecutorTaskUtils.getThreadPoolExecutor().execute(new Runnable() {
			@Override
			public void run() {
				Random random = new Random();
				for (int i = 0; i < 100000; i++) {
					getDs().save(new Book("mongodb title" + i, "username" + random.nextInt(20), random.nextInt(10)));
					System.out.println(i);
				}
				System.out.println("end task");
			}
		});
	}

	@Test
	public void testSaveBook() {
		Random random = new Random();
		for (int i = 0; i < 4000000; i++) {
			getDs().save(new Book("mongodb title" + i, "username" + random.nextInt(20), random.nextInt(10)));
			System.out.println(i);
		}

	}

	@Test
	public void testOut() {
//		getDb().dropDatabase();
		Random random = new Random();
		List<Book> list = Lists.newArrayList();
		for (int i = 0; i < 10000; i++) {
			for (int j = 0; j < 1000; j++) {
				list.add(new Book("mongodb title" + j, "username" + random.nextInt(20), random.nextInt(10)));
			}
			getDs().save(list);
			System.out.println(i);
		}

		AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();
		MorphiaIterator<Author, Author> aggregate = getDs().<Book, Author> createAggregation(Book.class)
				.group("author"/* 按照作者group */, grouping("books", push("title"))/* 使用累加器累加该作者的书名title到books集合中 */)
				.out(Author.class, options)/* 结果输出到author表中 */;
		System.out.println("==author集合大小为：" + getDs().getCollection(Author.class).count());
		Author author = aggregate.next();

		System.out.println("==打印author：" + author.books.size());
		getDs().<Book, Author> createAggregation(Book.class).group("author", grouping("books", push("title")))
				.out("different2", Author.class);// 按照author的集合结构存储到different2中
		System.out.println("==different2:" + getDb().getCollection("different2").count());
	}

	@Test
	public void testOutNamedCollection() {
		// getDb().dropDatabase();
		getDs().save(new Book("The Banquet", "Dante", 2, "Italian", "Sophomore Slump"),
				new Book("Divine Comedy", "Dante", 1, "Not Very Funny", "I mean for a 'comedy'", "Ironic"),
				new Book("Eclogues", "Dante", 2, "Italian", ""),
				new Book("The Odyssey", "Homer", 10, "Classic", "Mythology", "Sequel"),
				new Book("Iliad", "Homer", 10, "Mythology", "Trojan War", "No Sequel"));

		getDs().<Book, Author> createAggregation(Book.class)
		// 查询符合条件的记录
				.match(getDs().getQueryFactory().createQuery(getDs()).field("author").equal("Homer"))
				// 按照author排序，计算这个作者的copies的和保存到testAverage集合中
				.group("author", Group.grouping("copies", sum("copies"))).out("testAverage", Author.class);
		DBCursor testAverage = getDb().getCollection("testAverage").find();
		Assert.assertNotNull(testAverage);
		try {
			Assert.assertEquals(20, testAverage.next().get("copies"));
		} finally {
			testAverage.close();
		}
	}

	@Test
	public void testLimit() {
		getDs().save(new Book("The Banquet", "Dante", 2), new Book("Divine Comedy", "Dante", 1),
				new Book("Eclogues", "Dante", 2), new Book("The Odyssey", "Homer", 10), new Book("Iliad", "Homer", 10));

		MorphiaIterator<Book, Book> aggregate = getDs().<Book, Book> createAggregation(Book.class).limit(2).aggregate(Book.class);
		int count = 0;
		while (aggregate.hasNext()) {
			aggregate.next();
			count++;
		}
		Assert.assertEquals(2, count);
	}

	@Test
	public void testSkip() {
		getDs().save(new Book("The Banquet", "Dante", 2), new Book("Divine Comedy", "Dante", 1),
				new Book("Eclogues", "Dante", 2), new Book("The Odyssey", "Homer", 10), new Book("Iliad", "Homer", 10));

		MorphiaIterator<Book, Book> aggregate = getDs().<Book, Book> createAggregation(Book.class).skip(2).aggregate(Book.class);
		Book book = aggregate.next();
		Assert.assertEquals("Eclogues", book.title);
		Assert.assertEquals("Dante", book.author);
		Assert.assertEquals(2, book.copies.intValue());
	}

	@Test
	public void testUnwind() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		getDs().save(new User("jane", format.parse("2011-03-02"), "golf", "racquetball"),
				new User("joe", format.parse("2012-07-02"), "tennis", "golf", "swimming"));

		MorphiaIterator<User, User> aggregate = getDs().<User, User> createAggregation(User.class)
				.project(projection("_id").suppress(), projection("name"), projection("joined"), projection("likes"))
				// 分解数组likes，单独输出
				.unwind("likes").aggregate(User.class);
		int count = 0;
		while (aggregate.hasNext()) {
			User user = aggregate.next();
			switch (count) {
			case 0:
				Assert.assertEquals("jane", user.name);
				Assert.assertEquals("golf", user.likes.get(0));
				break;
			case 1:
				Assert.assertEquals("jane", user.name);
				Assert.assertEquals("racquetball", user.likes.get(0));
				break;
			case 2:
				Assert.assertEquals("joe", user.name);
				Assert.assertEquals("tennis", user.likes.get(0));
				break;
			case 3:
				Assert.assertEquals("joe", user.name);
				Assert.assertEquals("golf", user.likes.get(0));
				break;
			case 4:
				Assert.assertEquals("joe", user.name);
				Assert.assertEquals("swimming", user.likes.get(0));
				break;
			default:
				Assert.fail("Should only find 5 elements");
			}
			count++;
		}
	}

	@Test
	public void testSumResult() {
		AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();

		MorphiaIterator<SumResult, SumResult> aggregate = getDs()
				.<SumResult, SumResult> createAggregation(SumResult.class)
				// 按照author分组后copies求和
				.group("author", Group.grouping("copiesSum", Group.sum("copies")))
				.project(Projection.projection("_id").suppress(), Projection.projection("author", "_id"),
						Projection.projection("copiesSum")).aggregate(SumResult.class, options);
		int count = 0;
		while (aggregate.hasNext()) {
			SumResult result = aggregate.next();
			count = count + Integer.valueOf(result.copiesSum);
		}
		System.out.println(count);
	}

	@Test
	public void testSum() {
		AggregationOptions options = AggregationOptions.builder().outputMode(AggregationOptions.OutputMode.CURSOR).build();

		MorphiaIterator<Book, Book> aggregate = getDs()
				.<Book, Book> createAggregation(Book.class)
				// 按照author分组后copies求和
				.group("author", Group.grouping("copies", Group.sum("copies")))
				.project(Projection.projection("_id").suppress(), Projection.projection("author", "_id"),
						Projection.projection("copies")).sort(Sort.ascending("author")).out("sumresult", Book.class, options);
		System.out.println(aggregate.getCursor().count());
		// Projection.projection("copies")).sort(Sort.ascending("author")).out("sumresult",Book.class,options);
		// 这个游标可以直接输出结果集大小
		// System.out.println(aggregate.getCursor().count());

		// Projection.projection("copies")).sort(Sort.ascending("author")).aggregate(Book.class);//输出到book对象

		while (aggregate.hasNext()) {
			Book book = aggregate.next();
			System.out.println("author:" + book.author + ",copies=" + book.copies);
		}
	}

	@Test
	public void testProjection() {
		getDs().save(new Book("The Banquet", "Dante", 2), new Book("Divine Comedy", "Dante", 1),
				new Book("Eclogues", "Dante", 2), new Book("The Odyssey", "Homer", 10), new Book("Iliad", "Homer", 10));

		MorphiaIterator<Book, Book> aggregate = getDs().<Book, Book> createAggregation(Book.class)
		// 按照author分组后copies求和
				.group("author", Group.grouping("copies", Group.sum("copies")))
				// 映射author为主键，为sort排序准备
				.project(Projection.projection("_id").suppress(), Projection.projection("author", "_id"),
				// 按照copies字段除以5放到copies中
						Projection.projection("copies", Projection.divide(Projection.projection("copies"), 5)))
				// 按照author升序，降序在author前加-
				.sort(Sort.ascending("author")).aggregate(Book.class);
		Book book = aggregate.next();
		System.out.println(book);
		Assert.assertEquals("Dante", book.author);
		Assert.assertEquals(1, book.copies.intValue());
	}

	@Test
	public void testGeoNear() {
		// Given

		// When

		// Then
	}

	@Entity("sumresult")
	private static class SumResult {
		@Id
		private ObjectId id;
		private String copiesSum;
		private String author;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}

	@Entity(value = "books2", noClassnameStored = true)
	private static final class Book {
		@Id
		private ObjectId id;
		private String title;
		private String author;
		private Integer copies;
		private List<String> tags;

		private Book() {
		}

		private Book(final String title, final String author, final Integer copies, final String... tags) {
			this.title = title;
			this.author = author;
			this.copies = copies;
			this.tags = Arrays.asList(tags);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}

	@Entity("authors")
	private static class Author {
		@Id
		private String name;
		private List<String> books;

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}

	@Entity("users")
	private static final class User {
		@Id
		private ObjectId id;
		private String name;
		private Date joined;
		private List<String> likes;

		private User() {
		}

		private User(final String name, final Date joined, final String... likes) {
			this.name = name;
			this.joined = joined;
			this.likes = Arrays.asList(likes);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this);
		}
	}
}
