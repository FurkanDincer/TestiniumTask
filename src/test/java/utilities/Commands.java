package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static utilities.Wait.waitfor;

public class Commands {
    public static double speed = 1;

    public static void actionsSendKeys(WebElement webElement, String string, int duration, int interval) {
        Actions actions = new Actions(Driver.getDriver());
        int timer = 0;
        do {
            try {
                if (webElement.isDisplayed()) {
                    actions.moveToElement(webElement).perform();
                    waitfor(speed);
                    actions.click(webElement).sendKeys(string).perform();
                    break;
                } else
                    throw new Exception();
            } catch (Exception e) {
                waitfor(1);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            Log.info(webElement + " is not able to send keys");
            throw new NoSuchElementException();
        }
    }

    public static void waitAndClick(WebElement webElement, int duration, int interval) {
        int timer = 0;
        do {
            try {
                waitfor(speed);
                if (webElement.isDisplayed()) {
                    webElement.click();
                    break;
                } else
                    throw new Exception();
            } catch (Exception e) {
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            Log.info(webElement + " is not clickable");
            throw new NoSuchElementException();
        }
    }

    public static void clearWebElement(WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                Commands.moveToElement(webElement);
                webElement.clear();
            }
        } catch (Exception e) {
            waitfor(1);
            if (webElement.isDisplayed()) {
                Commands.moveToElement(webElement);
                webElement.clear();
            }
        }
        waitfor(2);
    }

    public static void moveToElement(WebElement webElement) {
        Actions actions = new Actions(Driver.getDriver());
        try {
            if (webElement.isDisplayed()) {
                actions.moveToElement(webElement).perform();
                waitfor(2);
            }
        } catch (Exception e) {
            waitfor(2);
            if (webElement.isDisplayed()) {
                actions.moveToElement(webElement).perform();
                waitfor(2);
            }
        }
    }
    public static void clickKey(Keys key, int duration, int interval) {
        Actions actions = new Actions(Driver.getDriver());
        int timer = 0;
        do {
            try {
                waitfor(speed);
                actions.sendKeys(key).perform();
                break;
            } catch (Exception e) {
                waitfor(interval);
                timer += interval;
            }
        } while (timer < duration);
        if (timer == duration) {
            Log.info(key + " is not clicked");
        }
        waitfor(1);
    }

    public static String getTextofaWebElement(WebElement webElement) throws Exception {
        String text = null;
        int timer = 0;
        while (true) {
            try {
                moveToElement(webElement);
                waitfor(speed);
                text = webElement.getText().trim();
                break;
            } catch (Exception e) {
                waitfor(1);
                timer++;
                if (timer == 2)
                    break;
            }
        }
        return text;
    }

    public static void writeListToFile(String filePath, List<WebElement> elements) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (WebElement element : elements) {
                String text = element.getText();
                writer.write(text);
                writer.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException("Liste dosyaya yazılamadı: " + e.getMessage());
        }
    }
}
