package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;

public class ReturnVehicleEnvent implements Event {

	private DeliveryVehicle toReturn;

	public ReturnVehicleEnvent(DeliveryVehicle toreturn) {
		this.toReturn = toreturn;
	}

	public DeliveryVehicle getVehicle() {
		return toReturn;
	}

}
