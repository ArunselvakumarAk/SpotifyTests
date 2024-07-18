package com.spotify.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UtilityMethods{
	
	public static <T> T getJsonData(String fileName, Class<T> T) {
		ObjectMapper objectMapper = new ObjectMapper();
		String filePath = "src\\test\\java\\com\\spotify\\web\\testdata\\" + fileName;
		try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            return objectMapper.readValue(inputStream, T);
        }  catch (IOException e) {
        	throw new IllegalArgumentException("File not found: " + fileName);
		}
	}
	
//	public static <T> T getJsonData(String fileName, Class<T> T) {
//		ObjectMapper objectMapper = new ObjectMapper();
//		//InputStream resourceAsStream = UtilityMethods.class.getClassLoader().getResourceAsStream(fileName);
//		InputStream resourceAsStream = null;
//		try {
//			resourceAsStream = new FileInputStream(new File(fileName));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(resourceAsStream!=null) {
//			try {
//				return objectMapper.readValue(resourceAsStream, T);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}else {
//			throw new IllegalArgumentException("File not found: " + fileName);
//		}
//		return null;
//	}

}
