package taskPages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class RESTPage extends BasePage {
    @Step("Вернуться на главную")
    public MainPaige goBack() {
        $(By.id("goMainBackButton")).click();
        return page(MainPaige.class);
    }
}