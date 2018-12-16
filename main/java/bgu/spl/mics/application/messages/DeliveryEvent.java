package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;

public class DeliveryEvent implements Event<Boolean> {

	private BookInventoryInfo book;
	private Customer c;
	
	public DeliveryEvent(BookInventoryInfo book,Customer c) {
		this.book = book;
		this.c= c;
	}

	public BookInventoryInfo getBook() {
		return book;
	}
	
	public Customer getCustomer() {return c;}
}
