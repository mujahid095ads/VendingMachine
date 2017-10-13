package com.vending.machine.test;

import com.vending.machine.constants.Event;
import com.vending.machine.service.ProductMachineController;

public class TestProductMachine {

	public static void main(String[] args) {
				
		Event[][] eventsInput = {
				{Event.ONE_R, Event.TWO_R, Event.ONE_R, Event.BUY},
				{Event.ONE_R, Event.ONE_R, Event.ONE_R, Event.BUY},
				{Event.ONE_R, Event.ONE_R, Event.TWO_R, Event.BUY},
				{Event.ONE_R, Event.ONE_R, Event.ONE_R, Event.ONE_R, Event.CANCEL},
				{Event.CANCEL, Event.TWO_R, Event.TWO_R, Event.BUY},
				{Event.TWO_R, Event.TWO_R, Event.ONE_R, Event.CANCEL},
				{Event.INVALID, Event.TWO_R, Event.INVALID, Event.TWO_R, Event.BUY},
		};
		
		for( Event[] events: eventsInput) {
			
			ProductMachineController machine = new ProductMachineController();
			
			for(Event event: events ) {
				machine.acceptEvent(event);
			}
			
			System.out.println("\n\n**************************************************\n\n");
		}
	}
}
