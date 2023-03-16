package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishPage {
    private final SelenideElement element = $("h1");

    public ReplenishPage() {
        element.shouldBe(Condition.visible);
    }

    public void fillData(String cardNumber, String replenishSum) {
        $("[data-test-id=\"amount\"] input").setValue(replenishSum);
        $("[data-test-id=\"from\"] input").setValue(cardNumber);
    }

    public void replenishCard() {
        $("[data-test-id=\"action-transfer\"]").click();
    }
}
