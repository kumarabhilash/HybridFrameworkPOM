package Utility;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helper {
	
	
	public static String TakeScreenShot(WebDriver driver, String screenShotName){
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		String destination = "C:\\Users\\kumar\\workspace\\com.learnautomation.hybrid\\ScreenShots\\"+screenShotName+System.currentTimeMillis()+".png";
		try {
			FileUtils.copyFile(src, new File(destination));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return destination;
	}
}
