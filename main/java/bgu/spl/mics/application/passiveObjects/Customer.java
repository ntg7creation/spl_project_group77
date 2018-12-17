package bgu.spl.mics.application.passiveObjects;

import java.util.LinkedList;
import java.util.List;

/**
 * Passive data-object representing a customer of the store. You must not alter
 * any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public
 * methods).
 */
public class Customer {

	private String name;
	private int id;
	private String address;
	private int distance;
	private List<OrderReceipt> myorders;
	private CreditCard creditCard;
	private OrderSchedule[] orderSchedule;


	public Customer(String name, int id, String address, int distance, int creditstart, int creditnumber) {
		this.name = name;
		this.id = id;
		this.address = address;
		this.distance = distance;
		myorders = new LinkedList<OrderReceipt>();
		creditCard = new CreditCard();
		creditCard.setAmount(creditstart);
		creditCard.setNumber(creditnumber);
	}

	/**
	 * Retrieves the name of the customer.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the ID of the customer .
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retrieves the address of the customer.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Retrieves the distance of the customer from the store.
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Retrieves a list of receipts for the purchases this customer has made.
	 * <p>
	 * 
	 * @return A list of receipts.
	 */
	public List<OrderReceipt> getCustomerReceiptList() {
		return myorders;
	}

	public void addReceipt(OrderReceipt o) {
		myorders.add(o);
	}

	/**
	 * Retrieves the amount of money left on this customers credit card.
	 * <p>
	 * 
	 * @return Amount of money left.
	 */
	synchronized public int getAvailableCreditAmount() {
		return creditCard.getAmount();
	}

	synchronized public Boolean removeCredit(int x) {
		if (creditCard.getAmount() < x)
			return false;
		creditCard.setAmount(creditCard.getAmount() -x);
		return true;
	}

	/**
	 * Retrieves this customers credit card serial number.
	 */
	public int getCreditNumber() {
		return creditCard.getNumber();
	}

	public OrderSchedule[] getOrderSchedule() {
		return orderSchedule;
	}

	public void setOrderSchedule(OrderSchedule[] orderSchedule) {
		this.orderSchedule = orderSchedule;
	}

}
