package com.restasssured.constant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



// Singleton class so that the only one object of the class is created.
public final class readPropertyFile {
	private static final String CONFIG_PROPERTIES_FILE_NAME = "/config.properties";
	private static readPropertyFile instance = new readPropertyFile();
	private static Properties configProps = new Properties();
	
	private readPropertyFile() {
		// Do-nothing..Do not allow to initialize this class from outside
	}

	
	public static readPropertyFile getInstance(){
		return instance;
	}
	

	public  Properties getconfigProps() {
		if(configProps.isEmpty()) {
			configProps = loadProperties(CONFIG_PROPERTIES_FILE_NAME);
		}
		return configProps;
	    }

	
	public Properties loadProperties(final String fileName) {
		InputStream inputStream = null;
		Properties props = new Properties();
		try {
			String propFileName = fileName;
			inputStream = getClass().getResourceAsStream(propFileName);

			if (inputStream != null) {
				props.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();

				}
			}
		}
		return props;
	}
	
	
}
