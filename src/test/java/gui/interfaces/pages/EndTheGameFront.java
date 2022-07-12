package gui.interfaces.pages;

import gui.driver.WebDriverManager;
import gui.helpers.Constants;
import gui.helpers.PageUtils;
import gui.interfaces.Message;
import gui.interfaces.Page;
import gui.interfaces.UrlName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EndTheGameFront implements UrlName, Page, gui.interfaces.Message {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/step";
    }

    @Override
    public String getName() {
        return "End the Game";
    }


    @FindBy(id = "NewGameButton")
    private WebElement NewGameButton;

    @FindBy(id = "BackButton")
    private WebElement BackButton;

    @FindBy(id = "Message")
    private WebElement Message;

    public EndTheGameFront() {

    }

    @Override
    public String getMessage() {
        return Message.getText();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(BackButton, "Назад");
    }

    @Override
    public void preActions() {

    }
}
