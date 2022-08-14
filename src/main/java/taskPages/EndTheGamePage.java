package taskPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class EndTheGamePage extends BasePage {

    @Step("Перейти к выбору новой игры")
    public GamesStartPage newGame() {
        $(By.id("NewGameButton")).click();
        return page(GamesStartPage.class);
    }

    @Step("Перейти на главную")
    public GamesStartPage newPlayer() {
        $(By.id("BackButton")).click();
        return page(GamesStartPage.class);
    }

    @Step("Получить результат игры")
    public EndTheGamePage getPlayersKey(String[] message, int position) {
        message[position] = $(By.id("Message")).getText();
        return page(EndTheGamePage.class);
    }

    @Step("Проверить результат игры")
    public void checkGameResult(String result) {
        String message = $(By.id("Message")).getText();
        if (result == null) {
            Assertions.assertEquals("Игра закончилась вничью.", message);
        } else {
            Assertions.assertEquals(result, message.substring(32));
        }
    }
}
