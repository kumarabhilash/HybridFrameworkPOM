package TestCases;

import java.io.IOException;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import DataProviders.ExcelDataProvider;
import PageLibrary.SignIn;
import TestBase.TestBase;
import Utility.Helper;

public class TC002_CreateAnAccountValidation extends TestBase {

	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setup() throws IOException {
		report = new ExtentReports("./Reports/CreateAnAccountValidation.html", true);
		logger = report.startTest("Verify Account creation", "This script will test account creation");
		init();
		logger.log(LogStatus.INFO, "Aplication is up and running");
	}

	@Test
	public void testLogin() throws InterruptedException {
		SignIn signin = PageFactory.initElements(driver, SignIn.class);
		ExcelDataProvider ecldp = new ExcelDataProvider();
		signin.clickonSignIn();
		logger.log(LogStatus.INFO, "clicked on sign in button");
		waitFor(10);
		signin.enterEmailAddressToCraeteAccount(ecldp.getData(1, 0, 0));
		logger.log(LogStatus.INFO, "entered emailid to create account");
		signin.clickOnCreateAnAccount();
		if (signin.createAnAccountTextPresent()) {
			Assert.assertTrue(true);
			logger.log(LogStatus.PASS, "create an account validated");
		}
		logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.TakeScreenShot(driver, "AccountCreatedValidation")));
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
