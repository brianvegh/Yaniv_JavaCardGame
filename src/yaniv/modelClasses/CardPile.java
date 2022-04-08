package yaniv.modelClasses;
// ************************************************************
// Title: Yaniv Brian Vegh Semester Project
// File: CardPile.java
// Author: Brian Vegh
// Description: Abstract Class
// ***********************************************************
abstract class CardPile {
    public abstract void clear();

    public abstract void addCard(Card c);

    public abstract Card removeCard(Card c);

    public abstract Card getCard(int position);
}
