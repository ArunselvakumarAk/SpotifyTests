package com.spotify.web.utils;

import java.io.IOException;
import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestdataUtils {
	
	public static <T> T getJsonData(String fileName, Class<T> T) {
		ObjectMapper objectMapper = new ObjectMapper();
		InputStream resourceAsStream = TestdataUtils.class.getClassLoader().getResourceAsStream(fileName);
		try {
			return objectMapper.readValue(resourceAsStream, T);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
