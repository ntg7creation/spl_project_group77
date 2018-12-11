package bgu.spl.mics.application.services;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.DeliveryEvent;
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

<<<<<<< HEAD
	public ResourceService(String Myname) {
		super(Myname);
		this.ResourcesHolder = ResourcesHolder.getInstance();
		
=======
	public ResourceService(ResourcesHolder holder) {
		super("ResourceService");
		this.ResourcesHolder = holder;

>>>>>>> branch 'ex2' of https://github.com/ntg7creation/spl_project_group77.git
		// TODO Implement this
	}

	@Override
	protected void initialize() {

		// subscribes to GetVehicleEvent/ Check again this Class
		subscribeEvent(DeliveryEvent.class, ev -> {
			// TODO Implement this

			
			complete(ev, null);
		});
	}
}
