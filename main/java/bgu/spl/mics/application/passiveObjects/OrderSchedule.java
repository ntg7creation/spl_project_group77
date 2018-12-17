package bgu.spl.mics.application.passiveObjects;

import java.io.Serializable;

public class OrderSchedule implements Serializable {

	private String bookTitle;
	private int tick;

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public int getTick() {
		return tick;
	}

	public void setTick(int tick) {
		this.tick = tick;
	}
}
