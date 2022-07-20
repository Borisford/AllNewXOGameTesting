package gui.PlayTests;


import gui.driver.WebDriverManager;
import gui.helpers.CustomUtils;
import gui.helpers.RandomName;
import gui.interfaces.pages.*;
import gui.steps.Steps;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



public class Tests  extends BaseTests {
    Steps steps;



    @BeforeEach
    public void beforeEach() {
        steps = new Steps();
    }

    @Feature("Проверка работоспособности игры")
    //@DisplayName("Открытите сайта")
    @Test()
    public void SmokeTest() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
    }

    //@DisplayName("Открытите сайта")
    @Test()
    public void GoRESTTest() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        RESTFront restFront = steps.goREST(mainFront);
        steps.restGoBack(restFront, mainFront);
    }

    //@DisplayName("PvP test")
    @Test()
    public void GoPvPTest() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        String nameTwo = RandomName.get();
        gamesStartFront = steps.addPlayer(addPlayerFront, nameTwo);
        String numberTwo = steps.getPlayersKey(gamesStartFront);
        NotYourStepFront notYourStepFront = steps.goMultiJoin(gamesStartFront, gameNumber);
        steps.goTab(numberOne, yourStepFront, nameOne);
        steps.doStep(yourStepFront, "4", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "0", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "6", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "8", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "2", nameOne);
        steps.checkGameResult(nameOne);
    }

    //@DisplayName("PvB test")
    @Test()
    public void GoPvBTest() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String name = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, name);
        YourStepFront yourStepFront = steps.goSoloStart(gamesStartFront);
        while (steps.makeRandomMove(yourStepFront)) {}
        steps.checkGameEnd();
    }

    //@DisplayName("PvP test")
    @Test()
    public void SameNameTest() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        steps.addNextPlayer(addPlayerFront, nameOne);
    }

    //@DisplayName("PvB test")
    @Test()
    public void doIllegalMove() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String name = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, name);
        YourStepFront yourStepFront = steps.goSoloStart(gamesStartFront);
        steps.doStep(yourStepFront, "22", name);
        Assertions.assertEquals(yourStepFront.getMessage(), "Выбранной ячейки не существует");
    }

    //@DisplayName("PvP test")
    @Test()
    public void connectToIllegalGame() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        String nameTwo = RandomName.get();
        gamesStartFront = steps.addPlayer(addPlayerFront, nameTwo);
        String numberTwo = steps.getPlayersKey(gamesStartFront);
        GameNumberFront gameNumberFront = steps.goIllegalGame(gamesStartFront, gameNumber.substring(1));
        steps.messageCheck(gameNumberFront, "Игра не найдена");
    }

    //@DisplayName("PvP test")
    @Test()
    public void connectToIllegalGame2() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        String nameTwo = RandomName.get();
        gamesStartFront = steps.addPlayer(addPlayerFront, nameTwo);
        String numberTwo = steps.getPlayersKey(gamesStartFront);
        GameNumberFront gameNumberFront = steps.goIllegalGame(gamesStartFront, gameNumber + 1);
        steps.messageCheck(gameNumberFront, "Формат номера игры некорректен");
    }

    //@DisplayName("PvP test")
    @Test()
    public void connectToIllegalGame3() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        String nameTwo = RandomName.get();
        gamesStartFront = steps.addPlayer(addPlayerFront, nameTwo);
        String numberTwo = steps.getPlayersKey(gamesStartFront);
        GameNumberFront gameNumberFront = steps.goIllegalGame(gamesStartFront, gameNumber + nameOne);
        steps.messageCheck(gameNumberFront, "Формат номера игры некорректен");
    }

    @Test()
    public void GoPvPTestDraw() {
        MainFront mainFront = new MainFront();
        steps.goPage(mainFront);
        AddPlayerFront addPlayerFront = steps.goAddPlayer(mainFront);
        String nameOne = RandomName.get();
        GamesStartFront gamesStartFront = steps.addPlayer(addPlayerFront, nameOne);
        String numberOne = steps.getPlayersKey(gamesStartFront);
        YourStepFront yourStepFront = steps.goMultiStart(gamesStartFront);
        String gameNumber = yourStepFront.getPlayGroundKey();
        steps.goPageInNewTab(mainFront);
        steps.goAddPlayer(mainFront);
        String nameTwo = RandomName.get();
        gamesStartFront = steps.addPlayer(addPlayerFront, nameTwo);
        String numberTwo = steps.getPlayersKey(gamesStartFront);
        NotYourStepFront notYourStepFront = steps.goMultiJoin(gamesStartFront, gameNumber);
        steps.goTab(numberOne, yourStepFront, nameOne);
        steps.doStep(yourStepFront, "4", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "0", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "6", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "2", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "1", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "7", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "5", nameOne);
        steps.goTab(numberTwo, notYourStepFront, nameTwo);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "3", nameTwo);
        steps.goTab(numberOne, notYourStepFront, nameOne);
        steps.refresh(notYourStepFront);
        steps.doStep(yourStepFront, "8", nameOne);
        steps.checkGameResult(null);
    }
}
