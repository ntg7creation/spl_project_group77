package bgu.spl.mics.jsonclass;

import bgu.spl.mics.application.passiveObjects.BookInventoryInfo;

public class storeObjects {
	private BookInventoryInfo[] initialInventory;
	private Resources[] initialResources;
	private Services services;

	public BookInventoryInfo[] getInitialInventory() {
		return initialInventory;
	}

	public void setInitialInventory(BookInventoryInfo[] initialInventory) {
		this.initialInventory = initialInventory;
	}

	public Resources[] getInitialResources() {
		return initialResources;
	}

	public void setInitialResources(Resources[] initialResources) {
		this.initialResources = initialResources;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}
}
