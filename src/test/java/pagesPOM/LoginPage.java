package pagesPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    //Elements
    @FindBy(id = "user-name")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginBtn;

    //Constructor
    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    //Methods i.e. Actions and validations
    @Override
    public boolean isAt() {
        return loginBtn.isDisplayed();
    }

    public ProductPage login(String username, String password){
        userNameInput.click();
        userNameInput.clear();
        userNameInput.sendKeys(username);

        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new ProductPage(driver);
    }
}
