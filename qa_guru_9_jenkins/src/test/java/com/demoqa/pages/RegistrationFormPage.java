package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.pages.components.CalendarComponent;
import com.demoqa.pages.components.ResultsTableComponent;
import io.qameta.allure.Step;

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

    @Step("Открыть главную страницу")
    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(TITLE_TEXT));
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");


        return this;
    }

    @Step("Заполнить поле имя")
    public RegistrationFormPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }


    public RegistrationFormPage clearFirstName() {
        firstNameInput.clear();

        return this;
    }
    @Step("Заполнить поле фамилия")
    public RegistrationFormPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }
    @Step("Ввести адрес электронной почты")
    public RegistrationFormPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }
    @Step("Выбрать пол")
    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();

        return this;
    }
    @Step("Ввести номер телефона")
    public RegistrationFormPage setNumber(String value) {
        $("#userNumber").setValue(value);

        return this;
    }
    @Step("Выбрать дату рождения")
    public RegistrationFormPage setBirthDate(String day, String month, String year) {
        $("#dateOfBirth-wrapper").click();
        calendarComponent.setDate(day, month, year);

        return this;
    }
    @Step("Выбрать предмет")
    public RegistrationFormPage setSubjects(String value) {
        $("#subjectsInput").setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать хобби")
    public RegistrationFormPage setHobbies(String value) {
        $("#hobbiesWrapper").$(byText(value)).click();

        return this;
    }@Step("Загрузить картинку")
    public RegistrationFormPage uploadPicture(String path) {
        $("#uploadPicture").uploadFile(new File(path));
        return this;
    }
    @Step("Заполнить адрес")
    public RegistrationFormPage setAddress(String address) {
        $("#currentAddress").setValue(address);

        return this;
    }
    @Step("Выбрать штат")
    public RegistrationFormPage setState(String value) {
        $("#react-select-3-input").setValue(value).pressEnter();

        return this;
    }
    @Step("Выбрать город")
    public RegistrationFormPage setCity(String value) {
        $("#react-select-4-input").setValue(value).pressEnter();

        return this;
    }
    @Step("Кликаем на кнопку загрузить")
    public RegistrationFormPage clickSubmit() {
        $("#submit").click();

        return this;
    }
    @Step("Проверяем, что открылась таблица результатов")
    public RegistrationFormPage checkResultsTableVisible() {
        resultsTableComponent.checkVisible();

        return this;
    }
    @Step("Проверяем что поле {key} заполнено верно")
    public RegistrationFormPage checkResult(String key, String value){
        resultsTableComponent.checkResult(key, value);

        return this;
    }

}


