package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.io.File;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Driver {
    private static WebDriver driver;

    private Driver() {}

    public static WebDriver getDriver() {
        String browser = ConfigurationReader.get("browser");
        if (driver == null) {

            if ("chrome".equals(browser)){
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();

                options.setExperimentalOption("excludeSwitches",
                        Arrays.asList("enable-automation"));

                options.setExperimentalOption("useAutomationExtension", false);

                options.addArguments("--disable-blink-features=AutomationControlled");
                options.addArguments("--start-maximized");

                driver = new ChromeDriver(options);

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

            } else if ("edge".equals(browser)) {
                WebDriverManager.edgedriver().setup();
                EdgeOptions options = new EdgeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("download.prompt_for_download=false");
                //options.setExperimentalOption("prefs", prefs);
                driver = new EdgeDriver(options);
            }

        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
