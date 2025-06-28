package com.my.orangehrm.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.my.orangehrm.pages.DashboardPage;
import com.my.orangehrm.pages.LoginPage;

public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage(driver);
        // Using credentials from config.properties
        DashboardPage dashboardPage = loginPage.login(
                configReader.getProperty("username"),
                configReader.getProperty("password")
        );
        // Assertion to verify successful login
        Assert.assertTrue(dashboardPage.isDashboardDisplayed(), "Dashboard should be displayed after successful login.");
        dashboardPage.logout(); // Logout after test to clean up
    }

    @Test(priority = 2, description = "Verify error message for invalid credentials")
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(
                "invalidUser", // Invalid username
                "wrongPass"    // Invalid password
        );
        String expectedErrorMessage = "Invalid credentials"; // Ensure this matches the actual error message on the UI
        String actualErrorMessage = loginPage.getErrorMessage();
        // Assertion to verify the error message
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error message should match for invalid login attempt.");
    }
    @Test(priority = 3, description = "Verify error message when username is empty")
    public void testEmptyUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutNavigation("", configReader.getProperty("password")); // Pass empty username
        String expectedRequiredMessage = "Required"; // Adjust this based on the actual message on UI
        String actualRequiredMessage = loginPage.getRequiredFieldMessage();
        Assert.assertEquals(actualRequiredMessage, expectedRequiredMessage, "Required message should be displayed for empty username.");
    }

    @Test(priority = 4, description = "Verify error message when password is empty")
    public void testEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutNavigation(configReader.getProperty("username"), ""); // Pass empty password
        String expectedRequiredMessage = "Required"; // Adjust this based on the actual message on UI
        String actualRequiredMessage = loginPage.getRequiredFieldMessage();
        Assert.assertEquals(actualRequiredMessage, expectedRequiredMessage, "Required message should be displayed for empty password.");
    }

    @Test(priority = 5, description = "Verify error message when both username and password are empty")
    public void testEmptyUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithoutNavigation("", ""); // Pass both empty
        String expectedRequiredMessage = "Required"; // Adjust this based on the actual message on UI
        // Assuming the same 'Required' message appears for both fields, or the first one encountered.
        // You might need to adjust locators if distinct messages appear for each field.
        String actualRequiredMessage = loginPage.getRequiredFieldMessage();
        Assert.assertEquals(actualRequiredMessage, expectedRequiredMessage, "Required message should be displayed for empty username and password.");
    }
}
