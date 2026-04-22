package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.alertsFrameWindows.AlertsPage;
import com.demoqa.pages.alertsFrameWindows.IframesPage;
import com.demoqa.pages.alertsFrameWindows.WindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class AlertsFrameWindowsTests extends TestBase {

    SidePanel sidePanel;
    AlertsPage alerts;
    WindowsPage windowsPage;
    IframesPage iframes;
    @BeforeEach
    public void precondition(){
        new HomePage(driver).getAlertsFrameWindows();
        sidePanel = new SidePanel(driver);
        alerts = new AlertsPage(driver);
        iframes = new IframesPage(driver);
    }

    @Test
    public void waitAlertTest(){
        sidePanel.getAlerts();
        alerts.verifyAlertWithTimer();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cancel", "Ok"})
    public void alertWithSelectResultTest(String select){
        sidePanel.getAlerts();
        alerts.clickOnSelectResultButton(select)
                .verifyResult(select);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello"})
    public void sendMessageToAlertTest(String text){
        sidePanel.getAlerts();
        alerts.clickOnPromptButton()
                .sendMessageToAlert(text)
                .verifyPromptResult(text);
    }

    @Test
    public void newTabTest(){
        windowsPage = sidePanel.getBrowserWindows();

        windowsPage.clickNewTabButton()
                .switchToNewTab(1)
                .verifyNewTabTitle("This is a sample page");
    }

    @Test
    public void iframeByIdTest(){
        sidePanel.getFrames();
        iframes.switchIframeById()
                .verifyIframeByTitle("This is a sample page");

    }
}
