package com.demoqa.core;

import com.demoqa.utils.ScreenshotUtil;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TestResultLogger implements TestWatcher {

    private static final Logger logger =
            LoggerFactory.getLogger(TestResultLogger.class);

    @Override
    public void testSuccessful(ExtensionContext context) {
        logger.info("TEST PASSED: {}", context.getDisplayName());
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        logger.error("TEST FAILED: {}", context.getDisplayName());
        logger.error("Reason: {}", cause.getMessage());

        Object testInstance = context.getRequiredTestInstance();

        if (testInstance instanceof TestBase) {
            TestBase testBase = (TestBase) testInstance;

            String screenshotPath =
                    ScreenshotUtil.take(testBase.driver);

            logger.error("Screenshot saved: {}", screenshotPath);
        }

        logger.info("=".repeat(50));
    }
}