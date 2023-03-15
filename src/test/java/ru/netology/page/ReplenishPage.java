package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;
import static ru.netology.data.DataGenerator.generateValidAmount;

public class ReplenishPage {
    private SelenideElement element = $("h1");

    public ReplenishPage() {
        element.shouldBe(Condition.visible);
    }

    public void fillData(String cardNumber) {
        $("[data-test-id=\"amount\"] input").setValue(DataGenerator.replenishSum);
        $("[data-test-id=\"from\"] input").setValue(cardNumber);
    }

    public void replenishCard() {
        $("[data-test-id=\"action-transfer\"]").click();
    }
}
