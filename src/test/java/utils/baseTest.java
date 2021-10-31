package utils;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.pageMethods;
import testData.App;

public class baseTest
{
	public WebDriver driver;
	
	public void launchIGNOUResultsPage()
	{
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(App.baseURL);
		
	}
	
	public void getResult(String course,String rollno) throws InterruptedException
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+course+"')]")).click();
		Thread.sleep(2000);
		Set<String>s=driver.getWindowHandles();
		Iterator<String> iterator= s.iterator();
		while(iterator.hasNext())
			{
			String child_window=iterator.next();
			driver.switchTo().window(child_window);
			driver.switchTo().window(child_window);
			}
		
		if(driver.getTitle().contains("500"))
			{
			Assert.fail("[INFO] IGNOU Site is Down, Please try again later");
			}
		else
			{
			System.out.println("[INFO] Launched IGNOU Results Site successfully.");
			}
		dropDownSelectionByValue(driver,pageMethods.selectProgramDropDown,course);
		enterValue(driver,pageMethods.rollNo,rollno);
		clickElement(driver,pageMethods.submit);
	}
	//For selecting value from dropdown
	public static void dropDownSelectionByValue(WebDriver driver, By dropDownID, String dropDownValue) {
		try {
			WebElement element = null;
			new WebDriverWait(driver, 5)
				.ignoring(StaleElementReferenceException.class)
				.until(ExpectedConditions.elementToBeClickable(dropDownID));
			element = driver.findElement(dropDownID);
			Select dropDown = new Select(element);
			dropDown.selectByValue(dropDownValue);
			System.out.println("[INFO] Selected course is: "+dropDownValue);
		}
		catch (StaleElementReferenceException ex) {
			System.out.println("Exception while selecting a value from dropdown" + ex.getMessage());
		}
  
}	//For sendKeys
	public static void enterValue(WebDriver driver,By elementToSend,String txtToSend)
	  {
		WebElement element = null;
		new WebDriverWait(driver, 5)
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(elementToSend));
		element = driver.findElement(elementToSend); 
		element.sendKeys(txtToSend);
		System.out.println("[INFO] Entered Roll Number is : "+txtToSend);
	  }
	//For clicking
	public static void clickElement(WebDriver driver,By elementToClick)
	  {
		WebElement element = null;
		new WebDriverWait(driver, 5)
			.ignoring(StaleElementReferenceException.class)
			.until(ExpectedConditions.elementToBeClickable(elementToClick));
		element = driver.findElement(elementToClick); 
		element.click();
		
}
}
