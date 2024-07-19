package com.spotify.web.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResourceUtils{
	
	public static <T> T getJsonData(String fileName, Class<T> T) {
		ObjectMapper objectMapper = new ObjectMapper();
		String filePath = "src\\test\\resources\\testdata\\" + fileName;
		try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            return objectMapper.readValue(inputStream, T);
        }  catch (IOException e) {
        	throw new IllegalArgumentException("File not found: " + fileName);
		}
	}
	
	// Method to load properties file and get the value of a given key
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        try (InputStream input = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
                System.out.println("unable to find " + fileName);
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return properties.getProperty(key);
    }

}
