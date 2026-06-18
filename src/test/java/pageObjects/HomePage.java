package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//span[text()='My Account']")
    WebElement linkMyAccount;

    @FindBy(xpath = "//li/a[text()='Register']")
    WebElement linkRegistor;

    @FindBy(xpath = "(//li/a[text()='Login'])[1]")
    WebElement linkLogin;



    public void clickMyAccount() {
        linkMyAccount.click();
    }

    public void clicklinkRegistor() {
        linkRegistor.click();
    }
    public void clicklinkLogin() {
        linkLogin.click();
    }

}