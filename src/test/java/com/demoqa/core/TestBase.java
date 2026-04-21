package com.demoqa.core;

import jdk.jfr.Enabled;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TestBase {

  protected WebDriver driver;

    @BeforeEach
    public void init(){
        driver = new ChromeDriver();
        driver.get("https://demoqa.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    @AfterEach
    @Disabled("Tests are not finished yet")
    public void tearDown(){
        if(driver != null) driver.quit();
    }
}
