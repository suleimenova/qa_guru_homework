package com.smira;

import com.codeborne.selenide.CollectionCondition;
import com.smira.data.Locale;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class ChocotravelTest {

    static Stream<Arguments> selenideSiteButtonsTextDataProvider() {
        return Stream.of(
                Arguments.of(Locale.kz, List.of("Ұшақ билеттері", "Т/Ж билеттері", "Қонақ үйлер", "Авто", "Визалар", "Корпоративтік клиенттерге", "Туры")),
                Arguments.of(Locale.en, List.of("Flights", "Railway tickets", "Hotels", "Car Rental", "Visas", "For corporate clients", "Туры"))

        );
    }

    @MethodSource("selenideSiteButtonsTextDataProvider")
    @ParameterizedTest
    void selenideSiteButtonsText(Locale locale, List<String> buttonsTexts) {
        open("https://www.chocotravel.com");
        $("div.lang-switcher__item--active").click();
        $$("div.lang-switcher__items div").findBy(attribute("data-lang", locale.name())).click();
        $$(".menu__main a span").shouldHave(CollectionCondition.texts(buttonsTexts));
    }



    @EnumSource
    @ParameterizedTest
    void checkLocaleTest (Locale locale){
        open("https://www.chocotravel.com");
        $("div.lang-switcher__item--active").click();
        $$("div.lang-switcher__items div").findBy(attribute("data-lang", locale.name())).shouldBe(visible);

    }
}
