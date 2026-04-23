package com.demoqa.pages.forms;

import com.demoqa.core.BasePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PracticeFormPage extends BasePage {

    public PracticeFormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "firstName")
    WebElement firstName;
    @FindBy(id = "lastName")
    WebElement lastName;
    @FindBy(id = "userEmail")
    WebElement userEmail;
    @FindBy(id = "userNumber")
    WebElement userNumber;
    //    @FindBy(css = "label[for='gender-radio-1']")
//    WebElement radioMale;
    @FindBy(xpath = "//label[contains(@for,'gender-radio')]")
    List<WebElement> radioButtons;
    @FindBy(id = "dateOfBirthInput")
    WebElement dateInput;
    @FindBy(css = ".react-datepicker__year-select")
    WebElement yearSelect;
    @FindBy(css = ".react-datepicker__month-select")
    WebElement monthSelect;
    @FindBy(css = ".react-datepicker__year-select>option[value='1990']")
    WebElement yearOption;
    @FindBy(css = ".react-datepicker__month-select>option[value='7']")
    WebElement monthOption;
    @FindBy(xpath = "//div[.='13']")
    WebElement dayOption;

    @FindBy(id = "subjectsInput")
    WebElement subjectsInput;
    @FindBy(xpath = "//label[contains(@for,'hobbies-checkbox')]")
    List<WebElement> hobbiesCheckboxes;
    @FindBy(id = "uploadPicture")
    WebElement uploadPicture;
    @FindBy(id = "react-select-3-input")
    WebElement stateInput;
    @FindBy(id = "react-select-4-input")
    WebElement cityInput;
    @FindBy(id = "submit")
    WebElement submit;
    @FindBy(id = "example-modal-sizes-title-lg")
    WebElement resultTitle;

    public PracticeFormPage enterPersonalData(String name, String surName, String email, String phone) {
        type(firstName, name);
        type(lastName, surName);
        type(userEmail, email);
        type(userNumber, phone);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        //if(gender.equals("Male")) click(radioMale);
        if (gender.equals("Male")) click(radioButtons.get(0));
        else if (gender.equals("Female")) click(radioButtons.get(1));
        else click(radioButtons.get(2));
        return this;
    }

    public PracticeFormPage typeDate(String date) {
        click(dateInput);
        String os = System.getProperty("os.name");
        System.out.println(os);

        if (os.startsWith("Mac")) dateInput.sendKeys(Keys.COMMAND, "a");
        else dateInput.sendKeys(Keys.CONTROL, "a");
        dateInput.sendKeys(date);
        dateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage selectDate(String date) {
        String[] split = date.split("\\.");
        String day = split[0];
        int month = Integer.parseInt(split[1]) - 1;
        String month1 = split[1];
        String year = split[2];
        click(dateInput);
        click(yearSelect);
        WebElement yearElement = driver.findElement(By.cssSelector(".react-datepicker__year-select>option[value='" + year + "']"));
        WebElement monthElement = driver.findElement(By.cssSelector(".react-datepicker__month-select>option[value='" + month + "']"));
        click(yearElement);
        click(monthSelect);
        click(monthElement);
        WebElement dayElement = driver.findElement(By.xpath("//div[.='" + day + "']"));
        click(dayElement);
        return this;
    }

    public PracticeFormPage addSubject(String[] subjects) {

        for (String subject : subjects) {
            if (subject != null) {
                type(subjectsInput, subject);
                subjectsInput.sendKeys(Keys.ENTER);
            }
        }
        return this;
    }

    public PracticeFormPage addSubjectList(List<String> subjects) {
        subjects.forEach(subject -> {
            if (subject != null) {
                type(subjectsInput, subject);
                subjectsInput.sendKeys(Keys.ENTER);
            }
        });
        return this;
    }

    public PracticeFormPage selectHobby(String[] hobbies) {
        for (String hobby : hobbies) {
            if (hobby.equals("Sports")) {
                click(hobbiesCheckboxes.get(0));
            }
            if (hobby.equals("Reading")) {
                click(hobbiesCheckboxes.get(1));
            }
            if (hobby.equals("Music")) {
                click(hobbiesCheckboxes.get(2));
            }

        }
        return this;
    }

    public PracticeFormPage uploadFile(String path) {
        uploadPicture.sendKeys(path);
        return this;
    }

    public PracticeFormPage enterState(String state) {
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage enterCity(String city) {
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage submit() {
        clickWithJs(submit);
        return this;
    }

    public PracticeFormPage verifySuccessRegistration(String title) {
        Assertions.assertTrue(shouldHaveText(resultTitle,title,10));
        return this;

    }
}
