package bgu.spl.mics.application.passiveObjects;

/**
 * Passive data-object representing a information about a certain book in the
 * inventory. You must not alter any of the given public methods of this class.
 * <p>
 * You may add fields and methods to this class as you see fit (including public
 * methods).
 */
public class BookInventoryInfo {
	private String bookTitle;
	private int amount;
	private int price;

<<<<<<< HEAD
	
	public BookInventoryInfo (String title, int amount, int price) {
		this.bookTitle= title;
		this.amount=amount;
		this.price=price;
		
=======
	public BookInventoryInfo(String title, int amount, int price) {
		this.title = title;
		this.amount = amount;
		this.price = price;

>>>>>>> branch 'ex2' of https://github.com/ntg7creation/spl_project_group77.git
	}
<<<<<<< HEAD
	
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
	
	
=======

>>>>>>> branch 'ex2' of https://github.com/ntg7creation/spl_project_group77.git
	/**
	 * Retrieves the title of this book.
	 * <p>
	 * 
	 * @return The title of this book.
	 */
	public String getBookTitle() {
<<<<<<< HEAD
		
		return bookTitle;
=======

		return title;
>>>>>>> branch 'ex2' of https://github.com/ntg7creation/spl_project_group77.git

	}

	/**
	 * Retrieves the amount of books of this type in the inventory.
	 * <p>
	 * 
	 * @return amount of available books.
	 */
	public int getAmountInInventory() {

		return amount;
	}

	/**
	 * Retrieves the price for book.
	 * <p>
	 * 
	 * @return the price of the book.
	 */
	public int getPrice() {

		return price;
	}
<<<<<<< HEAD
	
	@Override
	public String toString()
	{
		return "[title:" +bookTitle + ",amount:" +amount+",price:"+price+"]" ;
	}
	
	
=======
>>>>>>> branch 'ex2' of https://github.com/ntg7creation/spl_project_group77.git

	@Override
	public String toString() {
		return "[title:" + title + ",amount:" + amount + ",price:" + price + "]";
	}

}
