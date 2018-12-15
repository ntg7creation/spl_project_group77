package bgu.spl.mics.application.services;

import bgu.spl.mics.Future;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetVehicleEvent;
import bgu.spl.mics.application.messages.ReturnVehicleEvent;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * ResourceService is in charge of the store resources - the delivery vehicles.
 * Holds a reference to the {@link ResourceHolder} singleton of the store. This
 * class may not hold references for objects which it is not responsible for:
 * {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */
public class ResourceService extends MicroService {
	private ResourcesHolder ResourcesHolder;

	//g

	public ResourceService(String Myname) {
		super(Myname);
		this.ResourcesHolder = ResourcesHolder.getInstance();

		// TODO Implement this
	}

	@Override
	protected void initialize() {

		// subscribe to getVehicleEvent

		subscribeEvent(GetVehicleEvent.class, ev->{
			Future <DeliveryVehicle> future =  ResourcesHolder.getInstance().acquireVehicle();
			complete(ev,future.get());

		});

		// subscribe to ReturnVehicleEvent

		subscribeEvent(ReturnVehicleEvent.class, ev->{
			ResourcesHolder.getInstance().releaseVehicle(ev.getVehicle());
			complete(ev,true);
		});




	};
}


