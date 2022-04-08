package yaniv.modelClasses;
// ************************************************************
// Title: yaniv.modelClasses.Discard
// File: yaniv.modelClasses.Discard.java
// Author: Brian Vegh
// Description: Class file for yaniv.modelClasses.Discard object
// ************************************************************

import java.util.*;

public class Discard extends CardPile{
    private List<Card> discard; //cards in hand
    private int quantityOfLastDeposit=0;

    public Discard() {
        discard = new ArrayList<Card>();
    }

    @Override
    public void clear() {
        discard.clear();
        quantityOfLastDeposit=0;
    }
    public Card[] reShuffle() {
        Card[] all = new Card[discard.size()];
        for (int i=0;i<discard.size();i++){
            all[i]=discard.get(i);
        }
        clear();
        return all;
    }
    @Override
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Cant add a null card to discard.");
        discard.add(c);
        quantityOfLastDeposit++;
    }
    @Override
    public Card removeCard(Card getThisCard) {
        discard.remove(getThisCard);
        quantityOfLastDeposit--;
        return getThisCard;
    }

    public Card removeCard(int insideOrOutside) {
        Card c = null;
        int OUTSIDE = 1;
        int INSIDE = 0;
        if (insideOrOutside == INSIDE) {
            c= discard.get(discard.size()-whatIsLastDeposit().size());
            discard.remove(discard.size()-whatIsLastDeposit().size());
        } else if (insideOrOutside == OUTSIDE) {
            c= discard.get(discard.size()-1);
            discard.remove(discard.size()-1);
        }
        quantityOfLastDeposit--;
        return c;
    }
    @Override
    public Card getCard(int position) {
        if (position < 0 || position >= discard.size())
            throw new IllegalArgumentException("Position does not exist in discard: "
                    + position);
        return discard.get(position);
    }
    public Collection <? extends Card> whatIsLastDeposit(){
        Card[] lastDeposit = new Card[quantityOfLastDeposit];
        int loopCount = 0;
        for (int i=quantityOfLastDeposit; i>0; i--) {
            lastDeposit[loopCount]=discard.get(discard.size()-quantityOfLastDeposit+loopCount);
            loopCount++;
        }
        return Arrays.asList(lastDeposit);
    }
    public void resetQuantityOfLastDeposit(){
        this.quantityOfLastDeposit=0;
    }

}
