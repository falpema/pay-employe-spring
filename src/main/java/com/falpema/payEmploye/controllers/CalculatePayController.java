package com.falpema.payEmploye.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.falpema.payEmploye.service.PayCalculator;

public class CalculatePayController {
	public static final Logger log = LoggerFactory.getLogger(CalculatePayController.class);
	public String calculatePay(String input) {
		// input =  "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
		double pay = 0d;
		String name = input.split("=")[0];
		String schedule = input.split("=")[1];
		try {
			pay = PayCalculator.calculatePay(schedule);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		String output = "OUTPUT \n The amount to pay " + name + " is : " + pay + " USD";
		return output;
	}
}
