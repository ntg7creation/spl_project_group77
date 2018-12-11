package bgu.spl.mics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.mics.application.messages.CheckAvailabilityEvent;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetBookEvent;
import bgu.spl.mics.application.messages.GetVehicleEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.Tick;

/**
 * The {@link MessageBusImpl class is the implementation of the MessageBus
 * interface. Write your implementation here! Only private fields and methods
 * can be added to this class.
 */
public class MessageBusImpl implements MessageBus {

	private static class Holder {
		private static final MessageBus INSTANCE = new MessageBusImpl();
	}

	private ConcurrentHashMap<Class, ConcurrentLinkedQueue<MicroService>> subscrtion;
	private ConcurrentHashMap<String, MicroService> registers;
	private ConcurrentHashMap<Event, Future> mailBoxs;
	private ConcurrentLinkedQueue<Event> eventsTosend;
	
	static public MessageBus getInstance() {
		return Holder.INSTANCE;
	}

	private MessageBusImpl() {

		registers = new ConcurrentHashMap<>();
		mailBoxs = new ConcurrentHashMap<>();
		subscrtion = new ConcurrentHashMap<>();

		subscrtion.put(CheckAvailabilityEvent.class, new ConcurrentLinkedQueue<>());
		subscrtion.put(DeliveryEvent.class, new ConcurrentLinkedQueue<>());
		subscrtion.put(GetBookEvent.class, new ConcurrentLinkedQueue<>());
		subscrtion.put(GetVehicleEvent.class, new ConcurrentLinkedQueue<>());
		subscrtion.put(OrderBookEvent.class, new ConcurrentLinkedQueue<>());
		subscrtion.put(Tick.class, new ConcurrentLinkedQueue<>());

		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		subscrtion.get(type).add(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
			subscrtion.get(type).add(m);
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void register(MicroService m) {
		registers.put(m.getName(), m);
		}

	@Override
	public void unregister(MicroService m) {
		registers.remove(m.getName(), m);
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		m.wait();
		return null;
	}

}
