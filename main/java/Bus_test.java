import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import bgu.spl.mics.Event;
import bgu.spl.mics.MessageBus;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.services.SellingService;
import bgu.spl.mics.example.messages.ExampleEvent;

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
		MicroService ms = new SellingService();
		Event<String> ev = new ExampleEvent("test MicroService");
		

	}

	@Test
	public void testSubscribeBroadcast() {
		fail("Not yet implemented");
	}

	@Test
	public void testComplete() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendBroadcast() {
		fail("Not yet implemented");
	}

	@Test
	public void testSendEvent() {
		fail("Not yet implemented");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testUnregister() {
		fail("Not yet implemented");
	}

	@Test
	public void testAwaitMessage() {
		fail("Not yet implemented");
	}

}
