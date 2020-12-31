import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Mats\\Desktop\\Software\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path+"?command=Register");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_search_default_case(){
        login("plopke", "TTTT");
        WebElement button = driver.findElement(By.id("searchnav"));
        button.click();
        assertEquals("Search", driver.getTitle());
        assertEquals(driver.findElements(By.tagName("tr")).size(),2);
        Boolean isErrorPresent = driver.findElements(By.id("error")).size() > 0;
        assertEquals(isErrorPresent, false);
    }

    @Test
    public void test_search_after_search_that_should_give_result(){
        login("plopke", "TTTT");
        WebElement button = driver.findElement(By.id("searchnav"));
        button.click();
        assertEquals("Search", driver.getTitle());
        fillOutField("searchfor","maria");
        driver.findElement(By.id("searchforSubmit")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(),2);
        Boolean isErrorPresent = driver.findElements(By.id("error")).size() > 0;
        assertEquals(isErrorPresent, false);
    }

    @Test
    public void test_search_after_search_that_should_give_no_result(){
        login("plopke", "TTTT");
        WebElement button = driver.findElement(By.id("searchnav"));
        button.click();
        assertEquals("Search", driver.getTitle());
        fillOutField("searchfor","ja dit gaat er dus zeker niet in staan he");
        driver.findElement(By.id("searchforSubmit")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(),0);
        Boolean isErrorPresent = driver.findElements(By.id("error")).size() > 0;
        assertEquals(isErrorPresent, true);
    }

    @Test
    public void test_search_after_search_with_blank(){
        login("plopke", "TTTT");
        WebElement button = driver.findElement(By.id("searchnav"));
        button.click();
        assertEquals("Search", driver.getTitle());
        driver.findElement(By.id("searchforSubmit")).click();
        assertEquals(driver.findElements(By.tagName("tr")).size(),2);
        Boolean isErrorPresent = driver.findElements(By.id("error")).size() > 0;
        assertEquals(isErrorPresent, false);
    }

    private void login(String userId, String password){
        fillOutField("userId", userId);
        fillOutField("password", password);

        WebElement button = driver.findElement(By.id("logIn"));
        button.click();
    }

    private void fillOutField(String name,String value) {
        WebElement field=driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

}
