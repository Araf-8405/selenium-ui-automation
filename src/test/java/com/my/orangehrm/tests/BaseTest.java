package com.my.orangehrm.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.lang.reflect.Method;

import com.my.orangehrm.utilities.ConfigReader;
import com.my.orangehrm.utilities.ExtentReportManager;

public class BaseTest {
    protected WebDriver driver;
    protected ConfigReader configReader;
    protected ExtentTest test; // Extent test reference

    @BeforeSuite
    public void setupSuite() {
        ExtentReportManager.getInstance(); // Initialize the report once per suite
    }

    @BeforeMethod
    public void setup(Method method) {
        configReader = new ConfigReader();
        String browser = configReader.getProperty("browser").toLowerCase();
        String url = configReader.getProperty("base.url");

        // Start a new test for each method
        test = ExtentReportManager.getInstance().createTest(method.getName());

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        driver.get(url);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        ExtentReportManager.getInstance().flush(); // Write result to report

        if (driver != null) {
            driver.quit();
        }
    }
}