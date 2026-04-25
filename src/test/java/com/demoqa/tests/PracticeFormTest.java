package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.forms.PracticeFormPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

public class PracticeFormTest extends TestBase {

    PracticeFormPage formPage;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).getForms();
        new SidePanel(driver).getPracticeFrom();
        formPage = new PracticeFormPage(driver);
    }

    @ParameterizedTest
    @MethodSource({"com.demoqa.utils.MyDataProviders#positiveDataProvider"})
    public void createAccountPositiveMethodSourceTest(String firstName, String lastName,
                                                      String email, String phone,
                                                      String gender, String birthDate,
                                                      List<String> subjects, String[] hobbies,
                                                      String path, String state,
                                                      String city, String verify) {
        formPage.enterPersonalData(firstName, lastName, email, phone)
                .selectGender(gender)
                .selectDate(birthDate)
                .addSubjectList(subjects)
                .selectHobby(hobbies)
                .uploadFile(path)
                .enterState(state)
                .enterCity(city)
                .submit()
                .verifySuccessRegistration(verify)
        ;
    }

    @Test
    @Tag("smoke")
    public void createAccountPositiveTest() {
        formPage.enterPersonalData("Jon", "Morgan", "morgan@gmail.com", "1234567890")
                .selectGender("Male")
                .typeDate("12 Aug 1997")
                .addSubject(new String[]{"Maths", "English"})
                .selectHobby(new String[]{"Sports", "Music"})
                .uploadFile("/Users/arturvikki/Tools/photo-flower.png")
                .enterState("NCR")
                .enterCity("Delhi")
                .submit()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }

    @ParameterizedTest
    @MethodSource({"com.demoqa.utils.MyDataProviders#negativeDataProvider"})
    public void createAccountNegativeTest(String firstName, String lastName,
                                          String email, String phone,
                                          String gender, String field) {
        formPage.enterPersonalData(firstName, lastName, email, phone)
                .selectGender(gender)
                .submit();
        Assertions.assertFalse(formPage.verifyValidation(field));
    }
}
