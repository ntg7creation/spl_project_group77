//import java.io.FileReader;
//import java.io.IOException;
//import java.io.Reader;
//import java.util.HashMap;
//
//import com.google.gson.Gson;
//
//import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;
//import bgu.spl.mics.application.passiveObjects.DeliveryVehicle;
//import bgu.spl.mics.application.passiveObjects.ResourcesHolder;
//import jsonclass.storeObjects;
//
//class testfile {
//
//	public static void main(String[] args) {
//	//	Gson gson = new Gson();
//
//		try (Reader reader = new FileReader("C:\\Users\\Lenovo\\git\\spl_project_group77\\temp.json")) {
//
//			// Convert JSON to Java Object
//			storeObjects staff = gson.fromJson(reader, storeObjects.class);
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
