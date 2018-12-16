package bgu.spl.mics.application.passiveObjects;
import java.util.concurrent.ConcurrentHashMap;


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

	private ConcurrentHashMap <String,Integer> amount;
	private ConcurrentHashMap <String,Integer> price;


	private Inventory() {
	amount = new ConcurrentHashMap <>();
	price = new ConcurrentHashMap <>();

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
	//	ConcurrentHashMap <String,Integer> amount = new ConcurrentHashMap <>();
	//	ConcurrentHashMap <String,Integer> price = new ConcurrentHashMap <>();

		for(int i = 0 ; i < inventory.length ; i++){
			price.put(inventory[i].getBookTitle() , inventory[i].getPrice());
			amount.put(inventory[i].getBookTitle() , inventory[i].getAmountInInventory());
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
				//count= count- 1;
			}
		}
		return OrderResult.OutOfStock;
		
		//		OrderResult result = OrderResult.DontHaveBook;
		//		for (BookInventoryInfo bookInventoryInfo : inventory) {
		//			if (bookInventoryInfo.getBookTitle().equals(book)) {
		//				result = OrderResult.OutOfStock;
		//				if (bookInventoryInfo.removeBooks(1)) {
		//					return OrderResult.Success;
		//				}
		//			}
		//		}
		//		return result;
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
            //synchronized (book) { not needed
                if (count> 0)
                    return price.get(book);
           // }
        }
        return -1;
	}
	
//		for (BookInventoryInfo bookInventoryInfo : inventory) {
//			if (bookInventoryInfo.getBookTitle().equals(book)) {
//				if (bookInventoryInfo.getAmountInInventory() > 0) {
//					return bookInventoryInfo.getPrice();
//				}
//			}
//		}
//		return -1;
//	}

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
		
//		ConcurrentHashMap <String,Integer> BooksConcurrentHashMap = new ConcurrentHashMap <>();
//		for(BookInventoryInfo bookInfo: books){
//			BooksConcurrentHashMap.put(bookInfo.getBookTitle(), bookInfo.getAmountInInventory());
//			try {
//				FileOutputStream fileOut = new FileOutputStream(filename);
//				ObjectOutputStream out = new ObjectOutputStream(fileOut);
//				out.writeObject(BooksConcurrentHashMap);
//				out.close();
//				fileOut.close();
//				System.out.printf("Serialized data is saved in " + filename);
//			} catch (IOException i) {
//				i.printStackTrace();
//		
//			
//
//		}
//			booksHashMap.put(bookInfo.getBookTitle(), bookInfo.getAmountInInventory());			String name = book.getBookTitle();
//			Integer amount = book.getAmountInInventory();
//			inventoryToFile.put(name,amount);
//			
////            FileOutputStream fos =
////                    new FileOutputStream("hashmap.ser");
////                 ObjectOutputStream oos = new ObjectOutputStream(fos);
////                 oos.writeObject(hmap);
////                 oos.close();
////                 fos.close();
////		}
////		filePrinter.printToFile(inventoryToFile,filename);
////		
////		BookMap.putAll(AmountMap);
////		try
////		{
////			//Saving of object in a file
////			FileOutputStream file = new FileOutputStream(filename);
////			ObjectOutputStream out = new ObjectOutputStream(file);
////
////			// Method for serialization of object
////			out.writeObject(BookMap);
////			out.close();
////			file.close();
////		}
////		catch(IOException ex)
//			
//			
//			
//			HashMap<String, Integer> booksHashMap = new HashMap<>();
//			for(BookInventoryInfo bookInfo: books){
//				booksHashMap.put(bookInfo.getBookTitle(), bookInfo.getAmountInInventory());
//			}
//		
//		
//		
//		{
//			System.out.println("IOException is caught");
//
//		
//		
//		
////		synchronized (Theinventory){
////			ConcurrentHashMap<String, Integer> booksHashMap = new ConcurrentHashMap<>();
////			for(BookInventoryInfo bookInfo: inventory){
////				booksHashMap.put(bookInfo.getBookTitle(), bookInfo.getAmountInInventory());
////				// need to complete
////			}
		}
}
	
	

