package utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties prop;

    static {
        try {
            FileInputStream file = new FileInputStream("Configuration.properties");
            prop = new Properties();
            prop.load(file);
        } catch (Exception e) {
            throw new RuntimeException("Config okunamadı");
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
