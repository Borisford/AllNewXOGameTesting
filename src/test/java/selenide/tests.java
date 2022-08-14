package selenide;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import gui.helpers.Constants;
import gui.helpers.RandomName;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import taskPages.*;

import java.awt.*;
import java.awt.event.KeyEvent;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.switchTo;


public class tests {
    @BeforeEach
    public  void option(){
        Configuration.timeout=6000;
        Configuration.browser="chrome";
        //Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }


    @Feature("Дымовой тест")
    //@DisplayName("Проверка способности нажимать на кноки")
    @Test
    public void smokeTest(){
        String[] playerKey = new String[1];
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class);
    }

    @Feature("Проверить переход на ")
    @Test
    public void GoRESTTest() {
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressGoRESTButton()
                .goBack();
    }

    @Feature("Тест PvP")
    @Test
    public void GoPvPTest(){
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameGood(game[0]);
        switchTo().window(0);
        first.doStepGood("4");
        switchTo().window(1);
        NotYourStepPage notYourStepPage = new NotYourStepPage();
        notYourStepPage.refreshGood().doStepGood("0");
        switchTo().window(0);
        notYourStepPage.refreshGood().doStepGood("6");
        switchTo().window(1);
        notYourStepPage.refreshGood().doStepGood("8");
        switchTo().window(0);
        notYourStepPage.refreshGood().doLastStepGood("2")
                .checkGameResult(nameOne);
    }

    @Feature("Тест PvP")
    @Test
    public void SameNameTest() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerBad(nameOne)
                .checkMessage("Имя " + nameOne + " уже используется.");
    }

    @Feature("Негативный тест ходов")
    @Test
    public void doIllegalMove() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameGood(game[0]);
        switchTo().window(0);
        first.doStepBad("22")
                .checkMessage("Выбранной ячейки не существует");
    }

    @Feature("Присоединиться к несуществующей игре")
    @Test
    public void connectToIllegalGame() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameBad(game[0].substring(1))
                .checkMessage("Игра не найдена");
    }

    @Feature("Присоединиться к игре используя некорректный номер")
    @Test
    public void connectToIllegalGame2() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameBad(game[0] + "11111111111111111111111111")
                .checkMessage("Формат номера игры некорректен");
    }

    @Feature("Присоединиться к игре используя некорректный номер")
    @Test
    public void connectToIllegalGame3() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameBad(game[0] + nameOne)
                .checkMessage("Формат номера игры некорректен");
    }

    @Feature("Совершить ход не дожидаясь второго игрока")
    @Test
    public void makeStepInNotFullGameTest() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        first.doStepBad("4")
                .checkMessage("Ждем других игроков");
    }

    @Feature("Тест PvP")
    @Test
    public void GoPvPTestDraw() {
        String nameOne = RandomName.get();
        String[] game = new String[1];
        YourStepPage first = open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameOne)
                .goMultiStart()
                .getPlayGroundKey(game, 0);
        String nameTwo = RandomName.get();
        addTab();
        switchTo().window(1);
        open(Constants.IP + "/gameplay/front/main", taskPages.MainPaige.class)
                .pressCreatePlayerButton()
                .addPlayerGood(nameTwo)
                .goMultiConnect()
                .joinGameGood(game[0]);
        NotYourStepPage notYourStepPage = new NotYourStepPage();
        switchTo().window(0);
        first.doStepGood("4");
        switchTo().window(1);
        notYourStepPage.refreshGood().doStepGood("0");
        switchTo().window(0);
        notYourStepPage.refreshGood().doStepGood("6");
        switchTo().window(1);
        notYourStepPage.refreshGood().doStepGood("2");
        switchTo().window(0);
        notYourStepPage.refreshGood().doStepGood("1");
        switchTo().window(1);
        notYourStepPage.refreshGood().doStepGood("7");
        switchTo().window(0);
        notYourStepPage.refreshGood().doStepGood("5");
        switchTo().window(1);
        notYourStepPage.refreshGood().doStepGood("3");
        switchTo().window(0);
        notYourStepPage.refreshGood().doLastStepGood("8")
                .checkGameResult(null);
    }

    @AfterEach
    void tearDown() {
        Selenide.closeWindow();
    }

    private void addTab() {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);
    }
}
