package com.falpema.payEmploye.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PayCalculator {

	public static final Logger log = LoggerFactory.getLogger(PayCalculator.class);

	// Strategies of calculating of pay hours

	private final static PayCalculationStrategyFactory payCalculationStrategyFactory = new PayCalculationStrategyFactory();

	public static double calculatePay(String schedule) throws ParseException {
		String[] days = schedule.split(",");
		double pay = 0D;
		try {
			for (String dayHours : days) {
				String day = dayHours.substring(0, 2);
				List<String>  hoursBetween = getHoursbetween(dayHours.substring(2, dayHours.length() ));
				for (String timeRange : hoursBetween) {
					PayCalculationStrategy cal = payCalculationStrategyFactory.getPayHourStrategy(day, timeRange);
					double differenceInHours = getHoursBetweenRangeTime(timeRange);
					pay += cal.calculatePay(differenceInHours);
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return pay;
	}


	/**
	 * get List of hours in range
	 * 
	 * 
	 * @param hours
	 * @return return in this format 10:00-11:00 11:00-12:00
	 * @throws NumberFormatException 
	 */
	private static List<String> getHoursbetween(String hours) throws NumberFormatException {

		String startHour = hours.split("-")[0];
		String finishHour = hours.split("-")[1];
		int diferenceHour = Integer.valueOf(finishHour.split(":")[0]) - Integer.valueOf(startHour.split(":")[0]);
		int diferenceMinuts = Integer.valueOf(finishHour.split(":")[1]) - Integer.valueOf(startHour.split(":")[1]);
		List<String> hoursBetween = new ArrayList();
		for (int i = Integer.valueOf(startHour.split(":")[0]) ; i < Integer.valueOf(finishHour.split(":")[0]) ; i++) {
			hoursBetween.add(formatted(i) + "-" + formatted(i + 1));
		}
		if (diferenceMinuts > 0) {
			String difMinutes = formattedWithOutMinutes(Integer.valueOf(finishHour)).concat(":")
					.concat(formattedWithOutMinutes(diferenceMinuts));
			hoursBetween.add(formatted(Integer.valueOf(startHour) + diferenceHour) + "-" + difMinutes);
		}
		return hoursBetween;
	}



	/**
	 * print "n" with 2 digits and append ":00"
	 * 
	 * @param n
	 * @return
	 */
	public static String formatted(int n) {

		return String.format("%02d:00", n);
	}

	public static String formattedWithOutMinutes(int n) {

		return String.format("%02d", n);
	}
	
	private static double getHoursBetweenRangeTime(String timeRange) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date1 = sdf.parse(timeRange.split("-")[0]);
		Date date2 = sdf.parse(timeRange.split("-")[1]);
		return (Math.abs(date2.getTime() - date1.getTime()) / (60 * 60 * 1000)) % 24;
	}

}
