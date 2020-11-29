/*
   File: Hand.java
   Author: Caleb Bitting
   Date: 09/01/2001
   Hand Class
*/

import java.util.ArrayList;

public class Hand {
    
    // hand contents
    private ArrayList<Card> contents = new ArrayList<Card>();

    // constructor
    public Hand() {}

    // empty hand
    public void reset() {
        contents.clear();
    }

    // add a card
    public void add (Card card) {
        contents.add(card);
    }

    // size of card
    public int size () {
        return contents.size();
    }

    // get value of a card given an index
    public Card getCard (int i) {
        return contents.get(i);
    }

    // returns total value of hand
    public int getTotalValue () {
        ArrayList<Integer> cardValues = new ArrayList<Integer>();
        int total = 0;

        // get values of every card in hand
        for (Card card : contents) {
        	cardValues.add(card.getValue());
        }
        
        for (int value : cardValues) {
            total += value;
        }
        
        // check to see if changing an 11 to a 1 would be beneficial
        if (total > 21 && cardValues.contains(11)) {
        	total -= 10;
        }
        
        return total;

    }

    // nicer printing
    public String toString() {

        String toReturn = "";

        for (Card card : contents) {

            toReturn += card.toString();
            toReturn += " ";

        }

        return toReturn;

    }

    public static void main(String[] args) {

        Hand test = new Hand();
        Card small = new Card(3);
        Card large = new Card(10);
        test.add(small);
        test.add(large);
        System.out.println(test.size());
        System.out.println(test.getCard(0));
        System.out.println(test.getTotalValue());
        System.out.println(test);
        test.reset();
        System.out.println(test);

    }

}