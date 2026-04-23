package com.demoqa.tests;

import com.demoqa.core.TestBase;
import com.demoqa.pages.HomePage;
import com.demoqa.pages.SidePanel;
import com.demoqa.pages.forms.PracticeFormPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

public class PracticeFormTest extends TestBase {

    PracticeFormPage formPage;

    @BeforeEach
    public void precondition() {
        new HomePage(driver).getForms();
        new SidePanel(driver).getPracticeFrom();
        formPage = new PracticeFormPage(driver);
    }

    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                Arguments.of("Jack", "Sparrow", "jack@sparrow.com", "1234567890", "Male", "16 Aug 1987",
                        List.of("Maths","English"),new String[]{"Sports","Music"},
                        "/Users/arturvikki/Tools/photo-flower.png","NCR","Delhi","Thanks for submitting the form")
        );
    }

    @ParameterizedTest
    @MethodSource("positiveDataProvider")
    public void createAccountPositiveTest1(String firstName, String lastName,
                                           String email, String phone,
                                           String gender, String birthDate,
                                           List<String> subjects, String[] hobbies,
                                           String path, String state,
                                           String city,String verify) {
formPage.enterPersonalData(firstName,lastName,email,phone)
        .selectGender(gender)
        .typeDate(birthDate)
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
    public void createAccountPositiveTest() {
        formPage.enterPersonalData("Jon", "Morgan", "morgan@gmail.com", "1234567890")
                .selectGender("Male")
//                .typeDate("12 Aug 1997")
                .selectDate("16.8.1987")
//                .addSubject(new String[]{"Maths","English"})
                .addSubjectList(List.of("Maths","English"))
                .selectHobby(new String[]{"Sports","Music"})
                .uploadFile("/Users/arturvikki/Tools/photo-flower.png")
                .enterState("NCR")
                .enterCity("Delhi")
                .submit()
                .verifySuccessRegistration("Thanks for submitting the form")
        ;
    }
}
