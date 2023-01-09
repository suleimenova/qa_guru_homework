package com.demoqa.tests;


import com.demoqa.utils.RandomMonth;
import com.github.javafaker.Faker;

public class TestData {
    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = faker.demographic().sex(),
            number = faker.phoneNumber().subscriberNumber(10),
            day = String.valueOf(faker.number().numberBetween(10, 31)),
            month = String.valueOf(String.valueOf(RandomMonth.getRandomMonth())),
            year = String.valueOf(faker.number().numberBetween(1950, 2022)),
            subjects = "History",
            hobbies = "Reading",
            picturePath = "src/test/resources/file.jpeg",
            picture = "file.jpeg",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Noida",
            birthDay = day + " " + month + "," + year;

}




