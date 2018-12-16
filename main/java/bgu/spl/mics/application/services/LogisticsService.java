package bgu.spl.mics.application.services;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetVehicleEvent;
import bgu.spl.mics.application.messages.ReturnVehicleEvent;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * Logistic service in charge of delivering books that have been purchased to
 * customers. Handles {@link DeliveryEvent}. This class may not hold references
 * for objects which it is not responsible for: {@link ResourcesHolder},
 * {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */
public class LogisticsService extends MicroService {

	public LogisticsService(String name) {
		super(name);

	}

	@Override
	protected void initialize() {

		subscribeEvent(DeliveryEvent.class, ev -> {
			Customer c = ev.getCustomer();
			BookInventoryInfo book = ev.getBook();
			DeliveryVehicle deliveryVehilce = openBox(getVehicle());
			if (deliveryVehilce != null)
				deliveryVehilce.deliver(c.getAddress(), c.getDistance());

			sendEvent(new ReturnVehicleEvent(deliveryVehilce));
			complete(ev, true);

		});

	}

	private Future<DeliveryVehicle> getVehicle() {

		Future<Future<DeliveryVehicle>> futureObject = sendEvent(new GetVehicleEvent());
		if (futureObject != null) {
			Future<DeliveryVehicle> resolved = futureObject.get();
			if (resolved != null) {
				return resolved;
			} else {
			}
		} else {
		}

		return null;
	}

	private DeliveryVehicle openBox(Future<DeliveryVehicle> futureObject) {
		if (futureObject != null) {
			DeliveryVehicle resolved = futureObject.get();
			if (resolved != null) {
				return resolved;
			} else {
			}
		} else {
		}

		return null;
	}

}
