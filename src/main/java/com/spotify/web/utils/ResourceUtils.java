package com.spotify.web.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ResourceUtils{
	public static Logger log = LogManager.getLogger(ResourceUtils.class);
	
	public static <T> T getJsonData(String fileName, Class<T> T) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		String filePath = "src\\test\\resources\\testdata\\" + fileName;
		
		try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
			log.info("Reading JSON data from file: {}", fileName);
            return objectMapper.readValue(inputStream, T);
        }  catch (FileNotFoundException e) {
        	log.error("{} File not found", fileName);
        	throw new IllegalStateException("File not found: " + fileName, e);
		}catch (IOException e) {
        	log.error("Failed to read JSON data from file: {}", fileName, e);
        	throw new IllegalArgumentException("Failed to read JSON data from file: {} " + fileName);
		}
	}
	
    public static String getProperty(String fileName, String key) {
        Properties properties = new Properties();
        
        try (InputStream input = ResourceUtils.class.getClassLoader().getResourceAsStream(fileName)) {
            if (input == null) {
            	log.error("Unable to find properties file: {}", fileName);
                throw new IllegalStateException("File not found: {}" + fileName);
            }
            properties.load(input);
        } catch (IOException ex) {
        	log.error("Failed to load properties file: {}", fileName, ex);
            throw new IllegalStateException("unable to load file: {}" + fileName);
        }
        return properties.getProperty(key);
    }   
}
