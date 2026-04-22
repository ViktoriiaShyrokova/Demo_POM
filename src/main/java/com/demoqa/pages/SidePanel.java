package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.IframesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Login']")
    WebElement login;

    public LoginPage getLogin() {
        clickWithJs(login);
        return new LoginPage(driver);
    }

    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;

    public AlertsPage getAlerts() {
        clickWithJs(alerts);
        return new AlertsPage(driver);
    }

    @FindBy(css = "a[href$='/browser-windows']")
    WebElement browserWindows;

    public WindowsPage getBrowserWindows() {
        clickWithJs(browserWindows);
        return new WindowsPage(driver);
    }

    @FindBy(css = "a[href$='/frames']")
    WebElement frames;

    public IframesPage getFrames() {
        clickWithJs(frames);
        return new IframesPage(driver);
    }
}
