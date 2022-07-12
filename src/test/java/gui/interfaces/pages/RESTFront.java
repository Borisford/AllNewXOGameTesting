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

public class RESTFront implements UrlName, Page {
    @Override
    public String getUrl() {
        return Constants.IP + "/gameplay/front/about";
    }

    @Override
    public String getName() {
        return "About REST";
    }

    @FindBy(id = "goMainBackButton")
    private WebElement goMainBackButton;

    public RESTFront() {

    }

    public void goBack() {
        WebDriverWait wait = new WebDriverWait(WebDriverManager.getCurrentDriver(), Duration.ofSeconds(Constants.DEFAULT_TIMEOUT));

        wait.until(ExpectedConditions.elementToBeClickable(goMainBackButton));
        goMainBackButton.click();
    }

    @Override
    public void init() {
        initPage();
    }

    @Override
    public boolean isPageLoaded() {
        return PageUtils.isElementTextContains(goMainBackButton, "Назад");
    }

    @Override
    public void preActions() {

    }
}