package bgu.spl.mics.application.services;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import bgu.spl.mics.Event;
import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.Tick;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * APIService is in charge of the connection between a client and the store. It
 * informs the store about desired purchases using {@link BookOrderEvent}. This
 * class may not hold references for objects which it is not responsible for:
 * {@link ResourcesHolder}, {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */
public class APIService extends MicroService {
	private HashMap<Integer, String> order;
	private Customer customer;

	public APIService(Customer customer, HashMap<Integer, String> booksTicks) {
		super("APIService :" + customer.getId());
		this.customer = customer;
		order = booksTicks;

		// TODO Implement this
	}

	@Override
	protected void initialize() {

		subscribeBroadcast(Tick.class, broad -> {
			Integer hourOfDay = new Integer(broad.getNewTime());
			while (order.containsKey(hourOfDay)) {
				String bookName = order.remove(hourOfDay);
				OrderReceipt R = orderBook(bookName);
				if (R != null)
					customer.addReceipt(R);
			}

		});

	}

	private OrderReceipt orderBook(String bookName) {

		Future<OrderReceipt> futureObject = sendEvent(new OrderBookEvent(customer, bookName));

		if (futureObject != null) {
			OrderReceipt resolved = futureObject.get();
			if (resolved != null) {
			//	System.out.println(this.getName() + " processing the event, its result is \"" + resolved + "\" - success");
				return resolved;
			} else {
				//System.out.println(this.getName() + ":cant resolved event");
			}
		} else {
			System.out.println(
					"No Micro-Service has registered to handle OrderBookEvent events! The event cannot be processed");
		}

		return null;
	}

}
