package taskPages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class YourStepPage extends BasePage {

    @Step("Успешно сделать ход в поле {cellNo}")
    public NotYourStepPage doStepGood(String cellNo) {
        $(By.id("CellInput")).setValue(cellNo).pressEnter();
        return page(NotYourStepPage.class);
    }

    @Step("Успешно сделать ход в поле {cellNo}")
    public EndTheGamePage doLastStepGood(String cellNo) {
        $(By.id("CellInput")).setValue(cellNo).pressEnter();
        return page(EndTheGamePage.class);
    }

    @Step("Не успешно сделать ход в поле {cellNo}")
    public YourStepPage doStepBad(String cellNo) {
        $(By.id("CellInput")).setValue(cellNo).pressEnter();
        return page(YourStepPage.class);
    }

    @Step("Получить ключ игры")
    public YourStepPage getPlayGroundKey(String[] message, int position) {
        message[position] = $(By.id("PlayGroundKey")).getText().substring(15);
        return page(YourStepPage.class);
    }

    @Step("Получить сообщение")
    public YourStepPage getMessage(String[] message, int position) {
        message[position] = $(By.id("Message")).getText();
        return page(YourStepPage.class);
    }

    @Step("Проверить сообщение")
    public YourStepPage checkMessage(String message) {
        Assertions.assertEquals($(By.id("Message")).getText(), message);
        return page(YourStepPage.class);
    }

    @Step("Получить ключ игрока")
    public YourStepPage getPlayersKey(String[] message, int position) {
        message[position] = $(By.id("PlayersKey")).getText();
        return page(YourStepPage.class);
    }
}
