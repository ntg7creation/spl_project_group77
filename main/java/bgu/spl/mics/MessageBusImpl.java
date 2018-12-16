package bgu.spl.mics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetBookEvent;
import bgu.spl.mics.application.messages.GetVehicleEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.ReturnVehicleEvent;
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

	private ConcurrentHashMap<Class, ConcurrentLinkedQueue<MicroService>> subscription;
	private ConcurrentHashMap<MicroService, Lock> locks;
	private ConcurrentHashMap<MicroService, ConcurrentLinkedQueue<Message>> registers;
	private ConcurrentHashMap<Event, Future> mailBoxs;

	static public MessageBus getInstance() {

		return Holder.INSTANCE;
	}

	private MessageBusImpl() {

		registers = new ConcurrentHashMap<>();
		mailBoxs = new ConcurrentHashMap<>();
		subscription = new ConcurrentHashMap<>();
		locks = new ConcurrentHashMap<>();

		subscription.put(CheckAvailabilityEventAndGetPriceEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(DeliveryEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(GetBookEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(GetVehicleEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(OrderBookEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(Tick.class, new ConcurrentLinkedQueue<>());
		subscription.put(ReturnVehicleEvent.class, new ConcurrentLinkedQueue<>());


	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
		if (subscription.get(type) == null)
			System.out.println("cant register to this type of event");
		subscription.get(type).add(m);
	}

	@Override
	public void subscribeBroadcast(Class<? extends Broadcast> type, MicroService m) {
		if (subscription.get(type) == null)
			System.out.println("cant register to this type of broadcast");
		subscription.get(type).add(m);
	}

	@Override
	public <T> void complete(Event<T> e, T result) {
		// System.out.println(mailBoxs);
	 Future<T> f = mailBoxs.remove(e);
		// // System.out.println();//TODO find a better fix
		// System.out.println(f);
		// if (f == null)
		// System.out.println("try to resolve a future that dose not exist");
		if (f == null)
			System.out.println(e+"      has lost his future");
		f.resolve(result);

	}

	@Override
	public void sendBroadcast(Broadcast b) {
		ConcurrentLinkedQueue<MicroService> waiting = subscription.get(b.getClass());
		for (MicroService m : waiting) {
			if (m != null) {
				if (registers.containsKey(m)) {
					registers.get(m).add(b);
					// System.out.println("a Broadcast has been added to " + m.getName());
					synchronized (m) {
						m.notify();
					}
				} else {
					System.out.println("error the waiting micro server dose not exsist");
				}

			}
		}
	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		Future<T> newBoxkey = null;
		ConcurrentLinkedQueue<MicroService> waiting = subscription.get(e.getClass());
		MicroService m = waiting.poll();
		if (m != null) {
			if (registers.containsKey(m)) {
				newBoxkey = new Future<T>();
				mailBoxs.put(e, newBoxkey);
				registers.get(m).add(e);
				// System.out.println("an event has been added to " + m.getName());
				synchronized (m) {
					m.notify();
				}
				waiting.add(m);
			} else {
				System.out.println("ERROR!!!! the waiting micro server dose not exsist");
			}

		}

		return newBoxkey;
	}

	@Override
	public void register(MicroService m) {
		if (!registers.containsKey(m))
			registers.put(m, new ConcurrentLinkedQueue<Message>());
		locks.put(m, new Lock());
	}

	@Override
	public void unregister(MicroService m) {

		Unsubscribe(CheckAvailabilityEventAndGetPriceEvent.class, m);
		Unsubscribe(DeliveryEvent.class, m);
		Unsubscribe(GetBookEvent.class, m);
		Unsubscribe(GetVehicleEvent.class, m);
		Unsubscribe(OrderBookEvent.class, m);
		Unsubscribe(ReturnVehicleEvent.class, m);
		Unsubscribe(Tick.class, m);

		if (registers.containsKey(m)) {
			locks.remove(m);
			while (!registers.get(m).isEmpty()) {
				Message toSend = registers.get(m).poll();
				if (toSend.getClass() != Tick.class) {
					ConcurrentLinkedQueue<MicroService> waiting = subscription.get(toSend.getClass());
					MicroService micro = waiting.poll();
					if (micro != null) {
						if (registers.containsKey(micro)) {
							registers.get(micro).add(toSend);
							// System.out.println("an event has been added to " + micro.getName());
							micro.notify();
							waiting.add(micro);
						} else {
							System.out.println("error the waiting micro server dose not exist");
						}

					}
				}
			}
		}
		registers.remove(m.getName(), m);
	}

	private void Unsubscribe(Class type, MicroService micro) {
		subscription.get(type).remove(micro);
	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		if (registers.containsKey(m)) {
			synchronized (m) {
				// System.out.println(m.getName() + " went to sleep");
				// System.out.println();
				m.wait();
			}
			// locks.get(m).lockme();
			// System.out.println(m.getName() + " just woke up and is starting to work");
			ConcurrentLinkedQueue<Message> ToDoList = registers.get(m);
			Message todoNext = ToDoList.poll();
			if (todoNext == null) {
				System.out.println("error: resive null message");
				m.terminate();
			}

			return todoNext;
		} else {
			System.out.println(m.getName() + " is not registered");
			m.terminate();
			return null;
		}
	}

}
