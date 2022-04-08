package yaniv.modelClasses;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: Round.java
// Author: Brian Vegh
// Description: Round object file
// ***********************************************************

import yaniv.controller.GameD;
import yaniv.controller.GameSceneController;
import yaniv.controller.Images;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static yaniv.controller.GameD.round;
import static yaniv.controller.OpponentLogic.opponentLogic;

public class Round {

    private static Player currentPlayer;
    private static Discard currentPlayerDiscardDrawPile;
    private static final int INITIAL_DEAL_QUANTITY = 7;
    private static boolean humanCallYanivIsLegal;
    private static boolean humanDiscardIsLegal;
    private static boolean humanDrawIsLegal;
    private static String DEFAULT_BACK_OF_CARD_FILENAME = "sriracha";

    public static Card backOfCard;
    public static int humanCardToDraw = -1;

    private final int ROUND_PLAYERS_COUNT;
    public static int[] playerScores;
    public static Player[] players;
    public static Deck deck;
    private static int ROUND_NUMBER;
    private static int turnRevolutionsThisGame;
    private static int roundTurnCount=0;
    private static int currentTurn;
    public static boolean roundOver=false;

    public Round(String PLAYER_NAME,int playerCount,int roundNumber){
        Round.players = new Player[playerCount];
        players[0]=new Player(PLAYER_NAME);
        for (int i=1;i<playerCount;i++){
            Round.players[i]=new Player();
        }
        currentPlayer= new Player("currentPlayer");
        currentTurn = 0;
        setCurrentPlayer(currentTurn);
        ROUND_PLAYERS_COUNT = players.length;
        Round.playerScores=new int[players.length];
        ROUND_NUMBER=roundNumber;
        turnRevolutionsThisGame = 0;
        deck = new Deck();
        backOfCard=new Card(DEFAULT_BACK_OF_CARD_FILENAME);
        deck.shuffle();
        deal();
        players[0].getHand().sortBySuit();
        players[0].getHand().sortByValue();
        humanCallYanivIsLegal=true;
        humanDiscardIsLegal=true;
        humanDrawIsLegal=false;
        Images.setActionText("Choose cards to discard.");
        Images.renderNodes();
    }

    private void setScores() {
        for (int i=0;i<ROUND_PLAYERS_COUNT;i++){
            playerScores[i]=players[i].getHand().getHandValue();
        }
    }
    public static int[] getScores() {
        return playerScores;
    }

    public static void nextTurn() {
        roundTurnCount++;
        checkIfNeedShuffleDeck();
        currentPlayer.getHand().sortByValue();
        if (currentTurn == players.length - 1) {
            currentTurn = 0;
            turnRevolutionsThisGame += 1;
        } else {
            currentTurn++;
        }
        setCurrentPlayer(currentTurn);
        for (Card c : currentPlayer.getHand().getHand()) {
            c.isSelected = false;
        }
        ///////////////
        printCurrentPlayer();
        ///////////////
        if (turnRevolutionsThisGame > 0) {
            currentPlayer.discard.resetQuantityOfLastDeposit();
        }
        if (!currentPlayer.isNPC) {
            currentPlayer.getHand().sortByValue();
            Images.renderHumanHand();
            Images.setActionText("Select Cards to Discard");
            setIsItHumansTurn();
            setHumanCallYanivIsLegal(true);
            setHumanDiscardIsLegal(true);
        } else {
            opponentAction(opponentLogic(currentPlayer.getHand(), currentPlayerDiscardDrawPile));
            Images.renderNodes();
            nextTurn();
        }
    }
    private static void checkIfNeedShuffleDeck() {
        if (deck.cardsLeft() == 0) {
            GameSceneController.displayMessage("For Your Information....", "The deck has been shuffled!");
            ArrayList<Card> cards = new ArrayList<Card>();
            for (Player p : players) {
                cards.addAll(Arrays.asList(p.discard.reShuffle()));
            }
            Card[] forNewDeck = new Card[cards.size()];
            for (int i=0;i<cards.size();i++){
                forNewDeck[i]=cards.get(i);
            }
            deck = new Deck(forNewDeck);
            Images.renderNodes();
        }
    }

    private static void printCurrentPlayer() {
        System.out.println("----------------------------------");
        System.out.println(currentPlayer.toString());
        currentPlayer.printHand('b');
        currentPlayer.printDiscard();
    }

    private static void opponentAction(ArrayList[] choices) {
        ArrayList discardCards = (ArrayList) choices[0].clone();
        ArrayList drawCardArray = (ArrayList) choices[1].clone();
        Card drawCard;
        if (drawCardArray.size() == 0) {
            drawCard = deck.dealCard();
        } else {
            drawCard = (Card) drawCardArray.get(0);
            currentPlayerDiscardDrawPile.removeCard(drawCard);
        }
        System.out.println("discard choice " + discardCards.toString());
        System.out.println("drawCard " + drawCard.toString());

        for (Object c : discardCards) {
            currentPlayer.discardCards((Card) c);
        }
        currentPlayer.drawCard(drawCard);
    }

    public static void setCurrentPlayer(int playerNumber) {
       currentPlayer = players[playerNumber];
        if (currentPlayer.getPlayerNumber() == 1) {
            currentPlayerDiscardDrawPile = players[players.length - 1].discard;
        } else currentPlayerDiscardDrawPile = players[currentPlayer.getPlayerNumber() - 2].discard;
    }

    public static void humanDiscardAction() {
        boolean change = false;
        System.out.println("discard clicked");
        if (getHumanDiscardIsLegal()) {
            ArrayList <Card> cardsToDiscard = new ArrayList <>();
            int initialHandSize = currentPlayer.getHand().size();
            for (int i = 0; i < initialHandSize; i++) {
                if (currentPlayer.getHand().getHand().get(i).isSelected) {
                    cardsToDiscard.add(currentPlayer.getHand().getHand().get(i));
                    change = true;
                }
            }
            if (checkIfSelectionLegal(cardsToDiscard)) {
                /// TO DO
                for (Card c : cardsToDiscard) {
                    currentPlayer.discardCards(c);
                }
                if (change) {
                    Images.deSelectAllButtons();
                    Images.setActionText("Choose a card to draw.");
                    Images.renderNodes();
                    setHumanCallYanivIsLegal(false);
                    setHumanDiscardIsLegal(false);
                    setHumanDrawIsLegal(true);
                }
            } else {
                GameSceneController.displayMessage("Alert!",
                        "                Please make a valid discard selection.\n" +
                                "        This includes sets of similar card types of two or more,\n" +
                                "                (multiple cards with the same value)\n" +
                                "                   or straights of three or more.\n" +
                                "            (cards of similar suit in consecutive order)");
            }
        } else {
            GameSceneController.displayMessage("Alert!", Images.getActionText());
        }
    }

    private static boolean checkIfSelectionLegal(ArrayList <Card> cardsToDiscard) {
        boolean setValid = true;
        boolean straightValid = true;
        if (cardsToDiscard.size()>0){
            int firstSuit = cardsToDiscard.get(0).getSuit();
            int firstCardValue = 0;
            for (int i = 0; i < cardsToDiscard.size(); i++) {
                Card c = cardsToDiscard.get(i);
                if (c.getSuit() != firstSuit) {
                    straightValid = false;
                }
                if (i == 0) {
                    firstCardValue = c.getValue();
                } else {
                    if (c.getValue() != firstCardValue + i) {
                        straightValid = false;
                    }
                    if (c.getValue() != firstCardValue) {
                        setValid = false;
                    }
                }
            }
        }
        else {
            setValid=false;
            straightValid=false;
        }
        return (setValid || straightValid);
    }

    public static void deal() {
        if (deck.cardsLeft() >= (INITIAL_DEAL_QUANTITY * Player.getPlayerCount())) {
            for (int i = 0; i < INITIAL_DEAL_QUANTITY; i++) {
                for (Player j : players) {
                    j.getHand().addCard(deck.dealCard());
                }
            }
            System.out.println("Cards left: " + deck.cardsLeft());
        } else System.out.println("not enough cards");
    }

    public static Card[] getHumanPlayersDiscardDrawOptions() {
        Card[] humanPlayersDiscardDrawOptions = new Card[2];
        if (players[players.length - 1].discard.whatIsLastDeposit() != null) {
            Card[] temp = players[players.length - 1].discard.whatIsLastDeposit().toArray(new Card[0]);
            humanPlayersDiscardDrawOptions[0] = temp[0];
            if (temp.length > 1) {
                humanPlayersDiscardDrawOptions[1] = temp[temp.length - 1];
            }
        }
        return humanPlayersDiscardDrawOptions;
    }
    public static void callYaniv() {
        Boolean win = true;
        Integer[] scores = new Integer[players.length];
        for (int i = 0; i < scores.length; i++) {
            scores[i] = players[i].getHand().getHandValue();
        }
        for (Player p : players) {
            if (p.getPlayerNumber() != currentPlayer.getPlayerNumber() &&
                    p.getHand().getHandValue() <= currentPlayer.getHand().getHandValue()) {
                win = false;
            }
        }
        if (win) {
            scores[currentPlayer.getPlayerNumber()-1]=0;
        } else {
            scores[currentPlayer.getPlayerNumber()-1]+=30;
            if (currentPlayer.getPlayerNumber() == 1) {
                GameSceneController.displayMessage("HAHAHA LOSER!", "YOU LOSE!\nA new round will begin.");
            } else {
                GameSceneController.displayMessage("ASAF FOOL!", "Player " + currentPlayer.getPlayerName() +
                        " looses! You win by default.\nA new round will begin.");
            }
        }
        GameD.roundOver(scores);
    }
    public static void humanDrawAction() {
        if (getHumanDrawIsLegal()) {
            switch (humanCardToDraw) {
                case -1:
                    GameSceneController.displayMessage("Alert", "Choose a card to pick up!");
                case 0:
                    currentPlayer.getHand().addCard(deck.dealCard());
                    break;
                case 1:
                    currentPlayer.getHand().addCard(currentPlayerDiscardDrawPile.removeCard(0));
                    break;
                case 2:
                    currentPlayer.getHand().addCard(currentPlayerDiscardDrawPile.removeCard(1));
                    break;
            }
            Images.renderNodes();
            if (humanCardToDraw != -1) {
                humanCardToDraw = -1;
                setHumanDrawIsLegal(false);
                setIsItHumansTurn();
                nextTurn();
            }
        } else {
            GameSceneController.displayMessage("Alert", "Please discard first.");
        }
    }

    public static int getNumberRemainingPlayers() {
        return players.length;
    }

    public static Player getPlayer(int playerNumber) {
        return players[playerNumber];
    }

    public static int getHumanPlayerScore() {
        return players[0].getHand().getHandValue();
    }

    public static Card getBackOfCard() {
        return backOfCard;
    }

    public static boolean getHumanCallYanivIsLegal() {
        return humanCallYanivIsLegal;
    }

    private static void setHumanCallYanivIsLegal(boolean legal) {
        humanCallYanivIsLegal = legal;
    }

    private static void setIsItHumansTurn() {
    }

    private static boolean getHumanDrawIsLegal() {
        return humanDrawIsLegal;
    }

    public static void setHumanCardToDraw(int humanCardToDraw) {
        round.humanCardToDraw = humanCardToDraw;
    }

    private static void setHumanDrawIsLegal(boolean humanDrawIsLegal) {
        Round.humanDrawIsLegal = humanDrawIsLegal;
    }

    private static boolean getHumanDiscardIsLegal() {
        return humanDiscardIsLegal;
    }

    private static void setHumanDiscardIsLegal(boolean humanDiscardIsLegal) {
        humanDiscardIsLegal = humanDiscardIsLegal;
    }

    public static Discard getCurrentPlayerDiscardDrawPile() {
        return currentPlayerDiscardDrawPile;
    }
}
