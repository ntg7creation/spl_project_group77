package bgu.spl.mics.application.services;

import java.util.Hashtable;
import java.util.HashMap;

import bgu.spl.mics.Event;
import bgu.spl.mics.MicroService;
import bgu.spl.mics.application.passiveObjects.Customer;

/**
 * APIService is in charge of the connection between a client and the store.
 * It informs the store about desired purchases using {@link BookOrderEvent}.
 * This class may not hold references for objects which it is not responsible for:
 * {@link ResourcesHolder}, {@link MoneyRegister}, {@link Inventory}.
 * 
 * You can add private fields and public methods to this class.
 * You MAY change constructor signatures and even add new public constructors.
 */
public class APIService extends MicroService{
    private HashMap<Integer,Event<String>> order;
    private Customer customer;


    public APIService(Customer customer, HashMap<Integer,Event<String>> booksTicks) {
        super("APIService : " + customer.getId());
        customer = customer;
        booksTicks = booksTicks;
    

		// TODO Implement this
	}

	@Override
	protected void initialize() {
		// TODO Implement this
		
	}

}
