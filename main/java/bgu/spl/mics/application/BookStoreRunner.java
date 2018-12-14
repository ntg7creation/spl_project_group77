package bgu.spl.mics.application;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.services.APIService;
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
    	test1();
    	
    }
    
    
    
    public static void test1()
    {
    	 ExecutorService e = Executors.newFixedThreadPool(3);
    	
    	SellingService s1 = new SellingService("david");
    	
    	Customer c = new Customer("nata", 208, "somwhere", 10, 1000, 771772);
    	HashMap<Integer,String> list = new HashMap<>();
    	list.put(3, "harry potter");
    	list.put(4, "parsy Jacson");
    	APIService a1 = new APIService(c, list) ;
    	TimeService T1 = new TimeService("natais clock");
    	
    	
    	Thread ts1 = new Thread(s1);
    	ts1.start();
    	Thread ta1 = new Thread(a1);
    	ta1.start();
    	Thread tt1 = new Thread(T1);
    	tt1.start();
    	
       // e.execute(s1);
        //e.execute(a1);
      // e.execute(T1);
    	
    }
    
}
