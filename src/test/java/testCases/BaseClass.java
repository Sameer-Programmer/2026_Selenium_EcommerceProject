package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class BaseClass {
    WebDriver driver;

    @BeforeClass
    public void setupDriver(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
        driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
    }

    @AfterClass
    public void tearDownDriver(){
        driver.close();
        driver.quit();
    }
}
