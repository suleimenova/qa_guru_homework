package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsTableComponent;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    private final CalendarComponent calendarComponent = new CalendarComponent();
    private final ResultsTableComponent resultsTableComponent = new ResultsTableComponent();
    private final static String TITLE_TEXT = "Student Registration Form";

    private SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail");

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");


        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage clearFirstName() {
        firstNameInput.clear();

        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }

    public RegistrationFormPage setNumber(String value) {
        $("#userNumber").setValue(value);

        return this;
    }

    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirth-wrapper").click();
        calendarComponent.setDate(day, month, year);

        return this;
    }
    public RegistrationFormPage setSubjects(String value) {
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setHobbies(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }
    public RegistrationFormPage uploadPicture(String path) {
        $("#uploadPicture").uploadFile(new File(path));
        return this;
    }

    public RegistrationFormPage setAddress(String address) {
        $("#currentAddress").setValue(address);

        return this;
    }

    public RegistrationFormPage setState(String value) {
        $("#react-select-3-input").setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage setCity(String value) {
        $("#react-select-4-input").setValue(value).pressEnter();

        return this;
    }

    public RegistrationFormPage clickSubmit() {
        $("#submit").click();

        return this;
    }

    public RegistrationFormPage checkResultsTableVisible() {
        resultsTableComponent.checkVisible();

        return this;
    }
    public RegistrationFormPage checkResult(String key, String value){
        resultsTableComponent.checkResult(key, value);

        return this;
    }

}


