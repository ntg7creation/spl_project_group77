package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.GetBookEvent;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.OrderResult;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * InventoryService is in charge of the book inventory and stock. Holds a
 * reference to the {@link Inventory} singleton of the store. This class may not
 * hold references for objects which it is not responsible for:
 * {@link ResourcesHolder}, {@link MoneyRegister}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */

public class InventoryService extends MicroService {
	private Inventory inventory;

	public InventoryService() {
		super("Inventory");
		inventory = Inventory.getInstance();

		// TODO Implement this
	}

	@Override
	protected void initialize() {

		// subscribes to CheckAvailabilityAndGetPrice Event
		 subscribeEvent(CheckAvailabilityEventAndGetPriceEvent.class, event -> {
		int getPrice = inventory.checkAvailabiltyAndGetPrice(event.getTitle());
		complete(event, getPrice);
		 });
		//subscribe to getBook
	subscribeEvent(GetBookEvent.class, event -> {
			OrderResult orderRe =inventory.take(event.GetName());
			BookInventoryInfo output = new BookInventoryInfo(getBookTitle(), 1, this.price)

				 
			 

		});

//		});


		// TODO Implement this

	};

}