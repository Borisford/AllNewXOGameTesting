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

public class MessageFront implements UrlName, Page, gui.interfaces.Message {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/player/input";
    }

    @Override
    public String getName() {
        return "Message page";
    }


    @FindBy(id = "BackButton")
    private WebElement BackButton;

    @FindBy(id = "Message")
    private WebElement Message;

    public MessageFront() {

    }

    public void doStep(String cellNo) {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(BackButton));
        BackButton.click();
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
