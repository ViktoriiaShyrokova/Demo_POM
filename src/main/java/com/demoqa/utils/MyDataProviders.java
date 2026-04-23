package com.demoqa.utils;

import org.junit.jupiter.params.provider.Arguments;

import java.util.List;
import java.util.stream.Stream;

public class MyDataProviders {

    static Stream<Arguments> positiveDataProvider() {
        return Stream.of(
                Arguments.of("Jack", "Sparrow", "jack@sparrow.com", "1234567890", "Male", "16.9.1987",
                        List.of("Maths","English"),new String[]{"Sports","Music"},
                        "/Users/arturvikki/Tools/photo-flower.png","NCR","Delhi","Thanks for submitting the form"),
                Arguments.of("Mary", "Smith", "mary@smith.com", "1234567891", "Female", "31.12.1988",
                        List.of("Arts","Social Studies"),new String[]{"Reading","Music"},
                        "/Users/arturvikki/Tools/photo-flower.png","Uttar Pradesh","Agra","Thanks for submitting the form"),
                Arguments.of("Sasha", "Dawson", "sasha@dawson.com", "1234567892", "Other", "1.1.1930",
                        List.of("Computer Science"),new String[]{"Reading"},
                        "/Users/arturvikki/Tools/photo-flower.png","Uttar Pradesh","Agra","Thanks for submitting the form")
        );
    }
    static Stream<Arguments> negativeDataProvider() {
        return Stream.of(
                Arguments.of("", "Sparrow", "jack@sparrow.com", "1234567890", "Male", "firstName"),
                Arguments.of("Mary", "", "mary@smith.com", "1234567891", "Female","lastName"),
                Arguments.of("Sasha", "Dawson", "sasha@dawson.com", "", "Other","phone"),
                Arguments.of("Sasha", "Dawson", "sasha@dawson.com", "00", "Other","phone"),
                Arguments.of("Jack", "Sparrow", "jack@sparrow.com", "1234567890", "", "gender")
        );
    }
}
