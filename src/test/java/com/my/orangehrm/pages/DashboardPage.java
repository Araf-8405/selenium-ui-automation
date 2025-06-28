package com.my.orangehrm.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for elements on the Dashboard Page
    private By dashboardHeader = By.xpath("//h6[normalize-space()='Dashboard']");
    private By userDropdown = By.cssSelector(".oxd-userdropdown-tab");
    private By logoutLink = By.linkText("Logout");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Actions/Verifications on the Dashboard Page
    public boolean isDashboardDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dashboardHeader)).isDisplayed();
    }

    public void logout() {
        wait.until(ExpectedConditions.elementToBeClickable(userDropdown)).click();
        wait.until(ExpectedConditions.elementToBeClickable(logoutLink)).click();
    }
}