package testBase;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
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
        DesiredCapabilities cap = new DesiredCapabilities();

        if(properties.getProperty("execution_env").equalsIgnoreCase("remote")){

            //os
            if(os.equalsIgnoreCase("windows")){
                cap.setPlatform(Platform.WIN11);
            } else if (os.equalsIgnoreCase("mac")) {
                cap.setPlatform(Platform.MAC);
            }else {
                System.out.println("No Matching found regarding OS");
                return;
            }

            //browser
            switch (browser.toLowerCase()){
                case "chrome":cap.setBrowserName("chrome");break;
                case "edge":cap.setBrowserName("MicrosoftEdge");break;
                default:
                    System.out.println("No Match found");
                    return;

            }

            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
        }


        if(properties.getProperty("execution_env").equalsIgnoreCase("local")){
            switch (browser.toLowerCase()){
                case "chrome" :  driver = new ChromeDriver(); break;
                case "edge" : driver = new EdgeDriver(); break;
                case "firefox": driver = new FirefoxDriver();break;
                default:
                    System.out.println("Invalid browser");return;
            }
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

    public String captureScreen(String tname) throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

        TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
        File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath = System.getProperty("user.dir")
                + "\\screenshots\\"
                + tname
                + "_"
                + timeStamp
                + ".png";

        File targetFile = new File(targetFilePath);

        sourceFile.renameTo(targetFile);

        return targetFilePath;
    }
}
