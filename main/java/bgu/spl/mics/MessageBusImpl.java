package bgu.spl.mics;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

import bgu.spl.mics.application.messages.CheckAvailabilityEventAndGetPriceEvent;
import bgu.spl.mics.application.messages.DeliveryEvent;
import bgu.spl.mics.application.messages.GetBookEvent;
import bgu.spl.mics.application.messages.GetVehicleEvent;
import bgu.spl.mics.application.messages.OrderBookEvent;
import bgu.spl.mics.application.messages.ReturnVehicleEvent;
import bgu.spl.mics.application.messages.Terminate;
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

	private int tercount=0;
	private Boolean TerminateMode;
	private ConcurrentHashMap<Class, ConcurrentLinkedQueue<MicroService>> subscription;
	private ConcurrentHashMap<MicroService, ConcurrentLinkedQueue<Message>> registers;
	private ConcurrentHashMap<Event, Future> mailBoxs;

	static public MessageBus getInstance() {

		return Holder.INSTANCE;
	}

	private MessageBusImpl() {

		TerminateMode = false;
		registers = new ConcurrentHashMap<>();
		mailBoxs = new ConcurrentHashMap<>();
		subscription = new ConcurrentHashMap<>();
		new ConcurrentHashMap<>();

		subscription.put(CheckAvailabilityEventAndGetPriceEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(DeliveryEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(GetBookEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(GetVehicleEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(OrderBookEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(Tick.class, new ConcurrentLinkedQueue<>());
		subscription.put(ReturnVehicleEvent.class, new ConcurrentLinkedQueue<>());
		subscription.put(Terminate.class, new ConcurrentLinkedQueue<>());

	}

	@Override
	public <T> void subscribeEvent(Class<? extends Event<T>> type, MicroService m) {
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
			System.out.println(e + "      has lost his future");
		f.resolve(result);

	}

	@Override
	public void sendBroadcast(Broadcast b) {

		if (b.getClass() == Terminate.class) {
			try {
				synchronized (this) {
					this.wait(500,1);
				}

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("starting terminate");
			TerminateMode = true;
		}

		ConcurrentLinkedQueue<MicroService> waiting = subscription.get(b.getClass());
		for (MicroService m : waiting) {
			if (m != null) {
				if (registers.containsKey(m)) {
					synchronized (registers.get(m)) {
						if (b.getClass() == Terminate.class) {
							System.out.println(m.getName() + "send terminate");
							while(!registers.get(m).isEmpty())
							{
								Message endM= registers.get(m).poll();
								mailBoxs.get(endM).resolve(null);
							}
							registers.get(m).clear();
						}


						registers.get(m).add(b);

					}
						// System.out.println("a Broadcast has been added to " + m.getName());
							synchronized (m) {
								
								m.notify();
							}
					
				} else {
					System.out.println("error the waiting micro server dose not exsist");
				}

			} else
				System.out.println("null micro");
		}
		if (b.getClass() == Terminate.class) {
			System.out.println("end terminate");
			System.out.println(tercount);
		}
	}

	@Override
	public <T> Future<T> sendEvent(Event<T> e) {
		if (e.getClass() == CheckAvailabilityEventAndGetPriceEvent.class) {
			// System.out.println(e.getClass());
			System.out.println(subscription.get(e.getClass()));
		}
		Future<T> newBoxkey = null;
		synchronized (subscription.get(e.getClass())) {

			ConcurrentLinkedQueue<MicroService> waiting = subscription.get(e.getClass());
			MicroService m = waiting.poll();
			if (m != null) {
				if (registers.containsKey(m)) {
					newBoxkey = new Future<T>();
					mailBoxs.put(e, newBoxkey);
					synchronized (registers.get(m)) {
						registers.get(m).add(e);
					}

					// System.out.println("an event has been added to " + m.getName());
					synchronized (m) {
						m.notify();
					}
					waiting.add(m);
				} else {
					System.out.println("ERROR!!!! the waiting micro server dose not exsist");
				}

			}
		}
		return newBoxkey;
	}

	@Override
	public void register(MicroService m) {
		if (!registers.containsKey(m))
			registers.put(m, new ConcurrentLinkedQueue<Message>());
	}

	@Override
	public void unregister(MicroService m) {

		tercount++;
		Unsubscribe(CheckAvailabilityEventAndGetPriceEvent.class, m);
		Unsubscribe(DeliveryEvent.class, m);
		Unsubscribe(GetBookEvent.class, m);
		Unsubscribe(GetVehicleEvent.class, m);
		Unsubscribe(OrderBookEvent.class, m);
		Unsubscribe(ReturnVehicleEvent.class, m);
		Unsubscribe(Tick.class, m);


		
		if (registers.containsKey(m)) {
			while (!registers.get(m).isEmpty()) {
				// Message toSend = registers.get(m).poll();
				// if (toSend.getClass() != Tick.class) {
				// ConcurrentLinkedQueue<MicroService> waiting =
				// subscription.get(toSend.getClass());
				// MicroService micro = waiting.poll();
				// if (micro != null) {
				// if (registers.containsKey(micro)) {
				// registers.get(micro).add(toSend);
				// // System.out.println("an event has been added to " + micro.getName());
				// micro.notify();
				// waiting.add(micro);
				// } else {
				// System.out.println("error the waiting micro server dose not exist");
				// }
				//
				// }
				// }
				synchronized (m) {
					m.notify();
				}

			}
		}
		registers.remove(m.getName(), m);
	}

	private void Unsubscribe(Class type, MicroService micro) {
		synchronized (subscription.get(type)) {
			subscription.get(type).remove(micro);
		}

	}

	@Override
	public Message awaitMessage(MicroService m) throws InterruptedException {
		if (registers.containsKey(m)) {
			// synchronized (registers.get(m)) {

			ConcurrentLinkedQueue<Message> ToDoList = registers.get(m);
			if (ToDoList.isEmpty())
				// rick here to miss if someone input
				synchronized (m) {
					m.wait();

				}

			Message todoNext = ToDoList.poll();
			if (todoNext == null) {
				System.out.println("error: resive null message");
				m.terminate();
			}

			return todoNext;
			// }
		} else {
			System.out.println(m.getName() + " is not registered");
			m.terminate();
			return null;
		}
	}

}
