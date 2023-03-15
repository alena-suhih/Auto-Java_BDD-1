package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    // к сожалению, разработчики не дали нам удобного селектора, поэтому так
    private ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    private SelenideElement replenishBalanceButton = $("[data-test-id=\"action-deposit\"] button");

    public DashboardPage() {
    }

    public int getCardBalanceByTestId(String id) {
        String testId = String.format("[data-test-id=\"%s\"]", id);
        var card = $(testId);
        var cardText = card.text();
        return extractBalance(cardText);
    }

    private int extractBalance(String text) {
        var start = text.indexOf(balanceStart);
        var finish = text.indexOf(balanceFinish);
        var value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public ReplenishPage goToReplenishPageForCardById(String id) {
        String testId = String.format("[data-test-id=\"%s\"] button", id);
        var replenishButton = $(testId);
        replenishButton.click();
        return new ReplenishPage();
    }
}