package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;

public class CheckAvailabilityEvent implements Event<Integer> {

	private String title;
	private int price;

	public CheckAvailabilityEvent(String title){
		this.title= title;
	}
	public String getTitle(){
		return this.title;
	}

	public int getPrice(){
		return this.price;

		}
	}