package com.vending.machine.service;

import com.vending.machine.constants.Event;
import com.vending.machine.dao.VendingMachine;
import com.vending.machine.impl.ProductMachine;

public class ProductMachineController {

	
	private VendingMachine machine = null;
	
	public ProductMachineController() {
		this.machine = new ProductMachine();
		System.out.println(machine);
	}
	
	
	public void acceptEvent(Event event) {
		
		switch (event) {
		case CANCEL:	
			System.out.println("User Event : Cancel");
			machine.cancel();
			break;
		case BUY: 
			System.out.println("User Event : Buy");
				machine.buy();
			break;
		case ONE_R:
		case TWO_R:
			System.out.println("User Event : "+event.getName());
			machine.insertCoin(event.getCoinValue());
			break;
		default:	//if unexpected input is given handle it
			System.out.println(ProductMachine.INVALID_OPTION);	//in real application we must throw an exception here
			//We can also check for max wrong attempts here if needed and cancel the transaction if required
			break;
		}
		System.out.println(machine);
	}
}
