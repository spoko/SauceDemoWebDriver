package firstLoginTest;

import baseTestCase.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class SuccessfulLoginTest extends TestUtil {

    @Test(dataProvider = "userList")
    public void successfulLogin(String username){
        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        //Explicit Wait:
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));

        WebElement productsPageTitle = driver.findElement(By.className("title"));
        wait.until(ExpectedConditions.visibilityOf(productsPageTitle));//usage of the explicit wait
        Assert.assertTrue(productsPageTitle.isDisplayed());
    }

    @DataProvider(name = "userList")
    public Object[] getUsers(){
        return new Object[] {
                "standard_user",
                "locked_out_user",
                "problem_user",
                "performance_glitch_user"
        };
    }
}