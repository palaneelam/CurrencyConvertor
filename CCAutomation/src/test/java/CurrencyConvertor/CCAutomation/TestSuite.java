package CurrencyConvertor.CCAutomation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Data.LoadProperties;
import page.classes.ExtentFactory;
import page.classes.captureScreenshots;

public class TestSuite {
	private WebDriver driver;
	private String baseURL;
	private String rateConvert;
	boolean rateFound;
	//private String amount, fromCurrency, ToCurrency;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeTest
	public void setup() throws Exception
	{
		report = ExtentFactory.getInstance();
		test = report.startTest("Currency Conversion Test");
		System.setProperty("webdriver.chrome.driver", "C:\\Selenium\\WebDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		baseURL = "http://www.xe.com/currencyconverter/";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//Duration.ofSeconds(4));	
		
	}
	
	
	@Test
	@Parameters({"amount", "fromCurrency", "toCurrency"})
	public void Test_EurINR(String amount, String fromCurrency, String toCurrency) throws Exception
	{
		driver.get(baseURL);
		test.log(LogStatus.INFO, "Application launched...");

		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//amount=LoadProperties.userData.getProperty("amount");
		//fromCurrency=LoadProperties.userData.getProperty("fromCurrency");
		//toCurrency=LoadProperties.userData.getProperty("ToCurrency");
		
	    // Choose amount "1"
	    driver.findElement(By.cssSelector("input#amount")).click();
	    driver.findElement(By.cssSelector("input#amount")).sendKeys(amount);

	    // Choose source currency 
	    driver.switchTo().activeElement().sendKeys(Keys.TAB);
	    driver.switchTo().activeElement().sendKeys(fromCurrency);
	    driver.switchTo().activeElement().sendKeys(Keys.ENTER);

	    // Choose destination currency 
	    driver.switchTo().activeElement().sendKeys(Keys.TAB);
	    driver.switchTo().activeElement().sendKeys(Keys.TAB);
	    driver.switchTo().activeElement().sendKeys(toCurrency);
	    driver.switchTo().activeElement().sendKeys(Keys.ENTER);

	    // Click the [Submit] button
	    driver.switchTo().activeElement().sendKeys(Keys.TAB);
	    driver.switchTo().activeElement().sendKeys(Keys.ENTER);

	    // Assert the conversion rate is correct
	    try 
	    {
	    	rateConvert = driver.findElement(By.xpath("//p[@class=\"result__ConvertedText-sc-1bsijpp-0 gwvOOF\"]//following-sibling::p")).getText();
	    	test.log(LogStatus.PASS, "Currency Converted Successfully");
	    }
	    catch(Exception e)
	    {
	    	try
	    	{
	    		rateConvert = driver.findElement(By.xpath("//div[@class=\"MidmarketQuoterstyles__RateWrapper-hd2z43-13 iVyTUY\"]/div[2]/span[2]")).getText();
	    		test.log(LogStatus.PASS, "Currency Converted Successfully");
	    	}
	    	catch (Exception ex)
	    	{
	    		try
	    		{
	    			rateConvert = driver.findElement(By.xpath("//div[@class=\"quoter__SpacedRow-bhpy0i-14 quoter__CostSpacedRow-bhpy0i-16 cXsPcw lcbTtB\"]/div/p[2]")).getText();
	    			test.log(LogStatus.PASS, "Currency Converted Successfully");
	    		}
	    		catch(Exception ex1)
	    		{
	    			String path = captureScreenshots.takeScreenshot(driver, "First Step");
	    			String imagePath = test.addScreenCapture(path);
	    			test.log(LogStatus.FAIL, "Currency Converted not Successfully", imagePath);
	    		}
	    	}	
	    	System.out.println(e.getMessage());
	    }
	    
	    if(rateConvert != null)
	    {		
	    	rateFound = true;
	    }
	    
	    Assert.assertTrue(rateFound);
	    
	}
	
	@AfterTest
	public void afterTestMethod()
	{
		report.endTest(test);
		report.flush();
		driver.quit();
	}

}
