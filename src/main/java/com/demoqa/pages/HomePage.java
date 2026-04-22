package com.demoqa.pages;

import com.demoqa.core.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "a[href='/books']")
    WebElement books;

    public SidePanel getBookStore() {
        clickWithJs(books);
        return new SidePanel(driver);
    }

    @FindBy(css = "a[href='/alertsWindows']")
    WebElement alertsWindows;
    public SidePanel getAlertsFrameWindows() {
        clickWithJs(alertsWindows);
        return new SidePanel(driver);
    }
}
