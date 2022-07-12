package gui.interfaces.pages;

import gui.driver.WebDriverManager;
import gui.helpers.Constants;
import gui.helpers.PageUtils;
import gui.interfaces.Page;
import gui.interfaces.UrlName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainFront implements UrlName, Page {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/main";
    }

    @Override
    public String getName() {
        return "Main page";
    }

    @FindBy(id = "createPlayerButton")
    private WebElement createPlayerButton;

    @FindBy(id = "goRESTButton")
    private WebElement goRESTButton;

    public MainFront() {

    }

    public void goREST() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(goRESTButton));
        goRESTButton.click();
    }

    public void goCreatePlayer() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(createPlayerButton));
        createPlayerButton.click();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(createPlayerButton, "Создать игрока");
    }

    @Override
    public void preActions() {

    }
}