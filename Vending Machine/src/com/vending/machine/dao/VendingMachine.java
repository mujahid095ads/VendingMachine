package com.vending.machine.dao;

import java.util.List;

import com.vending.machine.constants.Event;

public interface VendingMachine {

	public void insertCoin(int coin);
	public List<Integer> refund();
	public void buy();
	public void cancel();
	public void reset();
	public boolean isValidEvent( Event event );
	
}
