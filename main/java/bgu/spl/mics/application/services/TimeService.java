package bgu.spl.mics.application.services;

import java.util.Timer;
import java.util.TimerTask;

import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.messages.Terminate;
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
	private int duration;
	private int time;
	// private Timer clock;

	public TimeService() {
		super("timer");
		// TODO Auto-generated constructor stub
	}

	public TimeService(String name) {
		super(name);
		// clock = new Timer();
		time = 0;
		speed = 1000;
	}

	@Override
	protected void initialize() {
		Timer clock = new Timer();
		clock.schedule(new TimerTask() {

			@Override
			public void run() {
				time++;
				if (time < duration)
					sendBroadcast(new Tick(time));
				else {
					sendBroadcast(new Terminate());
					clock.cancel();
				}
			}
		}, 0, speed);

	}
}
