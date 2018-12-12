package bgu.spl.mics.application.services;

import java.util.concurrent.TimeUnit;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetBookEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.Tick;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

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

	private int time;
	private MoneyRegister themoney;

	public SellingService(String name) {
		super(name);
		themoney = MoneyRegister.getInstance();
	}

	private Integer AskAvilabilityAndGetPrice() {
		Future<Integer> futureObject = sendEvent(new CheckAvailabilityEventAndGetPriceEvent("test"));
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
					"No Micro-Service has registered to handle CheckAvilabilityAndgetPrice events! The event cannot be processed");
		}

		return null;
	}

	private void buyBook(Integer price, Customer c, OrderReceipt r) {
		themoney.chargeCreditCard(c, price);
	}

	private BookInventoryInfo getBook(String name) {
		Future<BookInventoryInfo> futureObject = sendEvent(new GetBookEvent(name));
		if (futureObject != null) {
			BookInventoryInfo resolved = futureObject.get(100, TimeUnit.MILLISECONDS);
			if (resolved != null) {
				System.out.println("Completed processing the event, its result is \"" + resolved + "\" - success");
				return resolved;
			} else {
				System.out.println("Time has elapsed, no services has resolved the event - terminating");
			}
		} else {
			System.out.println(
					"No Micro-Service has registered to handle GetBookEvent events! The event cannot be processed");
		}
		return null;
	}

	@Override
	protected void initialize() {
		System.out.println("Event Handler " + getName() + " started");

		subscribeEvent(OrderBookEvent.class, ev -> { // so this is the call function of the ev event that is being sent
			System.out.println("Event Handler " + getName() + " got a new event "); // TODO delete
			Customer c = ev.getCustomer();
			OrderReceipt receipt = new OrderReceipt(8, getName(), c.getId(), ev.getbookName(), time);
			Integer price = AskAvilabilityAndGetPrice();
			BookInventoryInfo book = null;
			if (price != -1) {
				if (ev.getCustomer().getAvailableCreditAmount() > price) {
					receipt.setProcessTick(time);
					book = getBook(ev.getbookName());
					buyBook(price, ev.getCustomer(), receipt);
					receipt.setIssuedTick(time);
					sendBook(book, c);

				}
			}
			complete(ev, receipt);

		});
		subscribeBroadcast(Tick.class, ev -> {
			time = ev.getNewTime();// dont care for double threads
			if (time == 999999999) { // TODO Implement this i want to terminate to to change
				terminate();
			}
		});

	}

	private void sendBook(BookInventoryInfo book, Customer c) {
		Future<Object> futureObject = sendEvent(new DeliveryEvent(book, c));
	}

}
