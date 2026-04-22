package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.*;

import java.util.List;

import static utilities.Asserts.assertEqualsIgnoreCase;

public class AddToCartPage {

    ProductDetailPage productDetailPage = new ProductDetailPage();

    public AddToCartPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//button[@data-qa-action='nav-to-cart']")
    private WebElement alisverisSepetiniGorButton;
    @FindBy(xpath = "//div[@class='shop-cart-item-pricing__current']//span[@class='money-amount__main']")
    private WebElement productPriceOnAddToCartPage;

    @FindBy(xpath = "//span[@class='bag-with-count__count-container bag-with-count__count--active']")
    private WebElement numberOfProductOnCartPage;

    @FindBy(xpath = "//div[@class='zds-empty-state__title']//span")
    private WebElement sepetinizBosAlert;

    @FindBy(className = "shop-cart-item-actions__dismiss")
    private List<WebElement> deleteButtonList;


    public void clickAlisverisSepetiniGorButton() {
        Commands.waitAndClick(alisverisSepetiniGorButton, 3, 1);
    }

    public void verifyProductPrice() throws Exception {
        String expectedProductPrice = Commands.getTextofaWebElement(productPriceOnAddToCartPage);
        Log.info("Product Price on Add To Cart Page: " + expectedProductPrice);
        assertEqualsIgnoreCase(expectedProductPrice, ProductDetailPage.priceOnProductPage, 3, 1);
    }

    public void addOneMoreProduct() throws InterruptedException {
        Driver.getDriver().navigate().back();
        productDetailPage.clickEkleButton();
        clickAlisverisSepetiniGorButton();
    }

    public void verifyProductNumber() throws Exception {
        String actualNumberOfProduct=Commands.getTextofaWebElement(numberOfProductOnCartPage);
        Log.info("Number of Product: "+actualNumberOfProduct);
        assertEqualsIgnoreCase("2",actualNumberOfProduct,3,1);
    }

    public void deleteProducts(){
        for (int i = 0; i < deleteButtonList.size(); i++) {
            Commands.waitAndClick(deleteButtonList.get(i),3,1);
        }
    }

    public void verifySepetinizBosText(){
        Asserts.assertIsDisplayed(sepetinizBosAlert,3,1);
    }
}
