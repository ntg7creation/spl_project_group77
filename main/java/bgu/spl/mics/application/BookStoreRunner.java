package bgu.spl.mics.application;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;
import bgu.spl.mics.application.services.APIService;
import bgu.spl.mics.application.services.InventoryService;
import bgu.spl.mics.application.services.LogisticsService;
import bgu.spl.mics.application.services.ResourceService;
import bgu.spl.mics.application.services.SellingService;
import bgu.spl.mics.application.services.TimeService;

/** This is the Main class of the application. You should parse the input file, 
 * create the different instances of the objects, and run the system.
 * In the end, you should output serialized objects.
 */
public class BookStoreRunner {
    public static void main(String[] args) {
    	//test
    	//code
    	BookInventoryInfo[] stash = {new BookInventoryInfo("harry potter", 20, 300)};
    	Inventory.getInstance().load(stash);
    	DeliveryVehicle[] cars = {new DeliveryVehicle(9183, 5)};
    	ResourcesHolder.getInstance().load(cars);
    	
    	for (int i = 0; i < 1; i++) {
    		test1();	
		}
    	TimeService T1 = new TimeService("natais clock");
    	Thread tt1 = new Thread(T1);
    	tt1.start();
    	System.out.println("the timers started");
    	
  //  	test2();
    }
    
    
    
    public static void test1()
    {
    	 ExecutorService e = Executors.newFixedThreadPool(3);
    	
    	SellingService s1 = new SellingService("david");
    	
    	Customer c = new Customer("natai", 208, "somewhere", 10, 1000, 771772);
    	HashMap<Integer,String> list = new HashMap<>();
    	list.put(3, "harry potter");
    	list.put(4, "parsy Jacson");
    	APIService a1 = new APIService(c, list) ;
    	InventoryService I1 = new InventoryService("Inventorytest");
    	LogisticsService l1 = new LogisticsService("driver test");
    	ResourceService r1 = new ResourceService("parking lot gate 1 test");
    	
    	
    	Thread tr1 = new Thread(r1);
    	tr1.start();
    	Thread tl1 = new Thread(l1);
    	tl1.start();
    	Thread ts1 = new Thread(s1);
    	ts1.start();
    	Thread Ti1 = new Thread(I1);
    	Ti1.start();
    	Thread ta1 = new Thread(a1);
    	ta1.start();

    	
       // e.execute(s1);
        //e.execute(a1);
      // e.execute(T1);
    	
    }
    
    public static void test2()
    {
    	 ExecutorService e = Executors.newFixedThreadPool(3);
    	
    	SellingService s1 = new SellingService("seller1");
    	SellingService s2 = new SellingService("seller2");
    	
    	Customer c1 = new Customer("natai", 208, "somwhere", 10, 1000, 771772);
    	Customer c2 = new Customer("omri", 811, "somwhere", 10, 1000, 771772);
    	
    	HashMap<Integer,String> list1 = new HashMap<>();
    	list1.put(3, "harry potter");
    	list1.put(4, "parsy Jacson");

    	HashMap<Integer,String> list2 = new HashMap<>();
    	list2.put(3, "the wall");
    	list2.put(4, "animal farm");
    	APIService a1 = new APIService(c1, list1) ;
    	APIService a2 = new APIService(c2, list2) ;
    	
    	TimeService T1 = new TimeService("natais clock");

    	
    	
    	Thread ts1 = new Thread(s1);
    	ts1.start();
    	Thread ta1 = new Thread(a1);
    	ta1.start();
    	Thread ts2 = new Thread(s2);
    	ts2.start();
    	Thread ta2 = new Thread(a2);
    	ta2.start();

    	Thread tt1 = new Thread(T1);
    	tt1.start();
    	
       // e.execute(s1);
        //e.execute(a1);
      // e.execute(T1);
    	
    }
    
}
