package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.OrderReceipt;

public class OrderBookEvent implements Event<OrderReceipt>{
	private Customer c;
	public void setCustomer(Customer c)
	{
		this.c=c;
	}
	public Customer getCustomer() {
		return c;
	}
	
	
	private String bookName;
	public void setbookName(String bName)
	{
		bookName=bName;
	}
	public String getbookName() {
		return bookName;
	}
	
}
