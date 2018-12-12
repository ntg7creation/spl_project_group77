package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;

public class CheckAvailabilityEventAndGetPrice implements Event<Integer> {

	private String title;

	public CheckAvailabilityEventAndGetPrice(String title){
		this.title= title;
	}
	public String getTitle(){
		return this.title;
	}

	}