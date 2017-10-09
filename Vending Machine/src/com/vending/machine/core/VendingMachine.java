package com.vending.machine.core;
import java.util.Scanner;

public class VendingMachine {

	private static final String INVALID_OPTION = "\nInvalid Input, Please insert any of the below option.";
	private int amount;
	
	public VendingMachine() {
		this.reset();
	}
	
	private void reset() {
		this.amount = 0;
	}
	private void refund() {
		if( this.amount > 0 ) {		//Refund only if any amount was inserted
			System.out.println("Please Collect your amount : "+this.amount);
		}
		this.reset();
	}
	
	private void insertCoin() {
		try( Scanner sc=new Scanner(System.in);  ){
			System.out.print("\nOptions :"+( this.amount == Events.VALID_AMOUNT ? " B = Buy,":" 1 = 1R, 2 = 2R," )+" C = Cancel \nPlease insert your Option = "); 
			String input = sc.next();
			validateInput(input);
		}
	}
	
	private void validateInput(String input) {
		
		switch (input.toUpperCase()) {
			case Events.CANCEL:	//if user opt to cancel then refund if any amount was inserted
				this.refund();
				System.out.println("CANCELLED");
				break;
			case Events.BUY: 
					validateAndBuy();	//Validate before Buy
				break;
			case Events.ONE_R:
			case Events.TWO_R:
				try {
					this.amount += Integer.parseInt(input);
					if( this.amount > Events.VALID_AMOUNT ) {	//check if amount goes beyond 4 then cancel the transaction
						validateInput(Events.CANCEL);
						break;
					}
				} catch (Exception e) {	//if unexpected input is given handle it
					System.out.println(INVALID_OPTION);
				}
				insertCoin();	//if all fine accept next input
				break;
			default:	//if unexpected input is given handle it
				System.out.println(INVALID_OPTION);
				insertCoin();
				//We can also check for max wrong attempts here if needed and cancel the transaction if required
				break;
		}
	}
	
	private void validateAndBuy() {
		if( this.amount < Events.VALID_AMOUNT ) {
			//System.out.println("Item cost is "+Events.VALID_AMOUNT+", Amount recieved is "+this.amount+", please insert remaining amount.");
			System.out.println(INVALID_OPTION);
			insertCoin();
		} else {
			System.out.println("COMPLETED");
			this.reset();
		}
	}
	
	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine();
		
		machine.insertCoin();
	}

}