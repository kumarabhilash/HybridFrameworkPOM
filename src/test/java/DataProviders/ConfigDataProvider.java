package DataProviders;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {

	File file;
	FileInputStream fis;
	Properties prop;

	public ConfigDataProvider() {

		file = new File("./Configuration/AppConfig.properties");
		try {
			fis = new FileInputStream(file);
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is : " + e.getMessage());
		}
	}

	public String getAppUrl() {

		String AppUrl = prop.getProperty("url");
		return AppUrl;
	}
	
	public String getChromePath() {

		return prop.getProperty("ChromeExe");
	}
	
	public String getFirefoxPath() {

		return prop.getProperty("FirfoxExe");
	}
	
	public String getIePath() {

		return prop.getProperty("IeExe");

	}

}
