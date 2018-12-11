package bgu.spl.mics.application.passiveObjects;

/**
 * Passive data-object representing a receipt that should be sent to a customer
 * after the completion of a BookOrderEvent. You must not alter any of the given
 * public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public
 * methods).
 */
public class OrderReceipt {

	private int order_id;
	private String seller;
	private int customer_id;
	private String book_title;
	private int price;
	private int order_tick;
	private int sold_tick;
	private int process_tick;

	public OrderReceipt(int orderid, int customer_id, String book_title, int ordertick) {
		order_id = orderid;
		this.customer_id = customer_id;
		this.book_title = book_title;
		order_tick = ordertick;
	}

	/**
	 * Retrieves the orderId of this receipt.
	 */
	public int getOrderId() {
		return order_id;
	}

	/**
	 * Retrieves the name of the selling service which handled the order.
	 */
	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}

	/**
	 * Retrieves the ID of the customer to which this receipt is issued to.
	 * <p>
	 * 
	 * @return the ID of the customer
	 */
	public int getCustomerId() {
		return customer_id;
	}

	/**
	 * Retrieves the name of the book which was bought.
	 */
	public String getBookTitle() {
		return book_title;
	}

	/**
	 * Retrieves the price the customer paid for the book.
	 */
	public int getPrice() {
		return price;
	}

	public void setPrice(int p) {
		price = p;
	}

	/**
	 * Retrieves the tick in which this receipt was issued.
	 */
	public int getIssuedTick() {
		return sold_tick;
	}

	public void setIssuedTick(int I) {
		sold_tick = I;
	}

	/**
	 * Retrieves the tick in which the customer sent the purchase request.
	 */
	public int getOrderTick() {
		return order_tick;
	}

	/**
	 * Retrieves the tick in which the treating selling service started processing
	 * the order.
	 */
	public int getProcessTick() {
		return process_tick;
	}

	public void setProcessTick(int p) {
		process_tick = p;
	}
}
