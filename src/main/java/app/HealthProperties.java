package app;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class HealthProperties {
	private static Properties properties = new Properties();

	public void loadProperties() {
		try {
			FileInputStream file;
			String path = "./config.properties";
			file = new FileInputStream(path);
			properties.load(file);
			file.close();
		} catch (IOException e) {
			System.out.println("External config file was not found!\nReading internal config file...");
			try {
				properties.load(getClass().getClassLoader()
						.getResourceAsStream("config.properties"));
			} catch (IOException e1) {
				System.out
						.println("File with properties doesn't exist or was not loaded correctly"
								+ e1.toString());
			}
		}
	}

	public static String getProperty(String propertyName) {
		return properties.getProperty(propertyName);
	}
}
