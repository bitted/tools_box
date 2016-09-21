package com.cyou.test;

import static java.lang.String.format;
import static org.mongodb.morphia.aggregation.Group.average;
import static org.mongodb.morphia.aggregation.Group.first;
import static org.mongodb.morphia.aggregation.Group.grouping;
import static org.mongodb.morphia.aggregation.Group.id;
import static org.mongodb.morphia.aggregation.Group.last;
import static org.mongodb.morphia.aggregation.Group.sum;
import static org.mongodb.morphia.aggregation.Projection.projection;
import static org.mongodb.morphia.aggregation.Sort.ascending;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Test;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.annotations.AlsoLoad;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.logging.Logger;
import org.mongodb.morphia.logging.MorphiaLoggerFactory;
import org.mongodb.morphia.query.MorphiaIterator;
import org.mongodb.morphia.query.Query;

/**
 * These tests recreate the example zip code data set aggregations as found in the official documentation.
 * 
 * @see <a href="http://docs.mongodb.org/manual/tutorial/aggregation-zip-code-data-set/">Aggregation with the Zip Code Data
 *      Set</a>
 */
public class ZipCodeDataSetTest extends TestBase {
	private static final Logger LOG = MorphiaLoggerFactory.get(ZipCodeDataSetTest.class);

	/**
	 * populationsAbove10M(
	 * 
	 * db.zipcodes.aggregate( [
	 * { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
	 * { $match: { totalPop: { $gte: 10*1000*1000 } } }
	 * ] )
	 * 
	 * )
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws InterruptedException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void populationsAbove10M() throws IOException, TimeoutException, InterruptedException {
		Query<Object> query = getDs().getQueryFactory().createQuery(getDs());

		AggregationPipeline<City, Population> pipeline = getDs().<City, Population> createAggregation(City.class)
				.group("state", grouping("totalPop", sum("pop"))).match(query.field("totalPop").greaterThanOrEq(10000000));
		validate(pipeline.aggregate(Population.class), "CA", 29754890);
		validate(pipeline.aggregate(Population.class), "OH", 10846517);
	}

	/**
	 * averageCitySizeByState(
	 * 
	 * db.zipcodes.aggregate( [
	 * { $group: { _id: { state: "$state", city: "$city" }, pop: { $sum: "$pop" } } },
	 * { $group: { _id: "$_id.state", avgCityPop: { $avg: "$pop" } } }
	 * ] )
	 * 
	 * )
	 * 
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void averageCitySizeByState() throws InterruptedException, TimeoutException, IOException {
		AggregationPipeline<City, Population> pipeline = getDs().<City, Population> createAggregation(City.class)
				.group(id(grouping("state"), grouping("city")), grouping("pop", sum("pop")))
				.group("_id.state", grouping("avgCityPop", average("pop")));
		validate(pipeline.aggregate(Population.class), "MN", 5372);
	}

	/**
	 * smallestAndLargestCities(
	 * 
	 * db.zipcodes.aggregate( [
	 * { $group:
	 * {
	 * _id: { state: "$state", city: "$city" },
	 * pop: { $sum: "$pop" }
	 * }
	 * },
	 * { $sort: { pop: 1 } },
	 * { $group:
	 * {
	 * _id : "$_id.state",
	 * biggestCity: { $last: "$_id.city" },
	 * biggestPop: { $last: "$pop" },
	 * smallestCity: { $first: "$_id.city" },
	 * smallestPop: { $first: "$pop" }
	 * }
	 * },
	 * 
	 * // the following $project is optional, and
	 * // modifies the output format.
	 * 
	 * { $project:
	 * { _id: 0,
	 * state: "$_id",
	 * biggestCity: { name: "$biggestCity", pop: "$biggestPop" },
	 * smallestCity: { name: "$smallestCity", pop: "$smallestPop" }
	 * }
	 * }
	 * ] )
	 * 
	 * )
	 * 
	 * @throws InterruptedException
	 * @throws TimeoutException
	 * @throws IOException
	 *             返回类型：void
	 * @exception
	 * @since 1.0.0
	 */
	@Test
	public void smallestAndLargestCities() throws InterruptedException, TimeoutException, IOException {
		getMorphia().mapPackage(getClass().getPackage().getName());
		AggregationPipeline<City, State> pipeline = getDs().<City, State> createAggregation(City.class)
				//
				.group(id(grouping("state"), grouping("city")), grouping("pop", sum("pop")))
				.sort(ascending("pop"))
				.group("_id.state", grouping("biggestCity", last("_id.city")), grouping("biggestPop", last("pop")),
						grouping("smallestCity", first("_id.city")), grouping("smallestPop", first("pop")))
				// 映射State输出格式
				.project(projection("_id").suppress(), projection("state", "_id"),
						projection("biggestCity", projection("name", "biggestCity"), projection("pop", "biggestPop")),
						projection("smallestCity", projection("name", "smallestCity"), projection("pop", "smallestPop")));

		MorphiaIterator<State, State> iterator = pipeline.aggregate(State.class);
		Map<String, State> states = new HashMap<String, State>();
		while (iterator.hasNext()) {
			State state = iterator.next();
			System.out.println("\n" + state);
			states.put(state.getState(), state);
		}

		State state = states.get("SD");

		Assert.assertEquals("SIOUX FALLS", state.getBiggest().name);
		Assert.assertEquals(102046, state.getBiggest().population.longValue());

		Assert.assertEquals("ZEONA", state.getSmallest().name);
		Assert.assertEquals(8, state.getSmallest().population.longValue());
		iterator.close();
	}

	private void validate(final MorphiaIterator<Population, Population> pipeline, final String state, final long value) {
		boolean found = false;
		for (Population population : pipeline) {
			if (population.state.equals(state)) {
				found = true;
				Assert.assertEquals(new Long(value), population.population);
			}
			LOG.info("population = " + population);
		}
		Assert.assertTrue("Should have found " + state, found);
		pipeline.close();
	}

	@Entity(value = "zipcodes", noClassnameStored = true)
	public static final class City {
		@Id
		private String id;
		@Property("city")
		private String name;
		@Property("loc")
		private double[] location;
		@Property("pop")
		private Double population;
		@Property("state")
		private String state;

		private City() {
		}

		@Override
		public String toString() {
			return format("City{id='%s', name='%s', location=%s, population=%s, state='%s'}", id, name,
					Arrays.toString(location), population, state);
		}
	}

	@Entity
	public static class Population {
		@Id
		private String state;
		@Property("totalPop")
		@AlsoLoad("avgCityPop")
		private Long population;

		@Override
		public String toString() {
			return String.format("Population{population=%d, state='%s'}", population, state);
		}
	}

	@Embedded
	public static class CityPopulation {
		@Property("name")
		private String name;
		@Property("pop")
		private Long population;

		@Override
		public String toString() {
			return String.format("CityPopulation{name='%s', population=%d}", name, population);
		}
	}

	@Entity
	public static class State {
		@Id
		private ObjectId id;
		@Property("state")
		private String state;
		@Embedded("biggestCity")
		private CityPopulation biggest;
		@Embedded("smallestCity")
		private CityPopulation smallest;

		public CityPopulation getBiggest() {
			return biggest;
		}

		public CityPopulation getSmallest() {
			return smallest;
		}

		public String getState() {
			return state;
		}

		@Override
		public String toString() {
			return String.format("State{state='%s', biggest=%s, smallest=%s}", state, biggest, smallest);
		}
	}
}
