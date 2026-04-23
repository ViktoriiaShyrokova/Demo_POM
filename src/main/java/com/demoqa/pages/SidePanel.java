package com.demoqa.pages;

import com.demoqa.core.BasePage;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.IframesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import com.demoqa.pages.bookStore.LoginPage;
import com.demoqa.pages.forms.PracticeFormPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidePanel extends BasePage {

    public SidePanel(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[.='Login']")
    WebElement login;
    @FindBy(xpath = "//span[.='Alerts']")
    WebElement alerts;
    @FindBy(css = "a[href$='/browser-windows']")
    WebElement browserWindows;
    @FindBy(css = "a[href$='/frames']")
    WebElement frames;
    @FindBy(css = "a[href$='/automation-practice-form']")
    WebElement practiceForm;

    public LoginPage getLogin() {
        clickWithJs(login);
        return new LoginPage(driver);
    }


    public AlertsPage getAlerts() {
        clickWithJs(alerts);
        return new AlertsPage(driver);
    }


    public WindowsPage getBrowserWindows() {
        clickWithJs(browserWindows);
        return new WindowsPage(driver);
    }


    public IframesPage getFrames() {
        clickWithJs(frames);
        return new IframesPage(driver);
    }

    public PracticeFormPage getPracticeFrom() {
        clickWithJs(practiceForm);
        return new PracticeFormPage(driver);
    }
}
