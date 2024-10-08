package pagesPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage{
    private final static String BASE_PRODUCT_ID = "add-to-cart-sauce-labs-";
    private final static String BASE_REMOVE_PRODUCT_ID = "remove-sauce-labs-";

    @FindBy(xpath = "//span[text()='Products']")
    WebElement productsPageTitle;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerMenuBtn;

    @FindBy(className = "shopping_cart_badge")
    WebElement shoppingCartBadge;

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public boolean isAt() {
        return productsPageTitle.isDisplayed() && burgerMenuBtn.isDisplayed();
    }

    public void addItemToTheCart(String itemName){
        WebElement itemTobeAdded = driver.findElement(By.id(BASE_PRODUCT_ID + itemName));
        itemTobeAdded.click();
    }

    public void removeItemFromTheCart(String itemName){
        WebElement itemTobeRemoved = driver.findElement(By.id(BASE_REMOVE_PRODUCT_ID + itemName));
        itemTobeRemoved.click();
    }

    public int getItemsInTheCart(){
        return Integer.parseInt(shoppingCartBadge.getText());
    }
}
