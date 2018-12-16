package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;

public class ReturnVehicleEvent implements Event<Boolean> {

	private DeliveryVehicle toReturn;

	public ReturnVehicleEvent(DeliveryVehicle toreturn) {
		this.toReturn = toreturn;
	}

	public DeliveryVehicle getVehicle() {
		return toReturn;
	}

}
