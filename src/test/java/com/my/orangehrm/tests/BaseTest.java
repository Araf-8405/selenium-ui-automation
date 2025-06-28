package com.my.orangehrm.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import com.my.orangehrm.utilities.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setup() {
        configReader = new ConfigReader();
        String browser = configReader.getProperty("browser").toLowerCase(); // Get browser from config
        String url = configReader.getProperty("base.url"); // Get URL from config

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup(); // Automatically downloads and sets up ChromeDriver
                ChromeOptions chromeOptions = new ChromeOptions();
                // Add any chrome options if needed
                chromeOptions.addArguments("--start-maximized"); // Start Chrome maximized
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup(); // Automatically downloads and sets up FirefoxDriver
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                // Add any firefox options if needed
                driver = new FirefoxDriver(firefoxOptions);
                break;
            // Add more browsers as needed (e.g., Edge, Safari)
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize(); // Maximize browser window
        driver.get(url); // Navigate to the application URL
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close the browser
        }
    }
}
