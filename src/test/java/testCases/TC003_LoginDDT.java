package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass {
    @Test(dataProviderClass = DataProviders.class,
            dataProvider= "LoginData",groups = "DataDriven")
    public void verify_loginDDT(String email, String password,String expetResult){


    HomePage homePage = new HomePage(driver);
    homePage.clickMyAccount();
    homePage.clicklinkLogin();

    LoginPage loginPage = new LoginPage(driver);
    loginPage.setEmail(email);
    loginPage.setPassword(password);
    loginPage.clickLogin();

    MyAccountPage myAccountPage = new MyAccountPage(driver);
    boolean status = myAccountPage.isMyAccountPageExists();


    if(expetResult.equalsIgnoreCase("Valid"))
    {
        if(status==true){
            myAccountPage.clickLogout();
            Assert.assertTrue(status);
        }else{
            Assert.fail();
        }

    } else if (expetResult.equalsIgnoreCase("Invalid")) {

            if(status==true){
                myAccountPage.clickLogout();
                Assert.assertTrue(false);

            }
            else {
                Assert.assertTrue(true);
            }
        }








    }

}
