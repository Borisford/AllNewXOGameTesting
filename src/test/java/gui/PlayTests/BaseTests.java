package gui.PlayTests;

import gui.driver.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;


public class BaseTests {

    @BeforeAll
    public static void before(){
        WebDriverManager.initChrome();
    }

    @AfterAll
    public static void closeTest(){
        WebDriverManager.killCurrentDriver();
    }

}
