package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        open("http://localhost:9999/");
        var loginPage = new LoginPage();
        var authInfo = DataGenerator.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataGenerator.getVerificationCode();
        var dashboardPage = verificationPage.validVerify(verificationCode);

        var firstCardInfo = DataGenerator.getFirstCardInfo();
        int firstCardBalance = dashboardPage.getCardBalanceByTestId(firstCardInfo.getTestId());
        var replenishPage = dashboardPage
                .goToReplenishPageForCardById(firstCardInfo.getTestId());
        replenishPage.fillData(DataGenerator.getSecondCardInfo().getCardNumber());
        replenishPage.replenishCard();
        int newFirstCardBalance = dashboardPage.getCardBalanceByTestId(firstCardInfo.getTestId());
        Assertions.assertEquals(firstCardBalance + Integer.parseInt(DataGenerator.replenishSum), newFirstCardBalance);
    }
}
