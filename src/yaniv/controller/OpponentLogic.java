package yaniv.controller;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: OpponentLogic.java
// Author: Brian Vegh
// Description: Controls opponent logic
// ***********************************************************

import yaniv.modelClasses.Card;
import yaniv.modelClasses.Discard;
import yaniv.modelClasses.Hand;
import yaniv.modelClasses.Round;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static yaniv.controller.GameD.round;


public class OpponentLogic {
    private static ArrayList<Card> handBeforeDiscard;
    private static ArrayList<Card> handAfterDiscard;
    private static ArrayList<Card> highestSingleCard;
    private static ArrayList<Card> toDraw;
    private static int averageCardValue;


    public static ArrayList[] opponentLogic (Hand currentPlayerHand, Discard currentPlayerDiscardDrawPile) {
        handAfterDiscard = new ArrayList <>();
        highestSingleCard = new ArrayList <>();
        toDraw=new ArrayList <>();

        int currentPlayerHandPointValue =0;
        for (Card c: currentPlayerHand.getHand()){
            currentPlayerHandPointValue+=c.getPointValue();
        }
        if (currentPlayerHandPointValue<=11){
            Round.callYaniv();
        }
        /*parameters for hand which cards need to be discarded from*/
        handBeforeDiscard =new ArrayList <>(currentPlayerHand.getHand());
        ArrayList <Card> discardChoice;//////discardCards arrayList
        discardChoice = new ArrayList <>(getHighestValueLegalDiscard());
        setAverageCardValue(handAfterDiscard);
        /*parameters for cards which PC will draw from after discard*/
        toDraw.addAll(currentPlayerDiscardDrawPile.whatIsLastDeposit());
        ArrayList <Card> drawChoice = getDrawChoice();
        ArrayList[] discardAndDrawChoices = new ArrayList[2];
        discardAndDrawChoices[0]= discardChoice;
        discardAndDrawChoices[1]= drawChoice;//////discardCards arrayList
        nullClassValues();
        return discardAndDrawChoices;
    }

    private static void nullClassValues() {
        handBeforeDiscard=null;
        handAfterDiscard = null;
        highestSingleCard = null;
        toDraw=null;
    }

    private static ArrayList<Card> getHighestValueLegalDiscard() {
        List<List<Card>> eligibleDrops;
        handBeforeDiscard.sort(Comparator.comparingInt(Card::getValue));
        highestSingleCard.add(handBeforeDiscard.get(handBeforeDiscard.size()-1));
        eligibleDrops = new ArrayList <>();
        eligibleDrops.add(highestSingleCard);
        //sort, and save all sets///////////////////////////////
        int highest=0;
        int location=0;
        int length=0;
        for (int i=0; i<handBeforeDiscard.size();i++) {
            Card c = handBeforeDiscard.get(i);
            if (c.getValue()>highest) {
                highest = c.getValue();
                location = i;
                length=0;
            }
            if (c.getValue() == highest) {
                length++;
            }
            if (length > 1) {
                List <Card> temp = new ArrayList <>();
                for (int j = location; j < location+length; j++) {
                    temp.add(handBeforeDiscard.get(j));
                }
                eligibleDrops.add(temp);
            }

        }
        // Sort, save all runs///////////////////////////// spades 0
        List<List<Card>> fourSuitArrays=new ArrayList <>();           /// hearts 1
        List<Card> spades=new ArrayList <>();                         /// diamonds 2
        List<Card> hearts=new ArrayList <>();                         /// clubs 3
        List<Card> diamonds=new ArrayList <>();
        List<Card> clubs=new ArrayList <>();

        fourSuitArrays.add(spades);fourSuitArrays.add(hearts);
        fourSuitArrays.add(diamonds);fourSuitArrays.add(clubs);

        for(Card c: handBeforeDiscard) {
            for (int i=0; i<4;i++){
                if (c.getSuit()==i) {
                    fourSuitArrays.get(i).add(c);
                }
            }
        }
        for (List<Card> l:fourSuitArrays) {//for each suit
            if (l.size() > 2) {
                int count = l.size();
                for (int h = l.size(); h > 1; h--) {
                    highest = l.get(count - 1).getValue();
                    location = 0;
                    length = 0;
                    for (int i = count; i > 1; i--) {
                        Card c = l.get(i - 2);
                        if (c.getValue() == highest - 1) {
                            length++;
                        }
                        if (length > 2) {
                            List <Card> temp = new ArrayList <>();
                            for (int j = 0; j < length; j++) {
                                temp.add(l.get(location + i));
                            }
                            eligibleDrops.add(temp);
                        }
                        if (c.getValue() < highest - 1) {
                            highest = c.getValue();
                            location = i;
                            length = 1;
                        }
                    }
                    count--;
                }
            }
        }
        int highestSum=0;
        ArrayList<Card> highestLegalHand=new ArrayList <>();

        for (List<Card>l:eligibleDrops){
            int currentSum=0;
            for(Card c:l){
                currentSum+=c.getPointValue();
            }
        if (currentSum>highestSum) {
            highestSum=currentSum;
            highestLegalHand= (ArrayList <Card>) l;
            }
        }
        handAfterDiscard.addAll(handBeforeDiscard);
        for (Card c:highestLegalHand){
            handAfterDiscard.remove(c);
        }

//        if (highestLegalHand.size()==0){
//            highestLegalHand.add(highestSingleCard.get(0));
//        }
        return highestLegalHand;
   }

    private static ArrayList<Card> getDrawChoice() {
        ArrayList <Card> getDrawChoice = new ArrayList <>();
        if (Round.getCurrentPlayerDiscardDrawPile().whatIsLastDeposit().size()>0) {

            Card known = (toDraw.get(0).getPointValue() < toDraw.get(toDraw.size() - 1).getPointValue() ?
                    toDraw.get(0) : toDraw.get(toDraw.size() - 1));
            if (known.getPointValue() < getAverageCardValue()) {
                getDrawChoice.add(known);
            }
            System.out.println("average card value " + getAverageCardValue());
            System.out.println("logicDrawChoice " + known);
        }
        return getDrawChoice;
    }

    private static int getAverageCardValue() {
        return averageCardValue;
    }

    private static void setAverageCardValue(List <Card> handAfterDiscard) {
        averageCardValue=Math.floorDiv(getValue(handAfterDiscard),handAfterDiscard.size());
    }
    private static int getValue(List<Card> list) {
        int value=0;
        for (Card c:list) {
            value +=c.getValue();
        }
        return value;
    }



}
