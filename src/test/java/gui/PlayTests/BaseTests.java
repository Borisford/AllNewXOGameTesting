package gui.PlayTests;

import gui.driver.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public class BaseTests {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void before(){
        WebDriverManager.initChrome();
        chromeDriver = WebDriverManager.getCurrentDriver();
    }

    @AfterEach
    public void closeTest(){
        WebDriverManager.killCurrentDriver();
    }

}
