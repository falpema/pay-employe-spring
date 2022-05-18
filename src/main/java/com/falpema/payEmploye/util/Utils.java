package com.falpema.payEmploye.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
	

}
