package com.vending.machine.constants;

public enum Event {

	ONE_R(1, "1R"), TWO_R(2, "2R"), CANCEL(0, "Cancel"), BUY(0, "Buy"), INVALID(-1, "Invalid Event");
	
	private int coinValue;
	private String name;
	
	
	private Event(int coinValue, String name) {
		this.coinValue = coinValue;
		this.name = name;
	}


	public int getCoinValue() {
		return coinValue;
	}


	public void setCoinValue(int coinValue) {
		this.coinValue = coinValue;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
