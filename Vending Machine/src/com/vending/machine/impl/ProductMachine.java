package com.vending.machine.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.vending.machine.constants.Event;
import com.vending.machine.constants.States;
import com.vending.machine.dao.VendingMachine;

public class ProductMachine implements VendingMachine{

	private static final int VALID_AMOUNT = 4;
	public static final String INVALID_OPTION = "Invalid Event!!!";
	
	private int totalAmount;		//private to restrict external manipulation with encapsulation
	private String currentState;
	private List<Integer> coins;
	private Set<Event> events;
	
	
	public ProductMachine() {
		
		this.coins = new ArrayList<>();
		this.events = new LinkedHashSet<>();
		this.reset();
	}
	
	
	@Override
	public void insertCoin(int coin) {
		
		this.totalAmount += coin;
		this.coins.add(coin);
		
		
		//Check if total exceeds expected amount
		if( this.isAmountExceeded() ) {
			this.cancel();
		} else if( this.isValidAmount() ) {	//enable buy option to end user
			this.addBuyEvent();
		}
		autoUpdateCurrentState();
	}
	
	

	@Override
	public List<Integer> refund() {
		if( this.coins.size() > 0 ) {	//refund only if any coins inserted
			
			System.out.print("Please Collect your coins : ");
			for(int coin: coins) System.out.print(coin+" ");
			System.out.println("");
			
		} else System.out.println("No coins inserted to refund!!!");
		
		return this.coins;
	}
	@Override
	public void buy() {
		if( this.isValidAmount() ) {
			System.out.println("Please Collect Your Item :)");
			reset();
		} else {
			//System.out.println("Item cost is "+VALID_AMOUNT+" and currently inserted coins total amount is "+this.totalAmount+"!!!");
			System.out.println(INVALID_OPTION);
		}
	}
	@Override
	public void cancel() {
		this.refund();
		this.reset();
		setUpdateState(States.CANCELLED);
	}
	@Override
	public void reset() {
		//Set initial state 
		this.currentState = States.STATE_0;
		this.totalAmount = 0;
		
		
		this.coins.clear();
		this.events.clear();
		
		//Add possible events on first initialization 
		this.events.add(Event.ONE_R);
		this.events.add(Event.TWO_R);
		this.events.add(Event.CANCEL);
	}

	public void setUpdateState(String state) {
		System.out.print(this.currentState);
		this.currentState = state;
		System.out.println(" -> "+this.currentState);
	}
	@Override
	public boolean isValidEvent( Event event ) {
		return this.events.contains(event);
	}

	private void addBuyEvent() {
		this.events.remove(Event.ONE_R);
		this.events.remove(Event.TWO_R);
		this.events.add(Event.BUY);
	}
		
	private boolean isValidAmount() {
		return this.totalAmount == VALID_AMOUNT;
	}
	
	private boolean isAmountExceeded() {
		return this.totalAmount > VALID_AMOUNT;
	}

	private void autoUpdateCurrentState() {
		if( this.totalAmount == 1 ) setUpdateState(States.STATE_1);
		else if( this.totalAmount == 2 ) setUpdateState(States.STATE_2);
		else if( this.totalAmount == 3 ) setUpdateState(States.STATE_3);
		else if( this.totalAmount == 4 ) setUpdateState(States.STATE_4);
	}
	
	@Override
	public String toString() {
		
		StringBuilder log = new StringBuilder("\nCurrent Machine State : ")
				.append(currentState).append(", Total Amount inserted : ")
				.append(totalAmount).append("\nPlease select an option : ");
		
		for( Event event: this.events ) {
			log.append(event.getName()).append(", ");
		}
		log.replace(log.length()-2, log.length(), "");		//remove extra comma
		
		return log.toString();
	}
	
	
}