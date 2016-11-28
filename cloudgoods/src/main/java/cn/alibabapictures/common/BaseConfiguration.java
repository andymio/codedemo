package cn.alibabapictures.common;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseConfiguration {

	private static String filePath = "src/main/resources/configuration.properties";
	private static String baseUrlKey = "base.url";
	private static String loginUrlKey = "login.url";
	private static String driverTypeKey = "driver.type";
	private static String driverPathKey = "driver.path";
	
	public static String getBaseUrl() {
		return getValue(baseUrlKey);
	}

	public static String getLoginUrl() {
		return getValue(loginUrlKey);
	}
	
	public static String getDriverType() {
		return getValue(driverTypeKey);
	}
	public static String getDriverPath() {
		return getValue(driverPathKey);
	}	
	
	public static String getValue(String key) {
		Properties configProperties = new Properties();
		try {
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
			configProperties.load(in);
			return configProperties.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

}
