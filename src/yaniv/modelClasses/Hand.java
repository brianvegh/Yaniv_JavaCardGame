package yaniv.modelClasses;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Hand extends CardPile {

    private List<Card> hand; //cards in hand

    public Hand(){
        hand = new ArrayList<Card>();
    }
    @Override
    public void clear(){
        hand.clear();
    }
    @Override
    public void addCard(Card c) {
        if (c == null)
            throw new NullPointerException("Cant add a null card to a hand.");
        hand.add(c);
    }
    @Override
    public Card removeCard(Card c) {
        hand.remove(c);
        return c;
    }
    public void removeCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        hand.remove(position);
    }
    public Card discard(int position) {
        Card c = new Card();
        c= hand.get(position);
        hand.remove(position);
        return c;
    }
    public int size(){
        return hand.size();
    }
    public int getCardCount() {
        return hand.size();
    }
    @Override
    public Card getCard(int position) {
        if (position < 0 || position >= hand.size())
            throw new IllegalArgumentException("Position does not exist in hand: "
                    + position);
        return hand.get(position);
    }
    public void sortBySuit() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;
            Card c = hand.get(0);
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getSuit() < c.getSuit() ||
                        (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }
    public void sortByValue() {
        ArrayList<Card> newHand = new ArrayList<Card>();
        while (hand.size() > 0) {
            int pos = 0;
            Card c = hand.get(0);
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getValue() < c.getValue() ||
                        (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }   public int getHandValue() {
        int handValue = 0;
        ListIterator <Card> cardListIteratortor = hand.listIterator();
        while (cardListIteratortor.hasNext()) {
            handValue += cardListIteratortor.next().getPointValue();
        }
        return handValue;
    }

    public List <Card> getHand() {
        return hand;
    }
}