package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;

public class CheckAvailabilityEventAndGetPriceEvent implements Event<Integer> {

	private String title;

	public CheckAvailabilityEventAndGetPriceEvent(String title){
		this.title= title;
	}
	public String getTitle(){
		return this.title;
	}

	}