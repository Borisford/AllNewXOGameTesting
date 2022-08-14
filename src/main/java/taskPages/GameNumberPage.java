package taskPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GameNumberPage extends BasePage {
    @Step("Успешно присоединиться к игре с кючом {gameNumber}")
    public NotYourStepPage joinGameGood(String gameNumber) {
        $(By.id("GameNumberInput")).setValue(gameNumber).pressEnter();
        return page(NotYourStepPage.class);
    }

    @Step("Не спешно присоединиться к игре с кючом {gameNumber}")
    public GameNumberPage joinGameBad(String gameNumber) {
        $(By.id("GameNumberInput")).setValue(gameNumber).pressEnter();
        return page(GameNumberPage.class);
    }

    @Step("Получить сообщение")
    public GamesStartPage getPlayersKey(String[] message, int position) {
        message[position] = $(By.id("Message")).getText();
        return page(GamesStartPage.class);
    }

    @Step("Проверить сообщение")
    public AddPlayerPage checkMessage(String message) {
        Assertions.assertEquals($(By.id("Message")).getText(), message);
        return page(AddPlayerPage.class);
    }
}
