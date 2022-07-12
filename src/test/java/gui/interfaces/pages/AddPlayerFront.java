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

public class AddPlayerFront  implements UrlName, Page {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/player";
    }

    @Override
    public String getName() {
        return "Add player";
    }

    @FindBy(id = "playerNameInput")
    private WebElement playerNameInput;

    @FindBy(id = "playerNameButton")
    private WebElement playerNameButton;

    @FindBy(id = "Message")
    private WebElement Message;

    public AddPlayerFront() {

    }

    public void addPlayer(String name) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(playerNameInput));
        playerNameInput.click();
        playerNameInput.sendKeys(name);

        wait.until(ExpectedConditions.elementToBeClickable(playerNameButton));
        playerNameButton.click();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(playerNameButton, "Добавить");
    }

    public String getMessage() {
        return Message.getText();
    }

    @Override
    public void preActions() {

    }
}
