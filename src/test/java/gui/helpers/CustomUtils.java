package gui.helpers;


import gui.driver.WebDriverManager;
import gui.interfaces.UrlName;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.model.Status;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomUtils {

    public static void print(String text) {
        Allure.step(text);
        System.out.println(text);
    }

    public static void printError(String text) {
        Allure.step(text, Status.FAILED);
        System.out.println(text);
    }

    public static void newTab(UrlName pageName) {
        System.out.println("!1");
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
        System.out.println("!2");
        WebDriverManager.getCurrentDriver().findElement(By.linkText(pageName.getUrl())).sendKeys(selectLinkOpeninNewTab);
        System.out.println("!3");
    }

    public static boolean goTab(String name) {
        List<String> tabs = new ArrayList<>(WebDriverManager.getCurrentDriver().getWindowHandles());
        for (String tab : tabs){
            WebDriverManager.getCurrentDriver().switchTo().window(tab);
            if(WebDriverManager.getCurrentDriver().getTitle().toLowerCase(Locale.ROOT).contains(name.toLowerCase(Locale.ROOT)))
                return true;
        }
        Assertions.assertTrue(false,"Не удалось открыть вкладку с именем "+name+"");
        return false;
    }

    public static boolean elementExist(String xpath, WebDriver driver) {
        try {
            WebElement clicking = driver.findElement(By.xpath(xpath));
        } catch (NoSuchElementException e) {
            return false;
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
