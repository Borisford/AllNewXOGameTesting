package taskPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class GamesStartPage extends BasePage {

    @Step("Начать соло игру с ботом")
    public YourStepPage goSoloStart() {
        $(By.id("SoloStartButton")).click();
        return page(YourStepPage.class);
    }

    @Step("Начать игру с живым противником")
    public YourStepPage goMultiStart() {
        $(By.id("MultiStartButton")).click();
        return page(YourStepPage.class);
    }

    @Step("Присоединиться к игре с живым противником")
    public GameNumberPage goMultiConnect() {
        $(By.id("MultiConnectButton")).click();
        return page(GameNumberPage.class);
    }

    @Step("Получить ключ игрока")
    public GamesStartPage getPlayersKey(String[] key, int position) {
        key[position] = $(By.id("PlayersKey")).getText();
        return page(GamesStartPage.class);
    }
}
