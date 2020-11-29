import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BlackjackTest {

	Blackjack blackjack;
		
	@BeforeEach
	void init() {
		blackjack = new Blackjack(20);
		blackjack.deal();
	}
	
	@Test
	void dealTest() {
		int expected = 48; 
		assertEquals(expected, blackjack.getDeck().size());
	}
	
	@Test
	void resetTest() {
		while (blackjack.getDeck().size() > 19) {
			blackjack.deal();
		}
		blackjack.reset();
		int[] results = new int[3];
		results[0] = blackjack.getPlayerHand().size();
		results[1] = blackjack.getDealerHand().size();
		results[2] = blackjack.getDeck().size();
		int[] expected = new int[] {0, 0, 52}; 
		assertArrayEquals(expected, results);
	}
	
	@Test
	void hitTest() {
		Hand preHand = blackjack.getPlayerHand();
		int pre = preHand.getTotalValue();
		blackjack.hit(blackjack.getPlayerHand());
		Hand postHand = blackjack.getPlayerHand();
		int diff = postHand.getTotalValue() - pre;
		assertNotEquals(diff, 0);
	}
	
	@Test
	void turnHitTest() {
		Hand hand = new Hand();
		Card card = new Card(11);
		hand.add(card);
		blackjack.turn(hand, 16);
		assertNotEquals(hand.getTotalValue(), 11);
	}
	
	@Test
	void turnStandTest() {
		Hand hand = new Hand();
		Card card = new Card(10);
		hand.add(card);
		hand.add(card);
		blackjack.turn(hand, 16);
		assertEquals(hand.getTotalValue(), 20);
	}
	
	@Test
	void bustTest() {
		Hand hand = new Hand();
		Card card = new Card(11);
		hand.add(card);
		hand.add(card);
		hand.add(card);
		int result = blackjack.checkBust(hand);
		assertEquals(result, -1);
	}
	
	@Test
	void notBustTest() {
		Hand hand = new Hand();
		Card card = new Card(10);
		hand.add(card);
		hand.add(card);
		int result = blackjack.checkBust(hand);
		assertEquals(result, 1);
	}

}
