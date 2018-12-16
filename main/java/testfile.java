import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class testfile {

   public static void main(String[] args){
	   JSONParser parser = new JSONParser();
	      String s = "[0,{\"1\":{\"2\":{\"3\":{\"4\":[5,{\"6\":7}]}}}}]";
			
	      try{
	         Object obj = parser.parse(s);
	         JSONArray array = (JSONArray)obj;
				
	         System.out.println("The 2nd element of array");
	         System.out.println(array.get(1));
	         System.out.println();

	         JSONObject obj2 = (JSONObject)array.get(1);
	         System.out.println("Field \"1\"");
	         System.out.println(obj2.get("1"));    

	         s = "{}";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s = "[5,]";
	         obj = parser.parse(s);
	         System.out.println(obj);

	         s = "[5,,2]";
	         obj = parser.parse(s);
	         System.out.println(obj);
	      }catch(ParseException pe){
			
	        //System.out.println("position: " + pe.getPosition());
	         System.out.println(pe);
	      }
	      System.out.println();
	      System.out.println();
	      try {
			JSONArray a = (JSONArray) parser.parse(new FileReader("C:\\Users\\Lenovo\\git\\spl_project_group77\\temp.json"));
			System.out.println(a.get(0));
			
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   }
}