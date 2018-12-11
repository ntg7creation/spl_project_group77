package bgu.spl.mics.application.services;

import java.awt.print.Book;
import java.util.concurrent.TimeUnit;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvilabilityAndgetPrice;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;
import bgu.spl.mics.example.messages.ExampleBroadcast;
import bgu.spl.mics.example.messages.ExampleEvent;

/**
 * Selling service in charge of taking orders from customers. Holds a reference
 * to the {@link MoneyRegister} singleton of the store. Handles
 * {@link BookOrderEvent}. This class may not hold references for objects which
 * it is not responsible for: {@link ResourcesHolder}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */
public class SellingService extends MicroService {

	public SellingService() {
		super("Change_This_Name");
		// TODO Implement this
	}

	private int time;
	private MoneyRegister themoney;

	private Integer AskifAvilabil() {
		Future<Integer> futureObject = sendEvent(new CheckAvilabilityAndgetPrice());
		if (futureObject != null) {
			Integer resolved = futureObject.get(100, TimeUnit.MILLISECONDS);
			if (resolved != null) {
				System.out.println("Completed processing the event, its result is \"" + resolved + "\" - success");
				return resolved;
			} else {
				System.out.println("Time has elapsed, no services has resolved the event - terminating");
			}
		} else {
			System.out.println(
					"No Micro-Service has registered to handle ExampleEvent events! The event cannot be processed");
		}

		return null;
	}

	private OrderReceipt buyBook(Integer price, Customer c,OrderReceipt r) {
		themoney.chargeCreditCard(c, price);
	//	the
		return null;
	}

	private BookInventoryInfo getBook() {

		return null;
	}

	@Override
	protected void initialize() {
		System.out.println("Event Handler " + getName() + " started");

		subscribeEvent(OrderBookEvent.class, ev -> { // so this is the call function of the ev event that is being sent
			System.out.println("Event Handler " + getName() + " got a new event ");
			Integer price = AskifAvilabil();
			Customer c = ev.getCustomer();
			OrderReceipt receipt = new OrderReceipt(8, c.getId(), ev.getbookName(), time);
			BookInventoryInfo book = null;
			if (price != -1) {
				if (ev.getCustomer().getAvailableCreditAmount() > price) {
					book = getBook();
					buyBook(price, ev.getCustomer(),receipt);
					sendBook(book,c);
				}
			}
			complete(ev, receipt);
			if (false) { // i want to terminate
				terminate();
			}
		});

	}

	private void sendBook(BookInventoryInfo book, Customer c) {
//		sendEvent(new )
		
	}

}
