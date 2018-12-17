package bgu.spl.mics.application;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import bgu.spl.mics.MessageBus;
import bgu.spl.mics.MessageBusImpl;
import bgu.spl.mics.staticFunctions;
import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;
import bgu.spl.mics.application.services.APIService;
import bgu.spl.mics.application.services.InventoryService;
import bgu.spl.mics.application.services.LogisticsService;
import bgu.spl.mics.application.services.ResourceService;
import bgu.spl.mics.application.services.SellingService;
import bgu.spl.mics.application.services.TimeService;
import bgu.spl.mics.jsonclass.Resources;
import bgu.spl.mics.jsonclass.storeObjects;

/**
 * This is the Main class of the application. You should parse the input file,
 * create the different instances of the objects, and run the system. In the
 * end, you should output serialized objects.
 */
public class BookStoreRunner {



	public static void main(String[] args) {
		System.out.println("start progrome");
		if (args.length != 5)
			System.out.println("ronge number of arguments");
		else {
			List<Thread> myThreads;
			Gson gson = new Gson();
			myThreads = new LinkedList<>();
			try (Reader reader = new FileReader(args[0])) {// "C:\\Users\\Lenovo\\git\\spl_project_group77\\temp.json"))
															// {

				// Convert JSON to Java Object
				storeObjects staff = gson.fromJson(reader, storeObjects.class);
				Inventory.getInstance().load(staff.getInitialInventory());
				for (Resources array : staff.getInitialResources()) {
					ResourcesHolder.getInstance().load(array.getVehicles());
				}
				for (int i = 0; i < staff.getServices().getSelling(); i++) {
					Thread SST = new Thread(new SellingService("seller:" + i));
					myThreads.add(SST);
					SST.start();
				}
				for (int i = 0; i < staff.getServices().getLogistics(); i++) {
					Thread LST = new Thread(new LogisticsService("Logistics:" + i));
					myThreads.add(LST);
					LST.start();
				}
				for (int i = 0; i < staff.getServices().getInventoryService(); i++) {
					Thread IST = new Thread(new InventoryService("inventoryService:" + i));
					myThreads.add(IST);
					IST.start();
				}
				for (int i = 0; i < staff.getServices().getResourcesService(); i++) {
					Thread RST = new Thread(new ResourceService("ResourceService:" + i));
					myThreads.add(RST);
					RST.start();
				}
				for (Customer c : staff.getServices().getCustomers()) {
					Thread AST = new Thread(new APIService(c, c.getOrderSchedule()));
					myThreads.add(AST);
					AST.start();

				}
				Thread TST = new Thread(staff.getServices().getTime());
				myThreads.add(TST);
				TST.start();

				
				for (Thread thread : myThreads) {
					try {
						thread.join();
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
				HashMap<Integer, Customer> mycustomers = new HashMap<>();
				for (Customer c : staff.getServices().getCustomers()) {
					mycustomers.put(c.getId(), c);
				}
				staticFunctions.printStoFail(args[1], mycustomers);
				Inventory.getInstance().printInventoryToFile(args[2]);
				MoneyRegister.getInstance().printOrderReceipts(args[3]);
				staticFunctions.printStoFail(args[4], MoneyRegister.getInstance());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test1() {
		ExecutorService e = Executors.newFixedThreadPool(3);

		SellingService s1 = new SellingService("david");

		Customer c = new Customer("natai", 208, "somewhere", 10000, 1000, 771772);
		HashMap<Integer, String> list = new HashMap<>();
		list.put(3, "harry potter");
		list.put(4, "parsy Jacson");
		APIService a1 = new APIService(c);
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
		// e.execute(a1);
		// e.execute(T1);

	}

	public static void test2() {
		ExecutorService e = Executors.newFixedThreadPool(3);

		SellingService s1 = new SellingService("seller1");
		SellingService s2 = new SellingService("seller2");

		Customer c1 = new Customer("natai", 208, "somwhere", 10, 1000, 771772);
		Customer c2 = new Customer("omri", 811, "somwhere", 10, 1000, 771772);

		HashMap<Integer, String> list1 = new HashMap<>();
		list1.put(3, "harry potter");
		list1.put(4, "parsy Jacson");

		HashMap<Integer, String> list2 = new HashMap<>();
		list2.put(3, "the wall");
		list2.put(4, "animal farm");
		APIService a1 = new APIService(c1);
		APIService a2 = new APIService(c2);

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
		// e.execute(a1);
		// e.execute(T1);

	}

}
