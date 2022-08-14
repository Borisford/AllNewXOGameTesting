package taskPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class MainPaige extends BasePage {
    @Step("Нажать на кнопку \"Создать игрока\"")
    public AddPlayerPage pressCreatePlayerButton() {
        $(By.id("createPlayerButton")).click();
        return page(AddPlayerPage.class);
    }

    @Step("Нажать на кнопку \"Создать игрока\"")
    public RESTPage pressGoRESTButton() {
        $(By.id("goRESTButton")).click();
        return page(RESTPage.class);
    }
}
