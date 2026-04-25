package com.demoqa.core;

import com.google.common.io.Files;
import org.assertj.core.api.SoftAssertions;
import org.jspecify.annotations.NonNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    public static JavascriptExecutor js;
    public static SoftAssertions softly;
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        softly = new SoftAssertions();

        logger.info("Page initialized: {}", this.getClass().getSimpleName());
    }

    public void click(WebElement element) {
        element.click();
        logger.info("Element clicked");
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.click();
            element.clear();
            element.sendKeys(text);
            logger.info("Text entered: {}", text);
        }
    }

    public void scrollWithJs(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
        logger.info("Page scrolled by x={}, y={}", x, y);
    }

    public void clickWithJs(WebElement element) {
        getWait(5).until(ExpectedConditions.visibilityOf(element));

        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        logger.info("Element clicked with JS");
    }

    public void typeWithJs(WebElement element, String text, int x, int y) {
        scrollWithJs(x, y);
        type(element, text);
        logger.info("Text entered with JS: {}", text);
    }

    public boolean isAlertPresent(int time) {
        try {
            Alert alert = getWait(time)
                    .until(ExpectedConditions.alertIsPresent());

            logger.info("Alert is present");
            alert.accept();
            logger.info("Alert accepted");

            return true;

        } catch (TimeoutException e) {
            logger.warn("Alert not found within {} seconds", time);
            return false;
        }
    }

    public WebDriverWait getWait(int time) {
        logger.info("Explicit wait created for {} seconds", time);
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public boolean isContainsText(WebElement webElement, String text) {
        boolean result = webElement.getText().contains(text);
        logger.info("Text contains check: '{}' -> {}", text, result);
        return result;
    }

    public boolean shouldHaveText(WebElement element, String text, int time) {
        boolean result = getWait(time)
                .until(ExpectedConditions.textToBePresentInElement(element, text));

        logger.info("Wait for text '{}' result: {}", text, result);
        return result;
    }

    public boolean isFieldInFormValid(WebElement element) {
        boolean result = (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return arguments[0].checkValidity();", element);

        logger.info("Field validation result: {}", result);
        return result;
    }

    public void verifyLink(String url) {
        try {
            logger.info("Checking link: {}", url);

            URL linkUrl = new URL(url);
            HttpURLConnection connection =
                    (HttpURLConnection) linkUrl.openConnection();

            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(5000);
            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode >= 400) {
                logger.error("BROKEN LINK: {} -> {}", url, connection.getResponseMessage());
                softly.fail("BROKEN: " + url + " --> " + connection.getResponseMessage());
            } else {
                logger.info("Valid link: {} -> {}", url, responseCode);
                softly.assertThat(responseCode).isLessThan(400);
            }

            connection.disconnect();

        } catch (IOException e) {
            logger.error("BROKEN LINK: {}", url);
            softly.fail("BROKEN: " + url);
        }
    }

    public void verifyDisplayedImg(WebElement img) {
        boolean imageDisplayed = (Boolean) js.executeScript(
                "return (typeof arguments[0].naturalWidth!=undefined && arguments[0].naturalWidth>0);",
                img
        );

        if (!imageDisplayed) {
            logger.error("BROKEN IMAGE: {}", img);
            softly.fail("BROKEN img: -> " + img);
        } else {
            logger.info("Image displayed successfully");
            softly.assertThat("ok");
        }
    }
}