package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.example.messages.ExampleEvent;

/**
 * Selling service in charge of taking orders from customers.
 * Holds a reference to the {@link MoneyRegister} singleton of the store.
 * Handles {@link BookOrderEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link ResourcesHolder}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class SellingService extends MicroService{

	public SellingService() {
		super("Change_This_Name");
		// TODO Implement this
	}

	@Override
	protected void initialize() {
        System.out.println("Event Handler " + getName() + " started");
        
        subscribeEvent(OrderBookEvent.class, ev -> {
            System.out.println("Event Handler " + getName() + " got a new event ");
            BookInventoryInfo info = new BookInventoryInfo("microBookTest",2,50);
            complete(ev, info);
            if (false) { // i want to terminate
                terminate();
            }
        });
		
	}

}
