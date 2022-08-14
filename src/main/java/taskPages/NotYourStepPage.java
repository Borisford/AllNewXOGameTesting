package taskPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class NotYourStepPage extends BasePage {

    @Step("Обновить успешно")
    public YourStepPage refreshGood() {
        $(By.id("RefreshButton")).click();
        return page(YourStepPage.class);
    }

    @Step("Обновить неуспешно")
    public NotYourStepPage refreshBad() {
        $(By.id("RefreshButton")).click();
        return page(NotYourStepPage.class);
    }

    @Step("Получить сообщение")
    public NotYourStepPage getMessage(String[] message, int position) {
        message[position] = $(By.id("Message")).getText();
        return page(NotYourStepPage.class);
    }



    @Step("Получить ключ игры")
    public NotYourStepPage getPlayGroundKey(String[] message, int position) {
        message[position] = $(By.id("PlayGroundKey")).getText().substring(15);
        return page(NotYourStepPage.class);
    }
}
