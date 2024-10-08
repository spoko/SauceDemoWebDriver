package testsPOM;

import baseTestCase.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import pagesPOM.LoginPage;
import pagesPOM.ProductPage;

public class LoginTests extends TestUtil {

    @Test
    public void successfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(productPage.isAt());
    }

    @Test
    public void unSuccessfulLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_userd", "secret_sauce");

        Assert.assertTrue(loginPage.isAt());
    }
}
