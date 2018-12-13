package bgu.spl.mics;

public class Lock {

	public void lockme() throws InterruptedException {
		this.wait();
	}
}
