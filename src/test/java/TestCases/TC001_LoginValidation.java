package TestCases;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import DataProviders.ExcelDataProvider;
import PageLibrary.SignIn;
import TestBase.TestBase;
import Utility.Helper;

public class TC001_LoginValidation extends TestBase {

	ExtentReports report;
	ExtentTest logger;
	File fi;

	@BeforeMethod
	public void setup() throws IOException {
		report = new ExtentReports("./Reports/LoginValidation.html", false);
		logger = report.startTest("Verify login", "This script will test login process and page title");
		init();
		logger.log(LogStatus.INFO, "Aplication is up and running");
	}

	@Test
	public void testAppTitle() {
		SignIn signin = PageFactory.initElements(driver, SignIn.class);
		String title = signin.getAppTitle();
		Assert.assertTrue(title.contains("My Store"));
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.TakeScreenShot(driver, "TitleValidation")));
		logger.log(LogStatus.PASS, "Aplication title is verified");
	}

	@Test
	public void testLogin() throws InterruptedException {
		SignIn signin = PageFactory.initElements(driver, SignIn.class);
		ExcelDataProvider ecldp = new ExcelDataProvider();
		signin.clickonSignIn();
		logger.log(LogStatus.INFO, "clicked on sign in button");
		signin.enterAllreadyRegisterUserEmail(ecldp.getData(0, 1, 0));
		logger.log(LogStatus.INFO, "entered username");
		signin.enterAllReadyRegisteredPassword(ecldp.getData(0, 1, 1));
		logger.log(LogStatus.INFO, "entered password");
		signin.clickonSignIn();
		waitFor(10);
		Assert.assertTrue(signin.getAppTitle().contains("Login - My Store"));
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.TakeScreenShot(driver, "LoginValidation")));
		logger.log(LogStatus.PASS, "Login process verified");
		waitFor(10);
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (ITestResult.FAILURE == result.getStatus()) {
			String path = Helper.TakeScreenShot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}

		closeBrowser();
		logger.log(LogStatus.INFO, "tear down successfull");
		report.endTest(logger);
		report.flush();
	}

}
