package ru.netology;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrderTest {

    @Test
    void shouldMakeOrderDeliveryOfCard() {
        open("http://localhost:9999");
        $("[data-test-id=city] input").setValue("Краснодар");
        $("[data-test-id=date] input").doubleClick().sendKeys(LocalDate.now().plusDays(3)
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        $("[data-test-id=name] input").setValue("Иванов-Петров Василий");
        $("[data-test-id=phone] input").setValue("+79990000000");
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).shouldBe(visible, Duration.ofMillis(15000));
        $(".notification__content").shouldHave(text("Встреча успешно забронирована на "),
                Duration.ofSeconds(15)).shouldBe(visible);

    }
}
