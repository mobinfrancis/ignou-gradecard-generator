package TC;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.pageMethods;
import utils.baseTest;

public class generateGradeCard extends baseTest {
	public WebDriver driver;
	
	@Test
	@Parameters({"course","rollNumber"})
	public void generator(String course,String rollNumber) throws InterruptedException, IOException
	{
		launchIGNOUResultsPage();
		getResult(course,rollNumber);
		Thread.sleep(4000);
		pageMethods.exportGC();
	}
	
	//@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
