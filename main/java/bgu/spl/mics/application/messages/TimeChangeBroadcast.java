package bgu.spl.mics.application.messages;

import bgu.spl.mics.Broadcast;

public class TimeChangeBroadcast implements Broadcast {

	private int newTime;

	public int getNewTime() {
		return newTime;
	}

	public void setNewTime(int n) {
		newTime = n;
	}
}
