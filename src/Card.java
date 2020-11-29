/*
   File: Card.java
   Author: Caleb Bitting
   Date: 09/01/2001
   Card Class
*/


public class Card {

    // set bounds
    private int lower = 1;
    private int upper = 11;

    // create value variable
    private int value;

    // constructors
    public Card() {
        value = 0;
    }

    public Card(int v) {
        // check to make sure the passed value is within the correct range
        if (v < lower | v > upper) {
            throw new Error("Card cannot have a value as passed.");
        } else { 
            value = v;
        }
    }

    // return card value
    public int getValue() {
        return value;
    }

    // pretty printing
    public String toString() {
        return Integer.toString(value);
    }

}