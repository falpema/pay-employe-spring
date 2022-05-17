package com.falpema.payEmploye.payemployespring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.falpema.payEmploye.controllers.CalculatePayController;
import com.falpema.payEmploye.service.PayCalculator;

@SpringBootApplication
public class PayEmployeSpringApplication {
	public static final Logger log = LoggerFactory.getLogger(PayEmployeSpringApplication.class);

	public static void main(String[] args) {
		CalculatePayController controller = new  CalculatePayController();
		String input = "RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00";
		String output = controller.calculatePay(input);
		log.info("OUTPUT \n  {} USD", output);
		SpringApplication.run(PayEmployeSpringApplication.class, args);
		
	}

}
