package jsonclass;

import bgu.spl.mics.application.passiveObjects.Customer;
import bgu.spl.mics.application.services.InventoryService;
import bgu.spl.mics.application.services.LogisticsService;
import bgu.spl.mics.application.services.ResourceService;
import bgu.spl.mics.application.services.SellingService;
import bgu.spl.mics.application.services.TimeService;

public class Services {
	private TimeService time;
	private int selling;
	private int inventoryService;
	private int logistics;
	private int resourcesService;
	private Customer[] customers;

	/**
	 * @return the time
	 */
	public TimeService getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(TimeService time) {
		this.time = time;
	}

	/**
	 * @return the selling
	 */
	public int getSelling() {
		return selling;
	}

	/**
	 * @param selling
	 *            the selling to set
	 */
	public void setSelling(int selling) {
		this.selling = selling;
	}

	/**
	 * @return the inventoryService
	 */
	public int getInventoryService() {
		return inventoryService;
	}

	/**
	 * @param inventoryService
	 *            the inventoryService to set
	 */
	public void setInventoryService(int inventoryService) {
		this.inventoryService = inventoryService;
	}

	/**
	 * @return the logistics
	 */
	public int getLogistics() {
		return logistics;
	}

	/**
	 * @param logistics
	 *            the logistics to set
	 */
	public void setLogistics(int logistics) {
		this.logistics = logistics;
	}

	/**
	 * @return the resourcesService
	 */
	public int getResourcesService() {
		return resourcesService;
	}

	/**
	 * @param resourcesService
	 *            the resourcesService to set
	 */
	public void setResourcesService(int resourcesService) {
		this.resourcesService = resourcesService;
	}

	/**
	 * @return the customers
	 */
	public Customer[] getCustomers() {
		return customers;
	}

	/**
	 * @param customers
	 *            the customers to set
	 */
	public void setCustomers(Customer[] customers) {
		this.customers = customers;
	}

	
}
