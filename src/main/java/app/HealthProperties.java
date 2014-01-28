package app;

import java.io.IOException;
import java.util.Properties;

public class HealthProperties {
	private static Properties properties=new Properties();

	public void loadProperties() {
		try {
			properties.load(getClass().getClassLoader().getResourceAsStream(
					"config.properties"));
//			System.out.println("Loaded successfully");
		} catch (IOException e) {
			System.out.println("File with properties doesn't exist or was not loaded correctly");
		}
	}

	public static String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
