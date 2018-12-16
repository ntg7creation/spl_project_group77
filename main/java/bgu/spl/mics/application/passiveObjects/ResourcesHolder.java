package bgu.spl.mics.application.passiveObjects;

import java.util.concurrent.ConcurrentLinkedQueue;

import bgu.spl.mics.Future;

/**
 * Passive object representing the resource manager. You must not alter any of
 * the given public methods of this class.
 * <p>
 * This class must be implemented safely as a thread-safe singleton. You must
 * not alter any of the given public methods of this class.
 * <p>
 * You can add ONLY private methods and fields to this class.
 */
public class ResourcesHolder {
	private ConcurrentLinkedQueue<DeliveryVehicle> vehiclesPool;
	private ConcurrentLinkedQueue<Future<DeliveryVehicle>> WaitingForVehicles;

	private ResourcesHolder() {
		this.vehiclesPool = new ConcurrentLinkedQueue<>();
		this.WaitingForVehicles = new ConcurrentLinkedQueue<>();

	}

	private static class Holder {
		private static final ResourcesHolder INSTANCE = new ResourcesHolder();
	}

	/**
	 * Retrieves the single instance of this class.
	 */
	public static ResourcesHolder getInstance() {
		return Holder.INSTANCE;

	}

	/**
	 * Tries to acquire a vehicle and gives a future object which will resolve to a
	 * vehicle.
	 * <p>
	 * 
	 * @return {@link Future<DeliveryVehicle>} object which will resolve to a
	 *         {@link DeliveryVehicle} when completed.
	 */
	public Future<DeliveryVehicle> acquireVehicle() {

		Future<DeliveryVehicle> futureVehicle = new Future<>();

			DeliveryVehicle incomingVehicle = vehiclesPool.poll();
			if (incomingVehicle != null) {
				futureVehicle.resolve(incomingVehicle);
			} else {
		//		System.out.println("someoneIsWaiting for vehicle");
				WaitingForVehicles.add(futureVehicle);
			}
			return futureVehicle;
		
	}

	/**
	 * Releases a specified vehicle, opening it again for the possibility of
	 * acquisition.
	 * <p>
	 * 
	 * @param vehicle
	 *            {@link DeliveryVehicle} to be released.
	 */
	public void releaseVehicle(DeliveryVehicle vehicle) {

			Future<DeliveryVehicle> nextVehicle = WaitingForVehicles.poll();
			if (nextVehicle == null) {
				vehiclesPool.add(vehicle);
			} else {
			//	System.out.println("give vhicle to the waiting guy");
				nextVehicle.resolve(vehicle);
			}
		
	}

	/**
	 * Receives a collection of vehicles and stores them.
	 * <p>
	 * 
	 * @param vehicles
	 *            Array of {@link DeliveryVehicle} instances to store.
	 */
	public void load(DeliveryVehicle[] vehicles) {
		// int j = 0;
		// while (j < vehicles.length);
		// vehiclesPool.add(vehicles[j]);
		// j = j++;
		for (int i = 0; i < vehicles.length; i++) {
			vehiclesPool.add(vehicles[i]);
		}
	}
}
