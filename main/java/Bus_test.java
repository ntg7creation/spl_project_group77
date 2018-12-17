import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bgu.spl.mics.MessageBus;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.services.SellingService;

public class Bus_test {

	private MessageBus m;

	@Before
	public void setUp() {
		m = MessageBusImpl.getInstance();
	}

	@After
	public void CleanUp() {

	}

	@Test
	public void testgetInstance() {
		try {
			m = MessageBusImpl.getInstance();
			assertNotNull(m);
		} catch (Exception e) {
			fail("Fail to get MessageBus");
		}
	}

	@Test
	public void testSubscribeEvent() {
		MicroService ms = new SellingService("test sellingService");
		Thread tms = new Thread(ms);
		tms.start();
		try {
			synchronized (this) {
				this.wait(1000);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CheckAvailabilityEventAndGetPriceEvent ev = new CheckAvailabilityEventAndGetPriceEvent("string");
		m.subscribeEvent(OrderBookEvent.class, ms);
		assertNull(m.sendEvent(ev));
		OrderBookEvent ev2 = new OrderBookEvent(new Customer("tes", 1, "testadd", 5, 9, 91), "testbook");
		m.subscribeEvent(OrderBookEvent.class, ms);
		assertNotNull(m.sendEvent(ev2));
		tms.interrupt();
	}



}
