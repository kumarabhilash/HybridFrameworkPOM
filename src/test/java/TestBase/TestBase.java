package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import DataProviders.ConfigDataProvider;

public class TestBase {

	public WebDriver driver;
	

	public void init() {
		SelectBrowser("chrome");
	}

	public WebDriver SelectBrowser(String browser){
		if(browser.equals("firefox") || browser.equals("FIREFOX")){
			ConfigDataProvider cdp = new ConfigDataProvider();
			System.setProperty("webdriver.gecko.driver", cdp.getFirefoxPath());
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
			driver.get(cdp.getAppUrl()); 
			return driver;
		} else if(browser.equals("chrome") || browser.equals("CHROME")){
			ConfigDataProvider cdp = new ConfigDataProvider();
			System.setProperty("webdriver.chrome.driver", cdp.getChromePath());
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.get(cdp.getAppUrl()); 
			return driver;
		} else if(browser.equals("ie") || browser.equals("IE")){
			ConfigDataProvider cdp = new ConfigDataProvider();
			System.setProperty("webdriver.ie.driver", cdp.getIePath());
			driver= new InternetExplorerDriver();
			driver.manage().window().maximize();
			driver.get(cdp.getAppUrl()); 
			return driver;
		}
		return null;		
	}
	
	public void waitFor(int timeInSeconds) throws InterruptedException{
		Thread.sleep(timeInSeconds * 1000);
	}
	
	public void closeBrowser() {
		driver.close();
	}

}
