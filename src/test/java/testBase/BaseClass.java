package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseClass {
    public WebDriver driver;
    public Properties properties;

    @Parameters({"os","browser"})
    @BeforeClass(groups={"Sanity","Regression","Master","DataDriven"})
    public void setupDriver(String os,String browser) throws IOException {

        //Loading config.propertiesFile
        FileReader fileReader = new FileReader("./src//test//resouces//config.properties");
        properties = new Properties();
        properties.load(fileReader);


        switch (browser.toLowerCase()){
            case "chrome" :  driver = new ChromeDriver(); break;
            case "edge" : driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver();break;
            default:
                System.out.println("Invalid browser");return;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.manage().deleteAllCookies();
        //driver.get("https://tutorialsninja.com/demo/index.php?route=account/register");
        driver.get(properties.getProperty("appurl")); //reading from properties file
    }

    @AfterClass(groups={"Sanity","Regression","Master"})
    public void tearDownDriver(){
        driver.close();
        driver.quit();
    }
}
