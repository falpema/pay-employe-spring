package com.falpema.payEmploye.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.falpema.payEmploye.service.PayCalculator;
import com.falpema.payEmploye.util.Utils;

@RestController
@RequestMapping("/api")
public class CalculatePayController {
	public static final Logger log = LoggerFactory.getLogger(CalculatePayController.class);
	
	@Value("${falpema.filename}")
	private String filename;
	@Value("${falpema.fileoutname}")
	private String fileOutname;
	
	@GetMapping(path="/calculatepay/{input}")
	public String calculatePay(@PathVariable String input) {
		double pay = 0d;
		String name = input.split("=")[0];
		String schedule = input.split("=")[1];
		try {
			pay = PayCalculator.calculatePay(schedule);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		StringBuilder output = new StringBuilder("The amount to pay " + name + " is: " + pay + " USD");
		return output.toString();
	}
	
	/**
	 * Generate file output.txt with lines of calculates
	 * @param path with filename or value default  flag to getfile input.txt in resource folder
	 * @return output.txt in path of inputfile or when is default : path /pay-employe-spring/target/classes/output.txt
	 */
	@GetMapping(path = "/generatepayfile")
	public String generatepayfile(
			@RequestParam(required = false, value = "pathfile", defaultValue = "default")  String pathfile
			) {
		Utils util = new Utils();
		InputStream inputStream = null;

		StringBuilder output = new StringBuilder();
		try {
			String data;
			String pathOut;
			Class clazz = CalculatePayController.class;
			if (pathfile.compareTo("default")==0) {	
				inputStream = clazz.getResourceAsStream("/" + filename);
				pathOut=new File(getClass().getClassLoader().getResource(".").getFile() + fileOutname).getPath();
			} else {
				File file = new File(pathfile);
				inputStream = util.getInputStreamFromPathFile(pathfile);
				pathOut = file.getParentFile()+"/"+ fileOutname;
			}
			
			data = util.readFromInputStream(inputStream);
			BufferedReader bufReader = new BufferedReader(new StringReader(data));
			String line = null;

			while ((line = bufReader.readLine()) != null) {
				output.append(calculatePay(line) + "\n");
			}

			 util.writeStringInFile(output.toString(),pathOut);
			 output.append("file was created in "+pathOut);
		} catch (FileNotFoundException e) {
			log.error("FileNotFoundException " + e.getMessage(), e);
		} catch (IOException e) {
			log.error("IOException " + e.getMessage(), e);
		}

		return output.toString();
	}
}
