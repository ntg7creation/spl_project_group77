package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;

public class GetVehicleEvent implements Event<DeliveryVehicle> {
	   private DeliveryVehicle vcl;
	    public GetVehicleEvent(DeliveryVehicle vcl){
	        this.vcl = vcl;
	    }

	    public DeliveryVehicle getVehicle(){
	        return this.vcl;
	    }
	}