package TC;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.baseTest;

public class generateGradeCard extends baseTest {
	
	@Test
	@Parameters({"course","rollNumber"})
	public void generator(String course,String rollNumber) throws InterruptedException, IOException
	{
		launchIGNOUResultsPage();
		getResult(course,rollNumber);
		Thread.sleep(4000);
		Arrays.stream(new File("C:\\Users\\mobin.francis\\Pictures\\GC\\").listFiles()).forEach(File::delete);
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(driver);
		ImageIO.write(screenshot.getImage(), "jpg", new File("C:\\Users\\mobin.francis\\Pictures\\GC\\fullimage.jpg"));
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.quit();
	}

}
