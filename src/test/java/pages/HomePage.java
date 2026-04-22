package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.Commands;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.Log;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.time.Duration;

public class HomePage {

    public HomePage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(id = "onetrust-reject-all-handler")
    private WebElement rejectAllCookies;

    @FindBy(xpath = "//a[@class='layout-actionable layout-desktop-account-action layout-header-desktop-action-account link']")
    private WebElement loginButton;

    @FindBy(name = "username")
    private WebElement usernameTextbox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement devamEtButton;
    @FindBy(xpath = "//span[.='Şifre ile giriş yapın']")
    private WebElement sifreIleGirisYapinButton;
    @FindBy(name = "password")
    private WebElement passwordTextbox;
    @FindBy(id = "react-aria5512956832-:r18:")
    private WebElement oturumAcButton;
    @FindBy(xpath = "(//div[@class='animated-hamburger-ss26'])[2]")
    private WebElement hambergerMenuButton;
    @FindBy(xpath = "(//span[@class='layout-categories-category-name'])[2]")
    private WebElement erkekSection;
    @FindBy(xpath = "(//span[text()='TÜMÜNÜ GÖR'])[1]")
    private WebElement tumunuGorSection;

    public void rejectCookies() {
        Commands.waitAndClick(rejectAllCookies, 3, 1);
        Log.info("Tüm Çerezler Reddedildi");
    }

    public void clickGirisYapButton() {
        Commands.waitAndClick(loginButton, 3, 1);
        Log.info("Giriş Yap Butonuna Tıklandı");
    }


    public void login() throws InterruptedException {
        Commands.waitAndClick(usernameTextbox, 3, 1);
        Commands.actionsSendKeys(usernameTextbox,ConfigurationReader.get("username"),3,1);
        Commands.waitAndClick(devamEtButton, 3, 1);
        Commands.waitAndClick(sifreIleGirisYapinButton,3,1);
        Commands.actionsSendKeys(usernameTextbox,ConfigurationReader.get("password"),3,1);
        Commands.waitAndClick(oturumAcButton, 3, 1);
        Log.info("Login Olundu");
    }

    public void clickHamburgerIcon() {
        Commands.waitAndClick(hambergerMenuButton,3,1);
    }
    public void clickErkekSection(){
        Commands.waitAndClick(erkekSection,3,1);
    }
    public void clickTumunuGorSection(){
        Commands.waitAndClick(tumunuGorSection,3,1);
    }
}
