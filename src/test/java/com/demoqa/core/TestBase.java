package com.demoqa.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

@ExtendWith(TestResultLogger.class)
public class TestBase {

    protected WebDriver driver;
    public Logger logger = LoggerFactory.getLogger(TestBase.class);



    @BeforeEach
    public void init(TestInfo testInfo) {
        String browser = System.getProperty("browser", "chrome");
        switch (browser) {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            default:
                driver = new ChromeDriver();
        }
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        logger.info("Start test_______{}", testInfo.getDisplayName());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) driver.quit();
        logger.info("Browser closed");
        logger.info("=".repeat(50));
    }
}
