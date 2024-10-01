package baseTestCase;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestUtil {
    public WebDriver driver;
    private String url, browser;
    private int implicitWait;

    @BeforeMethod
    public void setupDriverAndOpenTestUrl(){
        readConfig("src/test/resources/config.properties");
        setUpDriver();
        //driver.manage().timeouts().implicitlyWait(Duration.from(Duration.ofSeconds(implicitWait)));//Implicit wait in general shall not be used
        driver.get(url);
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    private void readConfig(String configPath){
        try{
            FileInputStream fileInputStream = new FileInputStream(configPath);
            Properties properties = new Properties();
            properties.load(fileInputStream);

            url = properties.getProperty("testUrl");
            browser = properties.getProperty("browser");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    private void setUpDriver(){
        switch (browser){
            case "firefox":
                driver = setupFirefoxDriver();
                break;
            case "edge":
                driver = setupEdgeDriver();
                break;
            default:
                driver = setupChromeDriver();
        }
    }

    private WebDriver setupFirefoxDriver(){
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }

    private WebDriver setupEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        return new EdgeDriver();
    }

    private WebDriver setupChromeDriver(){
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }
}
