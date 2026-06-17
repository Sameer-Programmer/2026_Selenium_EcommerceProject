package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import testBase.BasePage;

import java.time.Duration;

public class MyAccountPage extends BasePage {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//h2[text()='My Account']")
    WebElement msgHeading;

    @FindBy (xpath = "//a[@class='list-group-item'][text()='Logout']")
    WebElement linkLogout;

    @FindBy(xpath="//span[text()='My Account']")
    WebElement myAccount;

    @FindBy(xpath="//a[text()='Logout']")
    WebElement logout;

    @FindBy(xpath="//a[text()='Edit your account information']")
    WebElement editAccountInfo;

    public boolean isMyAccountPageExists()
    {
        try
        {
            return msgHeading.isDisplayed();
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public void clickLogout()
    {
      linkLogout.click();
    }
}
