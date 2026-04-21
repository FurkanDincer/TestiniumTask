package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import utilities.ConfigurationReader;

import java.util.Locale;

public class BaseApiTest {
    protected static ExtentReports extent;
    protected static ExtentTest test;

    protected static String apiKey;
    protected static String token;

    @BeforeAll
    public static void apiSetup() {
        Locale.setDefault(Locale.ENGLISH);

        if (extent == null) { // Eğer rapor henüz başlatılmamışsa başlat
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ApiReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("Tester", "QA Automation Engineer");
        }
        RestAssured.baseURI = "https://api.trello.com/1";
        apiKey = ConfigurationReader.get("trello_key");
        token = ConfigurationReader.get("trello_token");
    }

    @AfterAll
    public static void tearDownReport() {
        // Raporu fiziksel olarak dosyaya yazdırır
        if (extent != null) {
            extent.flush();
        }
    }
}
