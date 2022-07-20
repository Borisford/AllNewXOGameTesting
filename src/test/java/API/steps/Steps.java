package API.steps;

import API.entities.PlayGroundEntity;
import API.entities.PlayerEntity;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public abstract class Steps {

    public static PlayerEntity createPlayer(String name) {
         return given()
                .when()
                .post("http://localhost:9090/gameplay/rest/players?name=" + name)
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayerEntity.class);
    }

    public static PlayGroundEntity startMultiGame(PlayerEntity playerOne, PlayerEntity playerTwo) {
        PlayGroundEntity game = given()
                .when()
                .post("http://localhost:9090/gameplay/rest/start/simple/multi?playerKey=" + playerOne.getPlayerKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        game = given()
                .when()
                .put("http://localhost:9090/gameplay/rest/players?playerKey=" + playerTwo.getPlayerKey() +"&playGroundKey="+ game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);
        return game;
    }

    public static PlayGroundEntity doStep(PlayGroundEntity game, PlayerEntity player, int cell , char sign) {
        String requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+player.getId()+",\n" +
                "    \"playerKey\" : "+player.getPlayerKey()+",\n" +
                "    \"cell\" : " +cell+ "\n" +
                "}";
        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(200);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[cell], sign);
        return game;
    }

    public static PlayGroundEntity doLastStep(PlayGroundEntity game, PlayerEntity player, int cell , char sign) {
        String requestBody = "{\n" +
                "    \"playGroundId\" : "+game.getId()+",\n" +
                "    \"playGroundKey\" : "+game.getPlayGroundKey()+",\n" +
                "    \"playerId\" : "+player.getId()+",\n" +
                "    \"playerKey\" : "+player.getPlayerKey()+",\n" +
                "    \"cell\" : " +cell+ "\n" +
                "}";
        given()
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("http://localhost:9090/gameplay/rest/steps")
                .then()
                .log().body()
                .statusCode(400);

        game = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/playGrounds/" + game.getPlayGroundKey())
                .then()
                .log().body()
                .statusCode(200)
                .extract().as(PlayGroundEntity.class);

        Assert.assertEquals(game.getContent()[cell], sign);
        return game;
    }

    public static void winnerCheck(PlayGroundEntity game, PlayerEntity player) {
        String winner = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/winners/game/" + game.getId())
                .then()
                .log().body()
                .statusCode(200)
                .extract().asString();

        Assert.assertEquals(winner, player.getName());
    }

    public static void drawCheck(PlayGroundEntity game) {
        String res = given()
                .when()
                .get("http://localhost:9090/gameplay/rest/winners/game/" + game.getId())
                .then()
                .log().body()
                .statusCode(200)
                .extract().asString();

        Assert.assertEquals(res, "Draw");
    }
}
