package com.demoqa.core;

import org.jspecify.annotations.NonNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    public static JavascriptExecutor js;
    //public WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }

    public void click(WebElement element) {
        element.click();
    }

    public void type(WebElement element, String text) {
        if (text != null) {
            element.click();
            element.clear();
            element.sendKeys(text);
        }
    }

    public void scrollWithJs(int x, int y) {
        js.executeScript("window.scrollBy(" + x + "," + y + ")");
    }

    public void clickWithJs(WebElement element) {
        getWait(5).until(ExpectedConditions.visibilityOf(element));
        ((JavascriptExecutor) driver)
                .executeScript(
                        "arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);
    }

    public void typeWithJs(WebElement element, String text, int x, int y) {
        scrollWithJs(x, y);
        type(element, text);
    }

    public boolean isAlertPresent(int time) {
        try {
            Alert alert = getWait(time)
                    .until(ExpectedConditions.alertIsPresent());
            alert.accept();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebDriverWait getWait(int time) {
        return new WebDriverWait(driver, Duration.ofSeconds(time));
    }

    public boolean isContainsText(WebElement webElement, String text) {
        return webElement.getText().contains(text);
    }

    public boolean shouldHaveText(WebElement element, String text, int time){
        return getWait(time).until(ExpectedConditions.textToBePresentInElement(element,text));
    }
}
