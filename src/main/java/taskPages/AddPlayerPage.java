package taskPages;

import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class AddPlayerPage extends BasePage {
    @Step("Успешно добавить игрока по имени {name}")
    public GamesStartPage addPlayerGood(String name) {
        $(By.id("playerNameInput")).setValue(name).pressEnter();
        return page(GamesStartPage.class);
    }

    @Step("Не спешно добавить игрока по имени {name}")
    public AddPlayerPage addPlayerBad(String name) {
        $(By.id("playerNameInput")).setValue(name).pressEnter();
        return page(AddPlayerPage.class);
    }

    @Step("Получить сообщение")
    public AddPlayerPage getMessage(String[] message, int position) {
        message[position] = $(By.id("Message")).getText();
        return page(AddPlayerPage.class);
    }

    @Step("Проверить сообщение")
    public AddPlayerPage checkMessage(String message) {
        Assertions.assertEquals($(By.id("Message")).getText(), message);
        return page(AddPlayerPage.class);
    }
}
