package bgu.spl.mics.application.services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.Tick;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;
import bgu.spl.mics.application.passiveObjects.OrderSchedule;
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
	private HashMap<Integer, List<String>> order;
	private Customer customer;

	public APIService(Customer customer) {
		super("APIService :" + customer.getId());
		this.customer = customer;
		order = new HashMap<>();

	}

	public APIService(Customer customer, OrderSchedule[] booksTicks) {
		super("APIService :" + customer.getId());
		order = new HashMap<>();
		for (OrderSchedule orderSchedule : booksTicks) {
			if (!order.containsKey(orderSchedule.getTick()))
				order.put(orderSchedule.getTick(), new LinkedList<String>());
			order.get(orderSchedule.getTick()).add(orderSchedule.getBookTitle());

		}
		this.customer = customer;

	}

	@Override
	protected void initialize() {

		subscribeBroadcast(Tick.class, broad -> {
			Integer hourOfDay = new Integer(broad.getNewTime());
			if (order.containsKey(hourOfDay)) {
				while (!order.get(hourOfDay).isEmpty()) {
					String bookName = order.get(hourOfDay).remove(0);
					OrderReceipt R = orderBook(bookName);
					if (R != null)
						customer.addReceipt(R);
				}
			}
		});

	}

	private OrderReceipt orderBook(String bookName) {

		System.out.println(this.getName() + " ordring " + bookName);
		Future<OrderReceipt> futureObject = sendEvent(new OrderBookEvent(customer, bookName));

		if (futureObject != null) {
			OrderReceipt resolved = futureObject.get();
			if (resolved != null) {
				// System.out.println(
				// this.getName() + " processing the event, its result is \"" + resolved + "\" -
				// success");
				return resolved;
			} else {
				// System.out.println(this.getName() + ":cant resolved event");
			}
		} else {
			System.out.println(
					"No Micro-Service has registered to handle OrderBookEvent events! The event cannot be processed");
		}

		return null;
	}

}
