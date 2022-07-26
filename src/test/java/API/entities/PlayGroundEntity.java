package API.entities;

import API.exeptions.*;
import io.qameta.allure.internal.shadowed.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Arrays;


@JsonIgnoreProperties(ignoreUnknown = true)
@Data
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

}
