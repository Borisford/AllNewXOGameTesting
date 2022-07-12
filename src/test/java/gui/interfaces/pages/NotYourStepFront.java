package gui.interfaces.pages;

import gui.driver.WebDriverManager;
import gui.helpers.Constants;
import gui.helpers.PageUtils;
import gui.interfaces.Page;
import gui.interfaces.UrlName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class NotYourStepFront implements UrlName, Page, gui.interfaces.Message, gui.interfaces.PlayersKey, gui.interfaces.PlayGroundKey {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/find";
    }

    @Override
    public String getName() {
        return "Not Your step";
    }


    @FindBy(id = "RefreshButton")
    private WebElement RefreshButton;

    @FindBy(id = "Message")
    private WebElement Message;

    @FindBy(id = "PlayersKey")
    private WebElement PlayersKey;

    @FindBy(id = "PlayGroundKey")
    private WebElement PlayGroundKey;

    public NotYourStepFront() {

    }

    public void refresh() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(RefreshButton));
        RefreshButton.click();
    }

    @Override
    public String getMessage() {
        return Message.getText();
    }

    @Override
    public String getPlayersKey() {
        return PlayersKey.getText();
    }

    @Override
    public String getPlayGroundKey() {
        return PlayGroundKey.getText();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(RefreshButton, "Обновить");
    }

    @Override
    public void preActions() {

    }
}
