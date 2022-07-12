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

public class GameNumberFront implements UrlName, Page, gui.interfaces.Message {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/step";
    }

    @Override
    public String getName() {
        return "Game number";
    }


    @FindBy(id = "GameNumberInput")
    private WebElement GameNumberInput;

    @FindBy(id = "JoinButton")
    private WebElement JoinButton;

    @FindBy(id = "Message")
    private WebElement Message;

    public GameNumberFront() {

    }

    public void join(String gameNumber) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(GameNumberInput));
        GameNumberInput.click();
        GameNumberInput.sendKeys(gameNumber);

        wait.until(ExpectedConditions.elementToBeClickable(JoinButton));
        JoinButton.click();
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
        return PageUtils.isElementTextContains(JoinButton, "Присоединиться");
    }

    @Override
    public void preActions() {

    }
}
