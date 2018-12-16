package bgu.spl.mics.application.passiveObjects;

/**
 * Passive data-object representing a information about a certain book in the inventory.
 * You must not alter any of the given public methods of this class. 
 * <p>
 * You may add fields and methods to this class as you see fit (including public methods).
 */
public class BookInventoryInfo {
	private String title;
	private int amount;
	private int price;

	
	public BookInventoryInfo (String title, int amount, int price) {
		this.title= title;
		this.amount=amount;
		this.price=price;
		
	}
	
	
	public void addBooks(int x) {
		amount+= x;
	}
	
	public Boolean removeBooks(int x)
	{
		if(amount<x)
			return false;
		amount-=x;
		return true;
	}
	
	
	/**
     * Retrieves the title of this book.
     * <p>
     * @return The title of this book.   
     */
	public String getBookTitle() {
		
		return title;

	}

	/**
     * Retrieves the amount of books of this type in the inventory.
     * <p>
     * @return amount of available books.      
     */
	public int getAmountInInventory() {

		return amount;
	}

	/**
     * Retrieves the price for  book.
     * <p>
     * @return the price of the book.
     */
	public int getPrice() {

		return price;
	}
	
	@Override
	public String toString()
	{
		return "[title:" +title + ",amount:" +amount+",price:"+price+"]" ;
	}
	
	

	
}
