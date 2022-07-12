package gui.interfaces.pages;

import gui.driver.WebDriverManager;
import gui.helpers.Constants;
import gui.helpers.PageUtils;
import gui.interfaces.Page;
import gui.interfaces.PlayGroundKey;
import gui.interfaces.PlayersKey;
import gui.interfaces.UrlName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class YourStepFront implements UrlName, Page, gui.interfaces.Message, gui.interfaces.PlayersKey, gui.interfaces.PlayGroundKey {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/about";
    }

    @Override
    public String getName() {
        return "Your step";
    }

    @FindBy(id = "CellInput")
    private WebElement CellInput;

    @FindBy(id = "StepDoButton")
    private WebElement StepDoButton;

    @FindBy(id = "Message")
    private WebElement Message;

    @FindBy(id = "GameField")
    private WebElement GameField;

    @FindBy(id = "PlayersKey")
    private WebElement PlayersKey;

    @FindBy(id = "PlayGroundKey")
    private WebElement PlayGroundKey;

    public YourStepFront() {

    }

    public void doStep(String cellNo) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(CellInput));
        CellInput.click();
        CellInput.sendKeys(cellNo);

        wait.until(ExpectedConditions.elementToBeClickable(StepDoButton));
        StepDoButton.click();
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
        return PlayGroundKey.getText().substring(15);
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(StepDoButton, "Сделать ход");
    }

    @Override
    public void preActions() {

    }
}
