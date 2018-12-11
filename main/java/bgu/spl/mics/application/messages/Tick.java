package bgu.spl.mics.application.messages;

import bgu.spl.mics.Broadcast;

public class Tick implements Broadcast {

	private int newTime;

	public Tick(int time) {
		newTime = time;
	}

	public int getNewTime() {
		return newTime;
	}

}
