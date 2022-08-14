package gui.steps;


import gui.driver.WebDriverManager;
import gui.interfaces.Message;
import gui.interfaces.PlayersKey;
import gui.interfaces.UrlName;
import gui.interfaces.pages.*;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Steps {
    private WebDriver driver;


    public Steps() {
        this.driver = WebDriverManager.getCurrentDriver();
    }

    @Step("Перейти на страницу {pageName}")
    public void goPage(UrlName pageName) {
        driver.get(pageName.getUrl());
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(pageName.getName().toLowerCase(Locale.ROOT)));
        pageName.init();
    }

    @Step("Открыть страницу {pageName} в новой вкладке и перейти на неё")
    public void goPageInNewTab(UrlName pageName) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(pageName.getUrl());
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(pageName.getName().toLowerCase(Locale.ROOT)));
        pageName.init();
    }

    @Step("Перейти со главной страницы на страницу About REST")
    public RESTFront goREST(MainFront mainFront) {
        mainFront.goREST();
        RESTFront restFront = new RESTFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(restFront.getName().toLowerCase(Locale.ROOT)));
        restFront.init();
        return restFront;
    }

    @Step("Перейти со главной страницы на страницу About REST")
    public void restGoBack(RESTFront restFront, UrlName pageName) {
        restFront.goBack();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(pageName.getName().toLowerCase(Locale.ROOT)));
    }

    @Step("Перейти к созданию персонажа")
    public AddPlayerFront goAddPlayer(MainFront mainFront) {
        mainFront.goCreatePlayer();
        AddPlayerFront addPlayerFront = new AddPlayerFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(addPlayerFront.getName().toLowerCase(Locale.ROOT)));
        addPlayerFront.init();
        return addPlayerFront;
    }

    @Step("Создать игрока с именем {name}")
    public GamesStartFront addPlayer(AddPlayerFront addPlayerFront, String name) {
        addPlayerFront.addPlayer(name);
        GamesStartFront gamesStartFront = new GamesStartFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(gamesStartFront.getName().toLowerCase(Locale.ROOT)));
        gamesStartFront.init();
        return gamesStartFront;
    }

    @Step("Полвторно создать игрока с именем {name}")
    public void addNextPlayer(AddPlayerFront addPlayerFront, String name) {
        addPlayerFront.addPlayer(name);
        Assertions.assertEquals(addPlayerFront.getMessage(), "Имя "+name+" уже используется.");
    }

    @Step("Начать одиночную игру")
    public YourStepFront goSoloStart(GamesStartFront gamesStartFront) {
        gamesStartFront.goSoloStart();
        YourStepFront yourStepFront = new YourStepFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(yourStepFront.getName().toLowerCase(Locale.ROOT)));
        yourStepFront.init();
        return yourStepFront;
    }

    @Step("Начать игру с живым игроком")
    public YourStepFront goMultiStart(GamesStartFront gamesStartFront) {
        gamesStartFront.goMultiStart();
        YourStepFront yourStepFront = new YourStepFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(yourStepFront.getName().toLowerCase(Locale.ROOT)));
        yourStepFront.init();
        return yourStepFront;
    }

    @Step("Присоединиться к игре с живым игроком")
    public NotYourStepFront goMultiJoin(GamesStartFront gamesStartFront, String gameNumber) {
        gamesStartFront.goMultiConnect();
        GameNumberFront gameNumberFront = new GameNumberFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(gameNumberFront.getName().toLowerCase(Locale.ROOT)));
        gameNumberFront.init();
        gameNumberFront.join(gameNumber);
        NotYourStepFront notYourStepFront = new NotYourStepFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(notYourStepFront.getName().toLowerCase(Locale.ROOT)));
        notYourStepFront.init();
        return notYourStepFront;
    }

    @Step("Поставить символ игрока {name} на позицию {cellNo}")
    public void doStep(YourStepFront yourStepFront, String cellNo, String name) {
        yourStepFront.doStep(cellNo);
    }

    public String getMessage(Message page) {
        return page.getMessage();
    }

    public String getPlayersKey(PlayersKey page) {
        return page.getPlayersKey().substring(17);
    }

    @Step("Перейти на вкладку игрока {name}")
    public void goTab(String playersKey, PlayersKey page, String name) {
        List<String> tabs = new ArrayList<>(WebDriverManager.getCurrentDriver().getWindowHandles());
        for (String tab : tabs) {
            WebDriverManager.getCurrentDriver().switchTo().window(tab);
            if (page.getPlayersKey().contains(playersKey)) {
                return;
            }
        }
        Assertions.assertTrue(false,"Не удалось открыть вкладку игрока с ключом "+playersKey+"");
    }

    @Step("Проверить переход хода")
    public void refresh(NotYourStepFront notYourStepFront) {
        notYourStepFront.refresh();
        YourStepFront yourStepFront = new YourStepFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(yourStepFront.getName().toLowerCase(Locale.ROOT)));
        yourStepFront.init();
    }

    @Step("Проверить результат игры")
    public void checkGameResult(String result) {
        EndTheGameFront endTheGameFront = new EndTheGameFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(endTheGameFront.getName().toLowerCase(Locale.ROOT)));
        endTheGameFront.init();
        if (result == null) {
            Assertions.assertEquals("Игра закончилась вничью.", endTheGameFront.getMessage());
        } else {
            Assertions.assertEquals(result, endTheGameFront.getMessage().substring(32));
        }
    }

    @Step("Проверить, что игра окончена")
    public void checkGameEnd() {
        EndTheGameFront endTheGameFront = new EndTheGameFront();
        endTheGameFront.init();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(endTheGameFront.getName().toLowerCase(Locale.ROOT)));
    }

    @Step("Сделать случайный ход")
    public boolean makeRandomMove(YourStepFront yourStepFront) {
        Random random = new Random();
        NotYourStepFront notYourStepFront = new NotYourStepFront();
        EndTheGameFront endTheGameFront = new EndTheGameFront();
        int step;
        boolean res = true;
        String title = driver.getTitle().toLowerCase(Locale.ROOT);
        while (!title.equals(notYourStepFront.getName().toLowerCase(Locale.ROOT)) && !title.equals(endTheGameFront.getName().toLowerCase(Locale.ROOT))) {
            step = random.nextInt(9);
            yourStepFront.doStep(step + "");
            title = driver.getTitle().toLowerCase(Locale.ROOT);
        }
        if (!title.equals(notYourStepFront.getName().toLowerCase(Locale.ROOT)) && !title.equals(yourStepFront.getName().toLowerCase(Locale.ROOT))) {
            res = false;
        }
        return res;
    }

    @Step("Сравнить сообщение с эталоном")
    public void messageCheck(Message message, String str) {
        Assertions.assertEquals(message.getMessage(), str);
    }

    @Step("Присоединиться к игре с живым игроком")
    public GameNumberFront goIllegalGame(GamesStartFront gamesStartFront, String gameNumber) {
        gamesStartFront.goMultiConnect();
        GameNumberFront gameNumberFront = new GameNumberFront();
        Assertions.assertTrue(driver.getTitle().toLowerCase(Locale.ROOT).contains(gameNumberFront.getName().toLowerCase(Locale.ROOT)));
        gameNumberFront.init();
        gameNumberFront.join(gameNumber);
        return gameNumberFront;
    }
}
