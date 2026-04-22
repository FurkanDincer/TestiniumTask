package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static utilities.Commands.*;

public class ProductDetailPage {
    public ProductDetailPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }
    Random random = new Random();
    @FindBy(xpath = "//a[@data-qa-id='header-search-text-link']")
    private WebElement searchBoxButton;
    @FindBy(id = "search-home-form-combo-input")
    private WebElement inputSearchField;
    @FindBy(xpath = "//a[@data-qa-action='product-click']")
    private List<WebElement> productList;
    @FindBy(xpath = "//h1[@data-qa-qualifier='product-detail-info-name']")
    private WebElement productDetailsOnProductPage;
    @FindBy(xpath = "//div[@class='product-detail-info__price']")
    private WebElement productPriceOnProductPage;
    @FindBy(xpath = "//button[@data-qa-action='add-to-cart']")
    private WebElement ekleButton;
    @FindBy(className = "size-selector-sizes-size__button")
    private List<WebElement> productSizeList;

    public void searchForSortProduct() {
        clickWebelement(searchBoxButton, 3, 1);
        String path = System.getProperty("user.dir")+"/src/test/resources/testdata/ProductList.xlsx";
        String valueSort = ExcelReader.getCellValue(path, 0, 0);
        Log.info("Value Sort: " + valueSort);
        actionsSendKeys(inputSearchField, valueSort, 3, 1);
    }

    public void searchForGomlekProduct() {
        clickWebelement(searchBoxButton, 3, 1);
        String path = System.getProperty("user.dir")+ "/src/test/resources/testdata/ProductList.xlsx";
        String valueSort = ExcelReader.getCellValue(path, 0, 1);
        Log.info("Value Sort: " + valueSort);
        actionsSendKeys(inputSearchField, valueSort, 3, 1);
        clickKey(Keys.ENTER, 3, 1);
    }

    public void clearSearchBox() {
        String text = inputSearchField.getAttribute("value");
        for (int i = 0; i < text.length(); i++) {
            inputSearchField.sendKeys(Keys.BACK_SPACE);
        }
    }

    public void clickEnter() {
        clickKey(Keys.ENTER, 3, 1);
    }

    public void selectRandomProduct() {
        int index = random.nextInt(productList.size());
        Log.info("Random Number is: " + index);
        clickWebelement(productList.get(index), 3, 1);
    }

    static String priceOnProductPage;
    String productDetails;

    public void writeToProductDetails() throws Exception {
        productDetails = getTextofaWebElement(productDetailsOnProductPage);
        Log.info("Product Details: " + productDetails);

        priceOnProductPage = getTextofaWebElement(productPriceOnProductPage);
        Log.info("Product Price: " + priceOnProductPage);

        List<WebElement> productInformation = new ArrayList<>(Arrays.asList(productDetailsOnProductPage, productPriceOnProductPage));
        String txtPath = System.getProperty("user.dir") + "/src/test/resources/testdata/Product.txt";
        writeListToFile(txtPath, productInformation);
    }

    public void clickEkleButton() throws InterruptedException {
        clickWebelement(ekleButton, 3, 1);
        List<WebElement> availableSizes = new ArrayList<>();
        for (WebElement size : productSizeList) {
            String disabledAttr = size.getAttribute("data-qa-action");
            Log.info("Attribute: "+disabledAttr);
            if (!disabledAttr.equals ("size-out-of-stock")) {
                availableSizes.add(size);
            }
        }
        WebElement selectedSize = availableSizes.get(random.nextInt(availableSizes.size()));
        clickWebelement(selectedSize,3,1);
    }

}
