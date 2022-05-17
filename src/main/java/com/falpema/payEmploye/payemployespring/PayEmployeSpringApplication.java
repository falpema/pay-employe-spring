package com.falpema.payEmploye.payemployespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.falpema.payEmploye.service.PayCalculator;

@SpringBootApplication
public class PayEmployeSpringApplication {
	public static final Logger log = LoggerFactory.getLogger(PayEmployeSpringApplication.class);

	public static void main(String[] args) {
		String input = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
		double pay = 0d;
		String name = input.split("=")[0];
		String schedule = input.split("=")[1];
		try {
			pay = PayCalculator.calculatePay(schedule);
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("OUTPUT \n The amount to pay {} is : {} USD", name, pay);
		SpringApplication.run(PayEmployeSpringApplication.class, args);
		
	}

}
