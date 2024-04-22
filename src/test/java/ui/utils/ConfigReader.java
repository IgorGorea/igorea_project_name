package ui.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigReader {
    private final Properties properties;

    public ConfigReader() {
        properties = new Properties();
        try {
            FileInputStream inputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("There is no value for key: " + key);
        }
        return properties.getProperty(key);
    }

    public Map<String, String> getProperties(String... keys) {
        Map<String, String> propertyMap = new HashMap<>();
        for (String key : keys) {
            propertyMap.put(key, getProperty(key));
        }
        return propertyMap;
    }
}