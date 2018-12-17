import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static java.time.Duration.ofSeconds;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bgu.spl.mics.Future;

public class Future_Test {

	Future<String> f;

	@Before
	public void setup() {
		f = new Future<String>();
	}

	@After
	public void clean() {
		f = null;
	}

	@Test
	public void testFuture() {
		try {
			f = new Future<String>();
		} catch (Exception e) {
			fail("cant creat Future");
		}
	}

	@Test
	public void testGet() {
		String value = f.get();
		if (value != null)
			fail("not null");
		f.resolve("strong");
		value = f.get();
		assertNotNull(value);
		if (!value.equals("strong"))
			fail("f is not strong");

	}

	@Test
	public void testResolve() {
		try {
			f.resolve("resolve test");
		} catch (Exception e) {
			fail("no resolve");
		}
	}

	@Test
	public void testIsDone() {
		assertFalse(f.isDone());
		f.resolve("done");
		assertTrue(f.isDone());
	}

	@Test
	public void testGetLongTimeUnit() {

		String value = f.get((long) 5, TimeUnit.SECONDS);
		assertNull(value);
		assertTimeout(ofSeconds(15), () -> {
			f.get((long) 10, TimeUnit.SECONDS);
		});
		f.resolve("test");
		// value = f.get(timeout, unit);
	}

}
