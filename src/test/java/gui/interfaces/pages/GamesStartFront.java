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

public class GamesStartFront implements UrlName, Page, gui.interfaces.PlayersKey {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/player/input";
    }

    @Override
    public String getName() {
        return "Games start";
    }

    @FindBy(id = "SoloStartButton")
    private WebElement SoloStartButton;

    @FindBy(id = "MultiStartButton")
    private WebElement MultiStartButton;

    @FindBy(id = "MultiConnectButton")
    private WebElement MultiConnectButton;

    @FindBy(id = "PlayersKey")
    private WebElement PlayersKey;

    public GamesStartFront() {

    };

    public void goSoloStart() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(SoloStartButton));
        SoloStartButton.click();
    }

    public void goMultiStart() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(MultiStartButton));
        MultiStartButton.click();
    }

    public void goMultiConnect() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(MultiConnectButton));
        MultiConnectButton.click();
    }

    @Override
    public String getPlayersKey() {
        return PlayersKey.getText();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(SoloStartButton, "Начать соло с ботом");
    }

    @Override
    public void preActions() {

    }
}
