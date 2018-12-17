package bgu.spl.mics;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class staticFunctions {

	public static void printStoFail(String path, Object o) {
		try {
			FileOutputStream fileOut = new FileOutputStream(path);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(o);
			out.close();
			fileOut.close();
			System.out.println("Data is saved in " + path);
		} catch (IOException i) {
			i.printStackTrace();

		}

	}

}
