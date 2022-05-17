package com.falpema.payEmploye.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Unit Test of calculate of pay of roll
 * @author falpema
 *
 */
class CalculatePayControllerTest {

	@Test
	void test() {
		CalculatePayController controller = new CalculatePayController();
		String response = controller.calculatePay("RENE=MO10:00-12:00,TU10:00-12:00,TH01:00-03:00,SA14:00-18:00,SU20:00-21:00");
		assertEquals("The amount to pay RENE is: 215.0 USD",response);
	}

}
