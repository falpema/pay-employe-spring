package com.falpema.payEmploye.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public String readFromInputStream(InputStream inputStream) throws IOException {
		StringBuilder resultStringBuilder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				resultStringBuilder.append(line).append("\n");
			}
		}
		return resultStringBuilder.toString();
	}

	public InputStream getInputStreamFromPathFile(String filepath) throws FileNotFoundException {
		InputStream inputStream = null;
		try {
		    File file = new File(filepath);
		     inputStream = new FileInputStream(file);
		    return inputStream;
		   
		}     
		finally {
		    if (inputStream != null) {
		        try {
		            inputStream.close();
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }
		}
		
	}

	public void writeStringInFile(String content, String pathOut) throws IOException {
		FileOutputStream outputStream = new FileOutputStream(pathOut);
		byte[] strToBytes = content.getBytes();
		outputStream.write(strToBytes);
		outputStream.close();

	}
	
	/**
	 * get string in a format with 2 digits and append ":00"
	 * 
	 * @param n number
	 * @return "n" with 2 digits and append ":00"
	 */
	public static String formatted(int number) {

		return String.format("%02d:00", number);
	}
	
	/**
	 * get string in a format with 2 digits "
	 * 
	 * @param n number
	 * @return "n" with 2 digits "
	 */
	public static String formattedWithOutMinutes(int number) {

		return String.format("%02d", number);
	}
	
	/**
	 * Get amount of hours between two moments
	 * @param timeRange 10:00-12:00
	 * @return amount of hours between
	 * @throws ParseException
	 */
	public static double getHoursBetweenRangeTime(String timeRange) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Date date1 = sdf.parse(timeRange.split("-")[0]);
		Date date2 = sdf.parse(timeRange.split("-")[1]);
		return (Math.abs(date2.getTime() - date1.getTime()) / (60 * 60 * 1000)) % 24;
	}

}
