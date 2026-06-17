package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testBase.BasePage;

public class HomePage extends BasePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[text()='My Account']")
    WebElement linkMyAccount;

    @FindBy(xpath = "//li/a[text()='Register']")
    WebElement linkRegistor;

    public void clickMyAccount() {
        linkMyAccount.click();
    }

    public void clicklinkRegistor() {
        linkRegistor.click();
    }

}