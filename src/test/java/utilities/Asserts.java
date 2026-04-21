package utilities;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Asserts {
    public static void assertIsDisplayed(WebElement webElement, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Wait.waitfor(interval);
                Assert.assertTrue(webElement.isDisplayed());
                System.out.println("ASSERTION PASSED : " + "Expected: " + webElement + ", is displayed");
                //Commands.getScreenhotForPassedAssertion();
                break;
            } catch (Exception | AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + webElement + " is not displayed");
            Assert.fail();
        }
    }
    public static void assertEqualsIgnoreCase(String expected, String actual, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(expected.toUpperCase(), actual.toUpperCase());
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", Actual: " + actual);
                //Commands.getScreenhotForPassedAssertion();
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }
    public static void assertEqualsIgnoreCase(int expected, int actual, int duration, int interval) {
        int timer = 0;
        do {
            try {
                Assert.assertEquals(expected, actual);
                System.out.println("ASSERTION PASSED : " + "Expected: " + expected + ", Actual: " + actual);
                //Commands.getScreenhotForPassedAssertion();
                break;
            } catch (AssertionError e) {
                Wait.waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            System.out.println("ASSERTION ERROR : " + "Expected: " + expected + ", Actual: " + actual);
            Assert.fail();
        }
    }
}
