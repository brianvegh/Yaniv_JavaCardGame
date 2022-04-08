package yaniv.modelClasses;
// ************************************************************
// Title: yaniv.modelClasses.Player
// File: yaniv.modelClasses.Player.java
// Author: Brian Vegh
// Description: Class file for yaniv.modelClasses.Player object
// ************************************************************


import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private static int playerCount =-1;
    private final int playerNumber;
    public final boolean isNPC;
    private String playerName;
    public Discard discard;
    private Hand hand;


    private ArrayList<Button> handButtons;
    private ArrayList<Label> discardLabels;

    public Player() {//creates Player Object
        playerCount++;
        this.playerNumber=playerCount;
        this.playerName="NPC "+this.playerNumber;
        this.isNPC=true;
        hand = new Hand();
        discard = new Discard();
    }
    public Player(String name) {//overloaded constructor
        this.playerName =name;
        if (this.playerName != "currentPlayer") {
            playerCount++;
        }
        this.playerNumber = playerCount;
        this.isNPC = false;
        hand = new Hand();
        discard = new Discard();
        ///////////////////////////////////////handButtons.a
    }
    public static int getPlayerCount() {
        return playerCount;
    }

    public void printHand(char byValueOrBySuit) {
        Hand hand = getHand();
          if (byValueOrBySuit == 'v' || byValueOrBySuit == 'V') {///prints by value
            System.out.println("Player "+getPlayerName()+" Hand by Value: ");
            hand.sortByValue();
            for (int i = 0; i < hand.size() - 1; i++) {
                System.out.print(hand.getCard(i).toStringBasic() + ", ");
            }
            System.out.print(hand.getCard(hand.size() - 1).toStringBasic() + ".\n");
        } else if((byValueOrBySuit == 's' || byValueOrBySuit == 'S')) {////prints by suit
            System.out.println("Player "+getPlayerName()+" Hand by Suit: ");
            hand.sortBySuit();
            for (int i = 0; i < hand.size() - 1; i++) {
                System.out.print(hand.getCard(i).toStringBasic() + ", ");
            }
            System.out.print(hand.getCard(hand.size() - 1).toStringBasic() +
                    ".\n");
        } else {                                                         //////prints by suit & value
            System.out.println("Player "+getPlayerName()+" Hand by Value: ");
            hand.sortByValue();
            for (int i = 0; i < hand.size() - 1; i++) {
                System.out.print(hand.getCard(i).toStringBasic() + ", ");
            }
            System.out.print(hand.getCard(hand.size() - 1).toStringBasic() + ".\n");
            System.out.println("Player "+getPlayerName()+" Hand by Suit: ");
            hand.sortBySuit();
            for (int i = 0; i < hand.size() - 1; i++) {
                System.out.print(hand.getCard(i).toStringBasic() + ", ");
            }
            System.out.print(hand.getCard(hand.size() - 1).toStringBasic() +
                    ".\n");
        }
    }
    public void printDiscard() {
        List discard = new ArrayList<Card>(this.discard.whatIsLastDeposit());
        if (discard.size()>0) {
            System.out.println("Player "+getPlayerName()+" Discard: ");
            for (int i = 0; i < discard.size() - 1; i++) {

                System.out.print(((Card) discard.get(i)).toStringBasic() + ", ");
            }
        }else{
            System.out.println("Player "+getPlayerName()+" Discard IS EMPTY!");
        }
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public Hand getHand() {
        return hand;
    }

    public Discard getDiscard() {
        return discard;
    }

    public void setPlayerName(String playerName){
        this.playerName=playerName;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void discardCards(int position){
        Card c = hand.getCard(position);
        discard.addCard(c);
        hand.removeCard(position);
        //renderDiscardPile();  ///will draw labels of discard pile onto Gui
        //renderHumanHand
    }
    public void discardCards(Card cardToDiscard){
        discard.addCard(cardToDiscard);
        hand.removeCard(cardToDiscard);

    }

    public void drawCard(Card c){
        hand.addCard(c);

    }
    public String toString() {
        String playerString = "";
        playerString+="Player Name - "+getPlayerName()+". ";
        playerString+="Number - "+playerNumber+". ";
        String npcOrHuman = (isNPC? "isNPC.":"isHuman.");
        playerString +=npcOrHuman;
        return playerString;
    }




}
