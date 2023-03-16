package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {
    private final String replenishSum = "100";

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataGenerator.getFirstCardInfo();
        var secondCardInfo = DataGenerator.getSecondCardInfo();
        int firstCardBalance = dashboardPage.getCardBalanceByTestId(firstCardInfo.getTestId());
        int secondCardBalance = dashboardPage.getCardBalanceByTestId(secondCardInfo.getTestId());
        var replenishPage = dashboardPage
                .goToReplenishPageForCardById(firstCardInfo.getTestId());
        replenishPage.fillData(DataGenerator.getSecondCardInfo().getCardNumber(), replenishSum);
        replenishPage.replenishCard();
        int newFirstCardBalance = dashboardPage.getCardBalanceByTestId(firstCardInfo.getTestId());
        int newSecondCardBalance = dashboardPage.getCardBalanceByTestId(secondCardInfo.getTestId());
        Assertions.assertEquals(firstCardBalance + Integer.parseInt(replenishSum), newFirstCardBalance);
        Assertions.assertEquals(secondCardBalance - Integer.parseInt(replenishSum), newSecondCardBalance);
    }
}
