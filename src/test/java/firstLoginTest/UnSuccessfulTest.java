package firstLoginTest;

import baseTestCase.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvRecurse;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class UnSuccessfulTest extends TestUtil {
    //public WebDriver driver;

    @Test(dataProvider = "wrongUsers")
    public void unSuccessfulLogin(String username, String password){
        //driver.get("https://www.saucedemo.com/");

        WebElement usernameInput = driver.findElement(By.id("user-name"));
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys(username);

        WebElement passwordInput = driver.findElement(By.cssSelector("#password"));
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement loginBtn = driver.findElement(By.name("login-button"));
        loginBtn.click();

        WebElement errorMsg = driver.findElement(By.xpath(".//*[text()='Epic sadface: Username and password do not match any user in this service']"));
        Assert.assertTrue(errorMsg.isDisplayed());
    }

//    @BeforeMethod
//    public void setupChromeDriver(){
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//    }

//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    @DataProvider(name = "wrongUsers")
    public Object[][] readWrongUsers(){
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongUsers.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvResult = new Object[csvData.size()][2];

            for (int i = 0; i < csvData.size(); i++){
                csvResult[i] = csvData.get(i);
            }
            return csvResult;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return null;
        }catch (CsvException e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
