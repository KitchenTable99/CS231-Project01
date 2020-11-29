import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CardTest {
	
	Card card;

	@BeforeEach
	void init() {
		card = new Card(4);
	}
	
	@Test
	void getValueTest() {
		int expected = 4;
		assertEquals(expected, card.getValue());
	}
	
	@Test
	void emptyConstructorTest() {
		Card test = new Card();
		int expected = 0;
		assertEquals(expected, test.getValue());
	}
	
	@Test
	void lowerThrowConstructorTest() {
		assertThrows(Error.class, () -> new Card(-1));
	}
	
	@Test
	void upperThrowConstructorTest() {
		assertThrows(Error.class, () -> new Card(12));
	}
	
	@Test
	void toStringTest() {
		String expected = "4";
		assertEquals(expected, card.toString());
	}

}
