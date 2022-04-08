// ************************************************************
// Title: yaniv.modelClasses.Deck
// File: yaniv.modelClasses.Deck.java
// Author: Brian Vegh
// Description: Class file for yaniv.modelClasses.Deck object
// ************************************************************
package yaniv.modelClasses;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: Deck.java
// Author: Brian Vegh
// Description: Game driver file
// ***********************************************************
public class Deck {

    private Card[] deck;
    private int cardsUsed;

    public  Deck() {//deck with no jokers
        this(false);
    }
    private Deck(boolean includeJokers) {
        if (includeJokers) {
            Card.newDeckHasJokers();
            deck = new Card[54];
        }
        else
           deck = new Card[52];
        int cardCt = 0; //number cards created so far
        for (int suit =0; suit<=3; suit++) {
            for (int value =1; value<=13; value++) {
                deck[cardCt] = new Card(value, suit);
                cardCt++;
            }
        }
        if (includeJokers) {
            deck[52] = new Card(1,Card.JOKER);
            deck[53] = new Card(2,Card.JOKER);
        }
        cardsUsed = 0;
    }
    public Deck(Card[] cards){
        deck = new Card[cards.length];
        for (int i=0; i<cards.length;i++){
            deck[i]=cards[i];
        }
        cardsUsed=0;
        shuffle();
    }

    public void shuffle() {
        for (int i = deck.length - 1; i > 0; i--) {
            int rand = (int) (Math.random() * (i + 1));
            Card temp = deck[i];
            deck[i] = deck[rand];
            deck[rand] = temp;
        }
        cardsUsed = 0;
    }
    public Card dealCard(){
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        cardsUsed++;
        return deck[cardsUsed -1];
    }
    public Card whatIsTopCard() {
        if (cardsUsed == deck.length)
            throw new IllegalStateException("No cards are left in the deck.");
        return deck[cardsUsed -1];
    }
    public int cardsLeft(){
        return deck.length - cardsUsed;
    }

    public boolean hasJokers() {
        return (deck.length == 54);
    }
}//class
