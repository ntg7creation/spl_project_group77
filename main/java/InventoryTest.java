import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.Test;

import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Inventory;

public class InventoryTest {

	Inventory i;
	BookInventoryInfo bookinv1;
	BookInventoryInfo bookinv2;

	@After
	public void clean() {
		bookinv1 = null;
		bookinv2 = null;
		i = null;
	}

	@Test
	public void testGetInstance() {
		try {
			Inventory inv = Inventory.getInstance();
			assertNotNull(inv);
		} catch (Exception e) {
			fail("Fail to get MessageBus");
		}
	}

	@Test
	public void testLoad() {
		i = Inventory.getInstance();
		bookinv1 = new BookInventoryInfo("Harry", 5, 400);
		bookinv2 = new BookInventoryInfo("Potter", 3, 200);
		BookInventoryInfo[] b = { bookinv1, bookinv2 };
		try {
			i.load(b);
			

		} catch (Exception e) {
			fail("did not load");
		}

	}

	@Test
	public void testTake() {
		i = Inventory.getInstance();
		bookinv1 = new BookInventoryInfo("Harry", 5, 400);
		bookinv2 = new BookInventoryInfo("Potter", 3, 200);
		BookInventoryInfo[] b = { bookinv1, bookinv2 };
		i.load(b);
		assertNotNull(i.take("Harry"));
		assertNotNull(i.take("Potter"));


	}

	@Test
	public void testCheckAvailabiltyAndGetPrice() {
		i = Inventory.getInstance();
		bookinv1 = new BookInventoryInfo("Harry", 5, 400);
		bookinv2 = new BookInventoryInfo("Potter", 3, 200);
		BookInventoryInfo[] b = { bookinv1, bookinv2 };
		i.load(b);
		assertTrue (i.checkAvailabiltyAndGetPrice(bookinv1.getBookTitle()) == bookinv1.getPrice());
		assertTrue(i.checkAvailabiltyAndGetPrice("none exist")== -1);
	}



}
