package ry.netology.test;

import org.junit.jupiter.api.Test;
import ry.netology.data.DataGenerator;
import ry.netology.page.DashboardPage;
import ry.netology.page.LoginPage;
import ry.netology.page.ReplenishPage;

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
        System.out.println("card balance " + firstCardBalance);
        ReplenishPage replenishPage = dashboardPage.goToReplenishPageForCardById(firstCardInfo.getTestId());
    }
}
