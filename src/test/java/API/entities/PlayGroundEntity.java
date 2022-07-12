package API.entities;

import API.exeptions.*;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayGroundEntity {

    private Long id;

    private Long playGroundKey;
    private char[] content;
    private int stepNo;
    private Long[] playerIDs;
    private String[] stringsNum;
    private boolean thisPlayerAI;
    private int maxPlayers;
    private int side;
    private int currentPlayersID;
    private boolean nextPlayerAI;
    private String[] strings;

    private static final char[] signs = {'x', '*', '@', '#', '$', '&'};

    public PlayGroundEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPlayGroundKey() {
        return playGroundKey;
    }

    public void setPlayGroundKey(Long playGroundKey) {
        this.playGroundKey = playGroundKey;
    }

    public char[] getContent() {
        return content;
    }

    public void setContent(char[] content) {
        this.content = content;
    }

    public int getStepNo() {
        return stepNo;
    }

    public void setStepNo(int stepNo) {
        this.stepNo = stepNo;
    }

    public Long[] getPlayerIDs() {
        return playerIDs;
    }

    public void setPlayerIDs(Long[] playerIDs) {
        this.playerIDs = playerIDs;
    }

    public static char[] getSigns() {
        return signs;
    }

    public String[] getStringsNum() {
        return stringsNum;
    }

    public void setStringsNum(String[] stringsNum) {
        this.stringsNum = stringsNum;
    }

    public boolean isThisPlayerAI() {
        return thisPlayerAI;
    }

    public void setThisPlayerAI(boolean thisPlayerAI) {
        this.thisPlayerAI = thisPlayerAI;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int side) {
        this.side = side;
    }

    public int getCurrentPlayersID() {
        return currentPlayersID;
    }

    public void setCurrentPlayersID(int currentPlayersID) {
        this.currentPlayersID = currentPlayersID;
    }

    public boolean isNextPlayerAI() {
        return nextPlayerAI;
    }

    public void setNextPlayerAI(boolean nextPlayerAI) {
        this.nextPlayerAI = nextPlayerAI;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    @Override
    public String toString() {
        return "PlayGroundEntity{" +
                "id=" + id +
                ", playGroundKey=" + playGroundKey +
                ", content=" + Arrays.toString(content) +
                ", stepNo=" + stepNo +
                ", playerIDs=" + Arrays.toString(playerIDs) +
                '}';
    }
}
