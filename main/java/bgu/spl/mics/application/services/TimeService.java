package bgu.spl.mics.application.services;

import java.util.Timer;
import java.util.TimerTask;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.Tick;
import bgu.spl.mics.application.passiveObjects.Inventory;
import bgu.spl.mics.application.passiveObjects.MoneyRegister;
import bgu.spl.mics.application.passiveObjects.ResourcesHolder;

/**
 * TimeService is the global system timer There is only one instance of this
 * micro-service. It keeps track of the amount of ticks passed since
 * initialization and notifies all other micro-services about the current time
 * tick using {@link Tick Broadcast}. This class may not hold references for
 * objects which it is not responsible for: {@link ResourcesHolder},
 * {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class. You MAY change
 * constructor signatures and even add new public constructors.
 */
public class TimeService extends MicroService {

	private int speed;
	private int time;
	Timer clock;

	public TimeService(String name) {
		super(name);
		clock = new Timer();
		time = 0;
		speed = 1000;
	}

	@Override
	protected void initialize() {
		clock.schedule(new TimerTask() {

			@Override
			public void run() {
				time++;
				System.out.println("time to send broadcast");
				sendBroadcast(new Tick(time));

			}
		}, 0, speed);
		
	}
}
