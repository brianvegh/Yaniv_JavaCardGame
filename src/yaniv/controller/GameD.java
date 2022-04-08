package yaniv.controller;
import yaniv.modelClasses.Card;
import yaniv.modelClasses.Player;
import yaniv.modelClasses.Round;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: GameD.java
// Author: Brian Vegh
// Description: Game driver file
// ***********************************************************
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameD implements Serializable{
    private static final boolean ALLOWED = true;
    private static final boolean ELIMINATED = false;
    private static final int STARTING_NUMBER_OF_PLAYERS = 4;
    private static Player[] ALL_PLAYERS;
    private static boolean[] eliminatedPlayers;
    private static Player[] remainingPlayers;
    private static ArrayList[] playerScores;
    private static ArrayList humanScores;
    private static ArrayList npc1Scores;
    private static ArrayList npc2Scores;
    private static ArrayList npc3Scores;
    private static String PLAYER_NAME;
    public static Round round;
    private static int totalRoundsQuantity;
    Boolean gameOver;

    public static void roundOver(Integer[] scores) {
        updateScoreBoard(scores, totalRoundsQuantity);
        newRound();
    }

    public static void newRound() {
        totalRoundsQuantity++;
        //setRemainingPlayers();
        round=new Round(PLAYER_NAME,getRemainingPlayerCount(), totalRoundsQuantity);
        //gameDriver();
    }
    public static void newGame() {
        totalRoundsQuantity=1;
        PLAYER_NAME=GameSceneController.getUserTextInput("Please Enter Your Name");
        eliminatedPlayers=new boolean[]{ALLOWED,ALLOWED,ALLOWED,ALLOWED};
        ALL_PLAYERS = new Player[STARTING_NUMBER_OF_PLAYERS];
        ALL_PLAYERS[0] = new Player(PLAYER_NAME);
        for (int i = 1; i < getRemainingPlayerCount(); i++) {
            ALL_PLAYERS[i] = new Player();
        }
        round =new Round(PLAYER_NAME,ALL_PLAYERS.length,totalRoundsQuantity);

    }
    public void loadGame(){

        //loadEliminatedPlayers
        //loadScoreboard
        //loadCurrentRound
        //gameDriver();
    }
    public void saveGame(){


        //saveEliminatedPlayers
        //saveScoreboard
        //saveCurrentRound
    }
    public void changeJokerImage(){

    }
    //backOfCard filename. must be .png, which is automatically appended.;
     private static void chooseBackOfCardDesign(String newBackOfCardFilename) {
         round.backOfCard = new Card(newBackOfCardFilename);
     }

    private static void addPlayerToEliminatedList(int playerNumber){

    }

    private static void setRemainingPlayers(){
        Player[] remainingPlayers=new Player[getRemainingPlayerCount()];
        int pc=0;
        for (int i=0;i<STARTING_NUMBER_OF_PLAYERS;i++){
            if (eliminatedPlayers[i]=ALLOWED){
                remainingPlayers[pc]=ALL_PLAYERS[i];
                pc++;
            }
        }
    }
    private static int getRemainingPlayerCount() {
        int eliminatedPlayers=0;
        for (boolean b: GameD.eliminatedPlayers){
            if (b=ELIMINATED){
                eliminatedPlayers++;
            }
        }
        return eliminatedPlayers;
    }

    private static void updateScoreBoard(Integer[] scores, int totalRoundsQuantity) {
        if (totalRoundsQuantity==1) {
            playerScores = new ArrayList[4];
            humanScores = new ArrayList <Integer>();
            npc1Scores = new ArrayList <Integer>();
            npc2Scores = new ArrayList <Integer>();
            npc3Scores = new ArrayList <Integer>();
            playerScores[0]=humanScores; playerScores[1]=humanScores;
            playerScores[2]=humanScores;playerScores[3]=humanScores;

        }
        int loopCount=0;
        for(int i=0; i<STARTING_NUMBER_OF_PLAYERS;i++){
            if (eliminatedPlayers[i]=ALLOWED){
                playerScores[i].add(scores[loopCount]);
                loopCount++;
            }
        }
        Images.renderScoreboard(playerScores);
    }

    public static Round getRound() {
        return round;
    }

}
