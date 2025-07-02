package com.my.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for elements on the Login Page
    private By usernameField = By.name("username");
    private By passwordField = By.name("password");
    private By loginButton = By.xpath("//button[normalize-space()='Login']");
    private By errorMessage = By.xpath("//div[@class='oxd-alert-content oxd-alert-content--error']");
    private By requiredFieldMessage = By.xpath("//span[contains(@class,'oxd-input-field-error-message')]");

    // Constructor to initialize WebDriver and WebDriverWait
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        // Explicit wait with a 10-second timeout
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions on the Login Page
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField)).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public String getErrorMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }
    public String getRequiredFieldMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(requiredFieldMessage)).getText();
    }
  // Combined action for logging in, returns the next page object
    public DashboardPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
        return new DashboardPage(driver);
    }
     // Overloaded login method for scenarios where fields might be intentionally left blank
    public void loginWithoutNavigation(String username, String password) {
        if (username != null) {
            enterUsername(username);
        }
        if (password != null) {
            enterPassword(password);
        }
        clickLoginButton();
    }
}