import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DeckTest {

	// clear (size zero)
	
	Deck deck;
	
	@BeforeEach
	void init() {
		deck = new Deck();
	}
	
	@Test
	void constructorTest() {
		boolean results[] = new boolean[4];
		// check deck size
		results[0] = deck.size() == 52;
		// make sure correct number of correct cards
		int twoCounter = 0;
		int tenCounter = 0;
		for (Card card : deck.getDeck()) {
			if (card.getValue() == 2) {
				twoCounter++;
			} else if (card.getValue() == 10) {
				tenCounter++;
			}
		}
		results[1] = twoCounter == 4;
		results[2] = tenCounter == 16;
		// right number of decks
		results[3] = deck.getDeckNums() == 1;
		boolean[] expected = new boolean[] {true, true, true, true};
		assertArrayEquals(expected, results);
	}
	
	@Test
	void advancedConstructorTest() {
		Deck bigDeck = new Deck(104);
		boolean results[] = new boolean[4];
		// right number of cards
		results[0] = bigDeck.size() == 104;
		// check that tens and twos (representative case) are present in correct amounts
		int twoCounter = 0;
		int tenCounter = 0;
		for (Card card : bigDeck.getDeck()) {
			if (card.getValue() == 2) {
				twoCounter++;
			} else if (card.getValue() == 10) {
				tenCounter++;
			}
		}
		results[1] = twoCounter == 8;
		results[2] = tenCounter == 32;
		results[3] = bigDeck.getDeckNums() == 2;
		boolean[] expected = new boolean[] {true, true, true, true};
		assertArrayEquals(expected, results);
	}
	
	@Test
	void constructorFailTest() {
		assertThrows(Error.class, () -> new Deck(100));
	}
	
	@Test
	void dealTest() {
		deck.deal();
		assertNotEquals(deck.size(), 52);
	}
	
	@Test
	void shuffleTest() {
		ArrayList<Card> pre = deck.getDeck();
		deck.shuffle();
		ArrayList<Card> shuffled = deck.getDeck();
		assertFalse(pre.equals(shuffled));
	}
	
	@Test
	void clearTest() {
		deck.clear();
		assertEquals(deck.size(), 0);
	}

}
