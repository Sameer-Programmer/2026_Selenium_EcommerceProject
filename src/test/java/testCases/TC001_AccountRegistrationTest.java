package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.DataGenerator;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test(groups ={"Regression","Master"})
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
