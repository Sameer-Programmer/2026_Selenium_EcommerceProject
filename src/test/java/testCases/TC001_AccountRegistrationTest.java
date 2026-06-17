package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import utilities.DataGenerator;

import java.time.Duration;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test
    public void verifyAccountRegistration() {

        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clicklinkRegistor();

        AccountRegistrationPage ar = new AccountRegistrationPage(driver);

        ar.setFirstName(DataGenerator.getFirstName());
        ar.setLastName(DataGenerator.getLastName());
        String provideemail = DataGenerator.getUniqueEmail();
        System.out.println(provideemail);
        ar.setEmail(provideemail);

        ar.setTelephone(DataGenerator.getPhoneNumber());

        String password = DataGenerator.getPassword();

        ar.setPassword(password);
        ar.setConfirmPassword(password);
        System.out.println(password);

        ar.clickPrivacyPolicy();
        ar.clickContinue();

        String confmsg = ar.getConfirmationMessage();
        Assert.assertEquals(confmsg,
                "Your Account Has Been Created!");
    }










}
