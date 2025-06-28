# selenium-ui-automation
This project offers an automated test suite for the OrangeHRM web application, utilizing Selenium WebDriver, TestNG, and the Page Object Model (POM). It focuses on validating user login and demonstrates essential test automation practices.
Table of Contents
Overview

Setup

Running Tests

Features

Overview
This framework validates OrangeHRM login functionality through automated tests. It is designed with maintainability and scalability in mind, serving as a foundational example for building robust Selenium test suites for web applications.

Setup
Prerequisites: JDK 11+, Maven 3.6.0+, and an IDE.

Project Acquisition: Obtain the orangehrm-automation project (e.g., clone from Git or create manually).

IDE Import: Open the project in your IDE as a Maven project.

Build: Run mvn clean install in the project root to download dependencies.

Configuration: Adjust src/test/resources/config.properties for browser, URL, and credentials.

Running Tests
IDE (TestNG): Right-click LoginTests.java and select "Run 'LoginTests'".

Maven CLI: Run mvn test in the project root. Use mvn -Dtest=LoginTests test for a specific class.

(Optional) Suite: Right-click testng.xml and "Run 'OrangeHRM Test Suite'".

Features
Page Object Model (POM): Improved UI/test logic separation.

TestNG: Robust test organization and execution.

WebDriverManager: Automatic browser driver setup.

Explicit Waits: Enhanced test reliability.

External Configuration: Flexible test data.

BaseTest Class: Centralized test setup/teardown.
