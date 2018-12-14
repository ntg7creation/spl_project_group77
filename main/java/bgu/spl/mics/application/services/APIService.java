package bgu.spl.mics.application.services;

import java.util.HashMap;

import bgu.spl.mics.Event;
import bgu.spl.mics.MicroService;
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
		super("APIService : " + customer.getId());
		this.customer = customer;
		order = booksTicks;

		// TODO Implement this
	}

	@Override
	protected void initialize() {
		
		subscribeBroadcast(Tick.class, broad-> { 
		    Integer hourOfDay = new Integer( broad.getNewTime());
		    while(order.containsKey(hourOfDay))
		    {
		    	String bookName = order.remove(hourOfDay);
		    	if(bookName == null)
		    		System.out.println("error in APIService");
		    	sendEvent(new OrderBookEvent(customer,bookName));
		    }

		});

	}

}
