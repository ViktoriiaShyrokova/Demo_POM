package com.demoqa.pages.alertsFrameWindows;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class IframesPage extends BasePage {

    public IframesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "frame1")
    WebElement frame1;

    @FindBy(tagName = "h1")
    WebElement title;

    @FindBy(tagName = "iframe")
    List<WebElement> iframes;
    public IframesPage switchIframeById() {
        //System.out.println(iframes.size());
        driver.switchTo().frame(frame1);

        return this;
    }

    public IframesPage verifyIframeByTitle(String text) {
        Assertions.assertTrue(isContainsText(title,text));
        return this;
    }
}
