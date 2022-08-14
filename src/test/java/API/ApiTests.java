package API;

import API.entities.PlayGroundEntity;
import API.entities.PlayerEntity;
import API.steps.Steps;
import gui.helpers.RandomName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Random;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ApiTests {
    @Test
    public void createPlayerTest() {
        String vovaName = RandomName.get();

        PlayerEntity vova = Steps.createPlayer(vovaName);


        Assertions.assertEquals(vova.getName(), vovaName);

        given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + vovaName)
                .then()
                .log().body()
                .statusCode(400);
    }

    @Test
    public void getPlayerTest() {
        String vovaName = RandomName.get();

        PlayerEntity postVova = Steps.createPlayer(vovaName);

        Assertions.assertEquals(postVova.getName(), vovaName);

        PlayerEntity getVova = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/players/" + postVova.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);

        Assertions.assertEquals(postVova.getName(), getVova.getName());
        Assertions.assertEquals(postVova.getId(), getVova.getId());
    }

    @Test
    public void testSimpleAutoGameTest() {
        String result = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/auto")
                .then()
                .log().body()
                .statusCode(400)
                .extract()
                .asString();

        Assertions.assertTrue(result.startsWith("Игра закончилась"));


    }

    @Test
    public void testComplexAutoGameTest() {
        String result = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/complex/auto?numberOfPlayers=5")
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();

        Assertions.assertTrue(result.startsWith("Игра закончилась"));
    }

    @Test
    public void testSimpleMultiPlayerGameTest(){
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = Steps.startMultiGame(playerOne, playerTwo);

        game = Steps.doStep(game, playerOne, 4, 'x');

        game = Steps.doStep(game, playerTwo, 0, '*');

        game = Steps.doStep(game, playerOne, 6, 'x');

        game = Steps.doStep(game, playerTwo, 2, '*');

        game = Steps.doStep(game, playerOne, 8, 'x');

        game = Steps.doLastStep(game, playerTwo, 1, '*');

        Steps.winnerCheck(game, playerTwo);
    }

    @Test
    public void testSimpleMultiPlayerGameTestDraw(){
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = Steps.startMultiGame(playerOne, playerTwo);

        game = Steps.doStep(game, playerOne, 4, 'x');

        game = Steps.doStep(game, playerTwo, 0, '*');

        game = Steps.doStep(game, playerOne, 6, 'x');

        game = Steps.doStep(game, playerTwo, 2, '*');

        game = Steps.doStep(game, playerOne, 1, 'x');

        game = Steps.doStep(game, playerTwo, 7, '*');

        game = Steps.doStep(game, playerOne, 5, 'x');

        game = Steps.doStep(game, playerTwo, 3, '*');

        game = Steps.doLastStep(game, playerOne, 8, 'x');

        Steps.drawCheck(game);
    }

    @Test
    public void illegalCellTest() {
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = Steps.startMultiGame(playerOne, playerTwo);

        String requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+playerOne.getId()+",\n" +
                "    \"playerKey\" : "+playerOne.getPlayerKey()+",\n" +
                "    \"cell\" : 99\n" +
                "}";

        String result = given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();

        Assertions.assertEquals(result, "В игре нет такой ячейки");
    }

    @Test
    public void illegalGameNumberTest() {
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/multi?playerKey=" + playerOne.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        String illegalGameNumber = game.getPlayGroundKey() + "111111111111111111111";

        String result = given()
                .when()
                .put("http://localhost:9090/gameplay/rest/players?playerKey=" + playerTwo.getPlayerKey() +"&playGroundKey="+ illegalGameNumber)
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();


        Assertions.assertEquals(result, "Формат номера игры некорректен");
    }

    @Test
    public void illegalGameNumberTest2() {
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/multi?playerKey=" + playerOne.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        String illegalGameNumber = RandomName.get();

        String result = given()
                .when()
                .put("http://localhost:9090/gameplay/rest/players?playerKey=" + playerTwo.getPlayerKey() +"&playGroundKey="+ illegalGameNumber)
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();


        Assertions.assertEquals(result, "Формат номера игры некорректен");
    }

    @Test
    public void illegalGameNumberTest3() {
        PlayerEntity playerOne = Steps.createPlayer(RandomName.get());

        PlayerEntity playerTwo = Steps.createPlayer(RandomName.get());

        PlayGroundEntity game = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/multi?playerKey=" + playerOne.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Random random = new Random();
        String illegalGameNumber = random.nextLong() + "";
        while (illegalGameNumber.equals(game.getPlayGroundKey())) {
            illegalGameNumber = random.nextLong() + "";
        }

        String result = given()
                .when()
                .put("http://localhost:9090/gameplay/rest/players?playerKey=" + playerTwo.getPlayerKey() +"&playGroundKey="+ illegalGameNumber)
                .then()
                .log().body()
                .statusCode(400)
                .extract().asString();


        Assertions.assertEquals(result, "Игра не найдена");
    }
}
