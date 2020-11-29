/*
   File: Blackjack.java
   Author: Caleb Bitting
   Date: 09/01/2001
   Blackjack Game Class
*/

public class Blackjack {
	
	private Deck deck;
	private Hand playerHand, dealerHand;
	private int reShuffleCutoff;
	private int blackjackCounter = 0;
	
	// constructors
	public Blackjack() {
		deck = new Deck();
		playerHand = new Hand();
		dealerHand = new Hand();
		setReShuffleCutoff(-1);
	}
	
	public Blackjack(int cutoff){
		deck = new Deck();
		deck.shuffle();
		playerHand = new Hand();
		dealerHand = new Hand();
		this.reset();
		setReShuffleCutoff(cutoff);
	}
	
	public Blackjack(int cutoff, int cards) {
		deck = new Deck(cards);
		deck.shuffle();
		playerHand = new Hand();
		dealerHand = new Hand();
		this.reset();
		setReShuffleCutoff(cutoff);
	}
	
	// accessor methods
	public int getBlackjackCounter() {
		return blackjackCounter;
	}
	
	public int getReShuffleCutoff() {
		return reShuffleCutoff;
	}
	
	public Deck getDeck() {
		return deck;
	}
	
	public Hand getPlayerHand() {
		return playerHand;
	}
	
	public Hand getDealerHand() {
		return dealerHand;
	}

	// setter method
	public void setReShuffleCutoff(int reShuffleCutoff) {
		this.reShuffleCutoff = reShuffleCutoff;
	}

	// empty hands and create new deck if not enough cards
	public void reset() {
		playerHand.reset();
		dealerHand.reset();
		if (deck.size() < reShuffleCutoff) {
		deck.clear();
		deck.build();
		deck.shuffle();
		}
	}
	
	// deal two cards to each hand
	public void deal() {
		playerHand.add(deck.deal());
		playerHand.add(deck.deal());
		dealerHand.add(deck.deal());
		dealerHand.add(deck.deal());
	}
	
	// add one card to the passed hand
	public void hit(Hand hand) {
		hand.add(deck.deal());
	}
	
	// hit until the stand cutoff is reached
	public void turn(Hand hand, int standCutoff) {
		int currentValue = hand.getTotalValue();
		if (currentValue < standCutoff) {
			this.hit(hand);
			this.turn(hand, standCutoff);
		}
	}
	
	// did the hand bust
	public int checkBust(Hand handToCheck) {
		if (handToCheck.getTotalValue() > 21) {
			return -1;
		} else {
			return 1;
		}
	}
	
	//check for wins
	public int winner(boolean verbose) {
		Hand playerHand = this.getPlayerHand();
		Hand dealerHand = this.getDealerHand();
		// deal with Blackjacks
		if (playerHand.getTotalValue() == 21 && dealerHand.getTotalValue() == 21) {
			if (playerHand.size() == 2 && dealerHand.size() == 2) {
				this.blackjackCounter += 2;
				return 0; // both players have a natural
			} else if (playerHand.size() == 2) {
				this.blackjackCounter++;
				return 1; // only player has a natural
			} else if (dealerHand.size() == 2) {
				this.blackjackCounter++;
				return -1; // only dealer has a natural
			} else {
				return 0; // nobody has a natural, but both have 21
			}
		}
		// non blackjacks
		if (this.playerHand.getTotalValue() > this.dealerHand.getTotalValue()) {
			if (verbose == true) {
				System.out.println(this);
				System.out.println("Player wins.");
			}
			return 1;
		} else if (this.playerHand.getTotalValue() < this.dealerHand.getTotalValue()) {
			if (verbose == true) {
				System.out.println(this);
				System.out.println("Dealer wins.");
			}
			return -1;
		} else {
			if (verbose == true) {
				System.out.println(this);
				System.out.println("Push.");
			}
			return 0;
		}
	}
	
	// pretty printing
	public String toString() {		
		String playerHand = this.playerHand.toString() + "| " + Integer.toString(this.playerHand.getTotalValue());
		String dealerHand = this.dealerHand.toString() + "| " + Integer.toString(this.dealerHand.getTotalValue());
		String toReturn = "Player: " + playerHand;
		toReturn += "\nDealer: ";
		toReturn += dealerHand;
		
		return toReturn;	
	}
	
	// simulate a game
	public int game(boolean verbose, Blackjack blackjack) {
		this.reset();
		blackjack.deal();
		if (verbose == true) {
			System.out.println(this);
		}
		blackjack.turn(this.playerHand, 16);
		if (blackjack.checkBust(blackjack.playerHand) == -1) {
			if (verbose == true) {
				System.out.println(this);
				System.out.println("Dealer wins. Bust");
			}
			return -1;
		}
		blackjack.turn(this.dealerHand, 17);
		if (blackjack.checkBust(blackjack.dealerHand) == -1) {
			if (verbose == true) {
				System.out.println(this);
				System.out.println("Player wins. Bust");
			}
			return 1;
		}
		
		int winner = this.winner(verbose);
		
		return winner;
	}

	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack(52);
		boolean thing = true;
		for (int i = 0; i < 3; i++) {
			int result = blackjack.game(thing, blackjack);
			System.out.println(result);
		}
	}
}
