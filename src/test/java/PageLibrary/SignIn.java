package PageLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import DataProviders.ExcelDataProvider;

public class SignIn {

	WebDriver driver;
	
	By signIn = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[1]/a");
	By createAccEmailAddr = By.xpath("//*[@id='email_create']");
	By allreadyRegEmailAddr = By.xpath("//*[@id='email']");
	By allReadyRegPass = By.xpath("//*[@id='passwd']");
	By submitbutton = By.xpath("//*[@id='SubmitLogin']");
	By createAnAccount = By.xpath("//*[@id='SubmitCreate']");
	By signOut = By.xpath("//*[@id='header']/div[2]/div/div/nav/div[2]/a");
	By createAnAccountValidation = By.xpath("//*[text()='Create an account']");
	
	public SignIn(WebDriver driver){
		this.driver = driver;
	}
	
	/**
	 * This method will click on sign in link in login page
	 */
	
	public void clickonSignIn(){

		try {
			driver.findElement(signIn).click();
			//test.log(LogStatus.PASS, "Clicking on sing in Link");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will enter email address to create an account text box.
	 * In Login Page
	 * @param emailAddress
	 */
	public String getAppTitle(){
		return driver.getTitle();
	}
	
	public void enterAllreadyRegisterUserEmail(String emailAddress){
		driver.findElement(allreadyRegEmailAddr).sendKeys(emailAddress);
	}
	
	public void enterAllReadyRegisteredPassword(String password){
		driver.findElement(allReadyRegPass).sendKeys(password);
	}
	
	public void clickonSignInToAccount(){
		driver.findElement(submitbutton).click();
	}
	
	public void enterEmailAddressToCraeteAccount(String emailAddress){
		driver.findElement(createAccEmailAddr).sendKeys(emailAddress);
	}
	
	public void clickOnCreateAnAccount(){
		driver.findElement(createAnAccount).click();
	}
	
	public boolean createAnAccountTextPresent(){
		return driver.findElement(createAnAccountValidation).isDisplayed();
	}
	
	/**
	 * This Method is used for login to application
	 */
	
	public void enterDataToCreateAnAccount(String emailAddress){
		clickonSignIn();
		enterEmailAddressToCraeteAccount(emailAddress);
		clickOnCreateAnAccount();
	}
	
	public void logout(){
		try{
		boolean isdisplayed = driver.findElement(signOut).isDisplayed();
		if(isdisplayed){
			driver.findElement(signOut).click();
		} else{
			Assert.assertTrue(false, "sign out buttom is not displayed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}   	
}
