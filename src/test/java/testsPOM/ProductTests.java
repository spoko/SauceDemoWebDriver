package testsPOM;

import baseTestCase.TestUtil;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pagesPOM.LoginPage;
import pagesPOM.ProductPage;

public class ProductTests extends TestUtil {
    @Test
    public void addItemToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        productPage.addItemToTheCart("bike-light");
        Assert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve added just one item");
    }

    @Test
    public void addRemoveItemsToTheCart(){
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = loginPage.login("standard_user", "secret_sauce");

        SoftAssert softAssert = new SoftAssert();

        productPage.addItemToTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve added just one item");

        productPage.addItemToTheCart("backpack");
        softAssert.assertEquals(productPage.getItemsInTheCart(), 2, "Since we`ve added one more item");

        productPage.removeItemFromTheCart("bike-light");
        softAssert.assertEquals(productPage.getItemsInTheCart(), 1, "Since we`ve removed one item");

        softAssert.assertAll();
    }
}
