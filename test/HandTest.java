import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HandTest {
	
	Hand hand;
	
	@BeforeEach
	void init() {
		hand = new Hand();
		Card small = new Card(2);
		Card big = new Card(11);
		hand.add(small);
		hand.add(big);
	}
	
	@Test
	void sizeTest() {
		assertEquals(hand.size(), 2);
	}
	
	@Test
	void valueTest() {
		int[] expected = new int[] {2, 13};
		int[] results = new int[2];
		results[0] = hand.getCard(0).getValue();
		results[1] = hand.getTotalValue();
		assertArrayEquals(expected, results);

	}
	
	@Test
	void resetTest() {
		hand.reset();
		assertEquals(hand.size(), 0);
	}
	
}
