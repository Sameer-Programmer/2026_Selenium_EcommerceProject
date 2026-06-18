package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups ={"Sanity","Master"} )
    public void verifyLogin(){
        HomePage homePage = new HomePage(driver);
        homePage.clickMyAccount();
        homePage.clicklinkLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.setEmail(properties.getProperty("email"));
        loginPage.setPassword(properties.getProperty("password"));
        loginPage.clickLogin();

        MyAccountPage myAccountPage = new MyAccountPage(driver);
       boolean status =  myAccountPage.isMyAccountPageExists();
       myAccountPage.clickLogout();
        Assert.assertEquals(status,true,"TestFailed-Login");


    }




}
