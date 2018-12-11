package bgu.spl.mics.application.messages;

import bgu.spl.mics.Event;
import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;

public class GetBookEvent implements Event<BookInventoryInfo> {

	private String bookName;

	public GetBookEvent(String name) {
		bookName = name;
	}

	public String GetName() {
		return bookName;
	}
}
