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
		//System.out.println("Event Handler " + getName() + " started");

		subscribeEvent(DeliveryEvent.class, ev -> { // so this is the call function of the ev event that is being sent
			//System.out.println("Event Handler " + getName() + " got a new event "); // TODO delete
			Customer c = ev.getCustomer();
			BookInventoryInfo book = ev.getBook();
			DeliveryVehicle deliveryVehilce = getVehicle();
			if (deliveryVehilce != null) {
				System.out.println("vhicle need to start driving");
				
				deliveryVehilce.deliver(c.getAddress(), c.getDistance());
			}	sendEvent(new ReturnVehicleEvent(deliveryVehilce));
			complete(ev, true);

		});

	}

	private DeliveryVehicle getVehicle() {
		Future<DeliveryVehicle> futureObject = sendEvent(new GetVehicleEvent());
		if (futureObject != null) {
			System.out.println("waiting for vhicletoget from future");
			DeliveryVehicle resolved = futureObject.get();
			if (resolved != null) {
				System.out.println(this.getName() + " processing the event, its result is \"" + resolved + "\" - success");
				return resolved;
			} else {
				System.out.println("the vehicleService failed to fech me a vehicle"); // shold never happen
			}
		} else {
			System.out.println(
					"No Micro-Service has registered to handle GetVehicleEvent events! The event cannot be processed");
		}

		return null;
	}

}
