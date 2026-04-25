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
    @FindBy(css = "a[href='/alertsWindows']")
    WebElement alertsWindows;
    @FindBy(css = "a[href='/forms']")
    WebElement forms;
    @FindBy(css = "a[href='/elements']")
    WebElement elements;

    public SidePanel getBookStore() {
        clickWithJs(books);
        return new SidePanel(driver);
    }

    public SidePanel getAlertsFrameWindows() {
        clickWithJs(alertsWindows);
        return new SidePanel(driver);
    }

    public SidePanel getForms() {
        clickWithJs(forms);
        return new SidePanel(driver);
    }

    public SidePanel getElements() {
        clickWithJs(elements);
        return new SidePanel(driver);
    }
}
