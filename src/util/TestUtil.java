package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestUtil {

	public static Properties readProperties() {
		Properties config = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					"C:\\Users\\aakis\\workspace\\Selenium_project1\\resources\\config.properties");
			config.load(fis);
		}
		catch (IOException io) {
			io.printStackTrace();
		}
		return config;
	}
}
