package com.smira;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class KaspiTest {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://kaspi.kz";
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
    }

    @ValueSource(strings = {"Iphone", "Apple watch"})
    //Тестовые данные: ["Iphone", "Apple watch"]
    @ParameterizedTest(name = "Поиск товара по наименованию и проверка числа результатов в Каспи магазине для запроса {0} ")
    void udemySearchCommonTest(String testData) {
        open("/shop/nur-sultan");
        $(".search-bar__input").setValue(testData);
        $("button.search-bar__submit").click();
        $$("div.ddl_product")
                .shouldHave(CollectionCondition.size(12))
                .first()
                .shouldHave(text(testData));
    }

    @CsvSource({
            "Apple MacBook, Apple MacBook Air 13 MGN63 серый ",
            "Фотоаппарат моментальной печати, Fujifilm Instax Mini 11 белый"
    })
    @ParameterizedTest()
    void udemySearchCommonTestDifferentExpectedText(String searchQuery, String expectedText) {
        open("/shop/nur-sultan");
        $(".search-bar__input").setValue(searchQuery);
        $("button.search-bar__submit").click();
        $$("div.ddl_product")
                .first()
                .shouldHave(text(expectedText));


    }



}

