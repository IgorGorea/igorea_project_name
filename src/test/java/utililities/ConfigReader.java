package utililities;

import cfg.BrowserDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;
    protected static final Logger logger = LoggerFactory.getLogger(BrowserDriver.class);

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            logger.error("The valid input was not provided from config.properties", e);        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("There is no value for key: " + key);
        }
        return properties.getProperty(key);
    }

}