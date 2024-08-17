package com.spotify.web.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class EventLogger {
	
	private static final Logger logger = LogManager.getLogger(EventLogger.class);

    public static void startTestCase(String testCaseName) {
    	String logFileName = testCaseName.replaceAll("[^a-zA-Z0-9]", "_").replaceAll("_+", "_");
        ThreadContext.put("logFilename", logFileName);
        info("************** Execution started for " + testCaseName + "**************");
    }

    public static void endTestCase(String testCaseName) {
        info("************** Execution ended for " + testCaseName + "**************");
    }

    public static void error(Object message) {
        logger.error(message);
    }

    public static void error(Object message, Throwable t) {
        logger.error(message, t);
    }

    public static void info(Object message) {
        logger.info(message);
    }

    public static void info(Object message, Throwable t) {
        logger.info(message, t);
    }
}
