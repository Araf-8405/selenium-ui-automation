package com.my.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private Properties properties;
    private final String propertyFilePath = "src/test/resources/config.properties";

    public ConfigReader() {
        properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream(propertyFilePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("config.properties not found at " + propertyFilePath);
        }
    }

    public String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value != null) {
            return value;
        } else {
            throw new RuntimeException("Property '" + key + "' not specified in the config.properties file.");
        }
    }
}
