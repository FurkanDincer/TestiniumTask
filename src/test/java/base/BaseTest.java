package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utilities.ConfigurationReader;
import utilities.Driver;
import utilities.Log;

import java.io.File;
import java.util.Locale;

public class BaseTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;
    protected WebDriver driver;

    @BeforeAll
    public static void setupReport() {
        Locale.setDefault(Locale.ENGLISH);
        String path = System.getProperty("user.dir") + File.separator + "test-output" + File.separator + "ExtentReport.html";

        File folder = new File(System.getProperty("user.dir") + File.separator + "test-output");
        if (!folder.exists()) {
            folder.mkdirs();
        }

        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        extent = new ExtentReports();
        extent.attachReporter(spark);

        System.out.println("Rapor yolu: " + path); // Konsoldan yolu kontrol et
    }

    @BeforeEach
    public void setUp(TestInfo testInfo) { // TestInfo ekledik
        // Her test başladığında raporda bir kayıt açar
        test = extent.createTest(testInfo.getDisplayName());
        driver = Driver.getDriver();
        driver.get(ConfigurationReader.get("url"));
    }


    @AfterEach
    public void tearDown(TestInfo testInfo) {
        test.info("Driver is closing.");
        Driver.closeDriver();
    }

    @AfterAll
    public static void tearDownReport() {
        if (extent != null) {
            extent.flush();
            Log.info("Report Created: test-output/ExtentReport.html");
        }
    }
}
