package tests;

import org.junit.jupiter.api.Test;
import pages.AddToCartPage;
import pages.HomePage;
import base.BaseTest;
import pages.ProductDetailPage;
import utilities.Driver;

public class AddToCartTest extends BaseTest {
    HomePage homePage = new HomePage();
    ProductDetailPage productDetailPage=new ProductDetailPage();
    AddToCartPage addToCartPage=new AddToCartPage();


    @Test
    public void zaraAddToCartFlowTest() throws Exception {
        test = extent.createTest("Zara WebSite Automation", "Search Product, Add to Cart");
        test.info("Test Started");

        homePage.rejectCookies();
        //homePage.clickGirisYapButton();
        //homePage.login();
        homePage.clickHamburgerIcon();
        homePage.clickErkekSection();
        homePage.clickTumunuGorSection();

        productDetailPage.searchForSortProduct();
        productDetailPage.clearSearchBox();

        homePage.clickHamburgerIcon();
        homePage.clickErkekSection();
        homePage.clickTumunuGorSection();
        productDetailPage.searchForGomlekProduct();
        productDetailPage.selectRandomProduct();
        productDetailPage.writeToProductDetails();
        productDetailPage.clickEkleButton();

        addToCartPage.clickAlisverisSepetiniGorButton();
        addToCartPage.verifyProductPrice();
        addToCartPage.addOneMoreProduct();
        addToCartPage.deleteProducts();
        addToCartPage.verifySepetinizBosText();
        Driver.closeDriver();
    }
}
