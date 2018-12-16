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
	private ConcurrentLinkedQueue<DeliveryVehicle> vehicles;
	private ConcurrentLinkedQueue<Future<DeliveryVehicle>> WaitingForVehicles;

	private ResourcesHolder() {
		this.vehicles = new ConcurrentLinkedQueue<>();
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

			DeliveryVehicle incomingVehicle = vehicles.poll();
			if (incomingVehicle != null) {
				futureVehicle.resolve(incomingVehicle);
			} else {
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
				vehicles.add(vehicle);
			} else {
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
			this.vehicles.add(vehicles[i]);
		}
	}
}
