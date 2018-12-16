package bgu.spl.mics.application.passiveObjects;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

/**
 * Passive data-object representing the store inventory. It holds a collection
 * of {@link BookInventoryInfo} for all the books in the store.
 * <p>
 * This class must be implemented safely as a thread-safe singleton. You must
 * not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private fields and methods to this class as you see fit.
 */
public class Inventory {

	private ConcurrentHashMap<String, Integer> amount;
	private ConcurrentHashMap<String, Integer> price;
	private ConcurrentLinkedQueue<String> books;

	private Inventory() {
		amount = new ConcurrentHashMap<>();
		price = new ConcurrentHashMap<>();
		books = new ConcurrentLinkedQueue<>();

	}

	private static class Holder {
		private static final Inventory INSTANCE = new Inventory();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static Inventory getInstance() {

		return Holder.INSTANCE;
	}

	/**
	 * Initializes the store inventory. This method adds all the items given to the
	 * store inventory.
	 * <p>
	 * 
	 * @param inventory
	 *            Data structure containing all data necessary for initialization of
	 *            the inventory.
	 */
	public void load(BookInventoryInfo[] inventory) {
		// ConcurrentHashMap <String,Integer> amount = new ConcurrentHashMap <>();
		// ConcurrentHashMap <String,Integer> price = new ConcurrentHashMap <>();

		for (int i = 0; i < inventory.length; i++) {
			price.put(inventory[i].getBookTitle(), inventory[i].getPrice());
			amount.put(inventory[i].getBookTitle(), inventory[i].getAmountInInventory());
		}

	}

	/**
	 * Attempts to take one book from the store.
	 * <p>
	 * 
	 * @param book
	 *            Name of the book to take from the store
	 * @return an {@link Enum} with options NOT_IN_STOCK and SUCCESSFULLY_TAKEN. The
	 *         first should not change the state of the inventory while the second
	 *         should reduce by one the number of books of the desired type.
	 */
	public OrderResult take(String book) {
		Integer count = amount.get(book);
		if (count != null) {
			synchronized (book) {
				if (count > 0) {
					return OrderResult.Success;
				}
				// count= count- 1;
			}
		}
		return OrderResult.OutOfStock;

		// OrderResult result = OrderResult.DontHaveBook;
		// for (BookInventoryInfo bookInventoryInfo : inventory) {
		// if (bookInventoryInfo.getBookTitle().equals(book)) {
		// result = OrderResult.OutOfStock;
		// if (bookInventoryInfo.removeBooks(1)) {
		// return OrderResult.Success;
		// }
		// }
		// }
		// return result;
	}

	/**
	 * Checks if a certain book is available in the inventory.
	 * <p>
	 * 
	 * @param book
	 *            Name of the book.
	 * @return the price of the book if it is available, -1 otherwise.
	 */
	public int checkAvailabiltyAndGetPrice(String book) {

		Integer count = amount.get(book);
		if (count != null) {
			// synchronized (book) { not needed
			if (count > 0)
				return price.get(book);
			// }
		}
		return -1;
	}

	// for (BookInventoryInfo bookInventoryInfo : inventory) {
	// if (bookInventoryInfo.getBookTitle().equals(book)) {
	// if (bookInventoryInfo.getAmountInInventory() > 0) {
	// return bookInventoryInfo.getPrice();
	// }
	// }
	// }
	// return -1;
	// }

	/**
	 * 
	 * <p>
	 * Prints to a file name @filename a serialized object HashMap<String,Integer>
	 * which is a Map of all the books in the inventory. The keys of the Map (type
	 * {@link String}) should be the titles of the books while the values (type
	 * {@link Integer}) should be their respective available amount in the
	 * inventory. This method is called by the main method in order to generate the
	 * output.
	 */
	public void printInventoryToFile(String filename) {

		try {
			FileOutputStream fileOut = new FileOutputStream(filename);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(amount);
			out.close();
			fileOut.close();
			System.out.println("Data is saved in " + filename);
		} catch (IOException i) {
			i.printStackTrace();

		}
	}
}