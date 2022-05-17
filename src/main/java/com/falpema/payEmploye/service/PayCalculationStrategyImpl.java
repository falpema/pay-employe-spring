package com.falpema.payEmploye.service;

/**
 * Concrete Strategys
 * 
 * @author falpema
 *
 */
class PayHoursMondayFridayCalculationFrom0To9 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 25;
	}

}

class PayHoursMondayFridayCalculationFrom9To18 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 15;
	}

}

class PayHoursMondayFridayCalculationFrom18To24 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 20;
	}

}

class PayHoursSaturdaySundayCalculationFrom0To9 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 30;
	}

}

class PayHoursSaturdaySundayCalculationFrom9To18 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 20;
	}

}

class PayHoursSaturdaySundayCalculationFrom18To24 implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return workHours * 25;
	}

}

class NoPayCalculation implements PayCalculationStrategy {

	public double calculatePay(double workHours) {

		return 0;
	}
}
