package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvailabilityAndgetPrice;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * InventoryService is in charge of the book inventory and stock.
 * Holds a reference to the {@link Inventory} singleton of the store.
 * This class may not hold references for objects which it is not responsible for:
 * {@link ResourcesHolder}, {@link MoneyRegister}.
 * 
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */

public class InventoryService extends MicroService{
	private Inventory inventory;


	public InventoryService() {
		super("Inventory");
		inventory = Inventory.getInstance();

		// TODO Implement this
	}

	@Override
	protected void initialize() {

		// subscribes to CheckAvailability Event
		 subscribeEvent(CheckAvailabilityAndgetPrice.class, event -> {
		int getPrice = inventory.checkAvailabiltyAndGetPrice(event.getBookTitle());
		// complete (ev,null)

		// subscribes to TickBroadCast. Event //Check again if necessary
		// subscribeBroadcast(TickBroadCast.class, getTick -> {
		// currTick=getTick.getTime();
		// complete
		subscribeEvent(CheckAvailabilityAndgetPrice.class, ev -> { // so this is the call function of the ev event that is being sent
			System.out.println("Event Handler " + getName() + " got a new event ");
			complete(ev,null);
						

		});


		// TODO Implement this

	}

}
