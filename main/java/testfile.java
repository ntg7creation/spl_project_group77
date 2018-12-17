//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//
//import com.google.gson.Gson;
//
//import bgu.spl.mics.staticFunctions;
//import bgu.spl.mics.application.passiveObjects.Customer;
//import bgu.spl.mics.application.passiveObjects.Inventory;
//import bgu.spl.mics.application.passiveObjects.MoneyRegister;
//import bgu.spl.mics.application.passiveObjects.ResourcesHolder;
//import bgu.spl.mics.application.services.APIService;
//import bgu.spl.mics.application.services.InventoryService;
//import bgu.spl.mics.application.services.LogisticsService;
//import bgu.spl.mics.application.services.ResourceService;
//import bgu.spl.mics.application.services.SellingService;
//import bgu.spl.mics.application.services.TimeService;
//import jsonclass.Resources;
//import jsonclass.storeObjects;
//import jsonclass.testclass;
//
//class testfile {
//
//	public static void main(String[] args) {
//		Gson gson = new Gson();
//
//		try (Reader reader = new FileReader("C:\\Users\\Lenovo\\git\\spl_project_group77\\temp.json")) {
//
//			// Convert JSON to Java Object
//		//	System.out.println("somthing"+ 6);
//			storeObjects staff = gson.fromJson(reader, storeObjects.class);
//			Inventory.getInstance().load(staff.getInitialInventory());
//			for ( Resources array : staff.getInitialResources()) {
//				ResourcesHolder.getInstance().load(array.getVehicles());
//			}
//			for (int i = 0; i < staff.getServices().getSelling(); i++) {
//				Thread SST = new  Thread(new SellingService("seller:" + i));
//				SST.start();
//			}
//			for (int i = 0; i < staff.getServices().getLogistics(); i++) {
//				Thread LST = new  Thread(new LogisticsService("Logistics:" + i));
//				LST.start();
//			}
//			for (int i = 0; i < staff.getServices().getInventoryService(); i++) {
//				Thread IST = new  Thread(new InventoryService("inventoryService:" + i));
//				IST.start();
//			}
//			for (int i = 0; i < staff.getServices().getResourcesService(); i++) {
//				Thread RST = new  Thread(new ResourceService("ResourceService:" + i));
//				RST.start();
//			}
//			for (Customer c: staff.getServices().getCustomers()) {
//				Thread AST = new Thread(new APIService(c, c.getOrderSchedule()));
//				AST.start();
//			}
//			MoneyRegister.getInstance().printOrderReceipts("C:\\Users\\Lenovo\\git\\spl_project_group77\\testfile1");
//			Inventory.getInstance().printInventoryToFile("C:\\Users\\Lenovo\\git\\spl_project_group77\\testfile2");
//			staticFunctions.printStoFail("C:\\Users\\Lenovo\\git\\spl_project_group77\\testfile3", MoneyRegister.getInstance());
//			//	staticFunctions.printStoFail("C:\\Users\\Lenovo\\git\\spl_project_group77\\testfile", staff);
//			
//			//System.out.println(staff);
//			// for (DeliveryVehicle s : staff) {
//			// System.out.println(s.getLicense());
//			// System.out.println(s.getSpeed());
//			// }
//
//			// Convert JSON to JsonElement, and later to String
//			/*
//			 * JsonElement json = gson.fromJson(reader, JsonElement.class); String
//			 * jsonInString = gson.toJson(json); System.out.println(jsonInString);
//			 */
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}
//
//}
