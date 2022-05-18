package com.falpema.payEmploye.payemployespring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan({"com.falpema.payEmploye.controller", "com.falpema.payEmploye.service"})
public class PayEmployeSpringApplication {


	public static void main(String[] args) {
		
		SpringApplication.run(PayEmployeSpringApplication.class, args);
	}

}
