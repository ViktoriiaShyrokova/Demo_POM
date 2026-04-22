package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertsPage extends BasePage {

    public AlertsPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(id = "timerAlertButton")
    WebElement timerAlertButton;

    public AlertsPage verifyAlertWithTimer() {
        clickWithJs(timerAlertButton);
        Assertions.assertTrue(isAlertPresent(5));
        return this;
    }

    @FindBy(id = "confirmButton")
    WebElement confirmButton;

//    public AlertsPage clickOnCancelButton(String result) {
//        clickWithJs(confirmButton);
//        if (result != null && result.equalsIgnoreCase("ok"))
//            driver.switchTo().alert().accept();
//        else if (result != null && result.equalsIgnoreCase("cancel")) {
//            driver.switchTo().alert().dismiss();
//        }
//        return this;
//    }

    public AlertsPage clickOnSelectResultButton(String result) {
        clickWithJs(confirmButton);
        if (result == null) return this;
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.alertIsPresent());

        if ("cancel".equalsIgnoreCase(result)) {
            alert.dismiss();
        } else {
            alert.accept();
        }
        return this;
    }

    @FindBy(id = "confirmResult")
    WebElement confirmResult;

    public AlertsPage verifyResult(String text) {
        Assertions.assertTrue(isContainsText(confirmResult, text));
        return this;
    }

    @FindBy(id = "promtButton")
    WebElement promptButton;
    public AlertsPage clickOnPromptButton() {
        clickWithJs(promptButton);
        return this;
    }

    public AlertsPage sendMessageToAlert(String text) {
        if(text != null){
        driver.switchTo().alert().sendKeys(text);
        driver.switchTo().alert().accept();
        }
        return this;
    }

    @FindBy(id = "promptResult")
    WebElement promptResult;

    public AlertsPage verifyPromptResult(String text) {
        Assertions.assertTrue(isContainsText(promptResult, text));
        return this;
    }
}
