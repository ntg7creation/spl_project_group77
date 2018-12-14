package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;

public class OrderBookEvent implements Event<OrderReceipt> {

	public OrderBookEvent(Customer c, String bookName) {
		this.c = c;
		this.bookName = bookName;
	}

	private Customer c;

	public Customer getCustomer() {
		return c;
	}

	private String bookName;

	public String getbookName() {
		return bookName;
	}

}
