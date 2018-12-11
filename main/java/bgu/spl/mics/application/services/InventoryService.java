package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.passiveObjects.Inventory;

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
		// subscribeEvent(CheckAvailabilityEvent.class, event -> {
		// getPrice = inventory.checkAvailabiltyAndGetPrice(event.getBook().getBookTitle());
		// complete

		// subscribes to CheckAvailability Event
		// subscribeBroadcast(TickBroadCast.class, getTick -> {
		// currTick=getTick.getTime();
		// complete


		// TODO Implement this

	}

}
