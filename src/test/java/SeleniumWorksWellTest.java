import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumWorksWellTest {
	private WebDriver driver;
	private String path = "http://localhost:8080/Controller";

	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mats\\Desktop\\Software\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(path+"?command=Register");
	}


		@After
		public void clean(){
			driver.quit();
		}
		
		@Test 
		public void browserVindtWikipedia() {
			assertEquals("Wikipedia, de vrije encyclopedie", driver.getTitle());
		}

		@Test
		public void wikipediaVindtSelenium() {
			WebElement field = driver.findElement(By.id("searchInput"));
			field.clear();
			field.sendKeys("selenium");
			WebElement link = driver.findElement(By.id("searchButton"));
			link.click();
			
			assertEquals("Selenium - Wikipedia", driver.getTitle());
			
			assertEquals("Selenium", driver.findElement(By.tagName("h1")).getText());

	}

}
