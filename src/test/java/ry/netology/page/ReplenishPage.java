package ry.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ReplenishPage {
    private SelenideElement element = $("h1");

    public ReplenishPage() {
        element.shouldBe(Condition.visible);
    }
}
