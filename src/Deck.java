/*
   File: Deck.java
   Author: Caleb Bitting
   Date: 09/01/2001
   Deck Class
*/

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    // internal attributes    
    private ArrayList<Card> deck = new ArrayList<Card>();
    private int deckNums; 

    // constructors
    public Deck() {
    	deckNums = 1;
        this.build();
    }
    
    public Deck(int cards) {
    	if (cards%52 != 0) {
    		throw new Error("Not an even multiple of 52.");
    	}
    	deckNums = (cards/52);
    	this.build();
    }
    
    public ArrayList<Card> getDeck() {
    	return deck;
    }
    
    public int getDeckNums() {
    	return deckNums;
    }

    // add 4 cards for 2 - 11 with the exception of 10 (10 gets 16 copies)
    // numbers above multiplied by number of decks to supply
    public void build() {
        for (int i = 2; i <= 11; i++) {
            if (i != 10) {
                for (int j = 0; j < 4*deckNums; j++) {
                    deck.add(new Card(i));
                }
            } else {
                for (int j = 0; j < 16*deckNums; j++) {
                    deck.add(new Card(i));
                }
            }
        }
    }

    // cards remaining in deck
    public int size() {
        return deck.size();
    }

    // deal the top card -- remove it from the deck
    public Card deal() {
        Card drawn = deck.remove(0);
        return drawn;
    }

    // select a card based on an index
    public Card pick(int i) {
        Card picked = deck.remove(i);
        return picked;
    }

    // mix up the order
    public void shuffle() {

        int size = deck.size();
        Random ran = new Random();
        ArrayList<Card> shuffled = new ArrayList<Card>();
        
        for (int i = 0; i < size; i++) {
            int rand = ran.nextInt(deck.size()); //index of value to be removed
            Card removed = deck.remove(rand); //removed value
            shuffled.add(removed);   
        }
        deck = shuffled;
    }
    
    public void clear() {
    	deck.clear();
    }

    // pretty printing
    public String toString() {

        String toReturn = "[";

        for (Card card : deck) {

            toReturn += card.toString();
            toReturn += ", ";

        }

        toReturn = toReturn.substring(0, toReturn.length() - 2);
        toReturn += "]";

        return toReturn;

    }

    public static void main(String[] args) {

        Deck test = new Deck();
        System.out.println(test);
        test.shuffle();
        System.out.println(test);

    }

}