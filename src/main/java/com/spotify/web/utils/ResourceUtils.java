package com.spotify.web.utils;

import java.io.FileNotFoundException;
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
			EventLogger.info("Reading JSON data from file "+fileName);
            return objectMapper.readValue(inputStream, T);
        }  catch (FileNotFoundException e) {
        	EventLogger.error(fileName+" file not found");
        	throw new IllegalStateException("File not found: " + fileName, e);
		}catch (IOException e) {
			EventLogger.error("Failed to read JSON data from file: "+fileName, e);
        	throw new IllegalArgumentException("Failed to read JSON data from file: "+fileName);
		}
	}
	
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        
        try (InputStream input = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
            	EventLogger.error("Unable to find properties file: "+fileName);
                throw new IllegalStateException("File not found: {}" + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
        	EventLogger.error("Failed to load properties file: "+fileName, ex);
            throw new IllegalStateException("unable to load file: " + fileName);
        }
        return properties.getProperty(key);
    }   
}
