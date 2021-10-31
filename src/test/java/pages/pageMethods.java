package pages;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import utils.baseTest;

public class pageMethods extends baseTest {
	public static By selectProgramDropDown=By.name("Program");
	public static By rollNo=By.xpath("//input[@name='eno']");
	public static By submit=By.xpath("//input[@name='submit']");
	public static By candidateDetails=By.xpath("//form[@name='FRMResult']/b");
	public static By failedSubjects=By.xpath("//strong[text()='Not Completed']/parent::td/preceding-sibling::td[7]");
	public static By totalSubjects=By.xpath("/html[1]/body[1]/form[1]/p[1]/table[1]/tbody[1]/tr");
	
	public static void stripCandidateDetailsFromGC() {
		System.out.println("-----Stripping Candidate Details from Grade Card : ------");
		List<WebElement> candDetails=driver.findElements(pageMethods.candidateDetails);
		for(int counter=0;counter<candDetails.size()-1;counter++)
		{
			System.out.println(candDetails.get(counter).getText());
		}
	}
		public static void printFailedSubjects() {
			List<WebElement> failedSubs=driver.findElements(pageMethods.failedSubjects);
			System.out.println("----- FAILED SUBJECTS ARE ----- " +failedSubs.size()+" Nos");
			for(int counter=0;counter<failedSubs.size();counter++)
			{
				System.out.println(failedSubs.get(counter).getText());
			}
		}
		public static void exportGC() throws IOException
		{
			Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(3000)).takeScreenshot(driver);
			ImageIO.write(screenshot.getImage(), "jpg", new File(System.getProperty("user.home")+"\\Pictures\\GC.jpg"));
		}
		
}
