import java.text.MessageFormat;

/*
   File: Simulation.java
   Author: Caleb Bitting
   Date: 09/01/2001
   Run simulations of blackjack games
*/

public class Simulation {
	
	private static int simulations = 1_000_000;
	
	public static void main(String[] args) {
		Blackjack blackjack = new Blackjack(20, 156);
		boolean thing = false;
		int playerWin = 0;
		int dealerWin = 0;
		int push = 0;
		for (int i = 0; i < simulations; i++) {
			int result = blackjack.game(thing, blackjack);
			if (result == 1) {
				playerWin++;
			} else if (result == 0) {
				push++;
			} else {
				dealerWin++;
			}
		}
		String play = MessageFormat.format("The player won {0} games. That is {1}%.\nThe dealer won {2} games. That is {3}%.\n{4} games resulted in a push. That is {5}%.\nThere were {6} blackjacks.", playerWin, (playerWin*100/simulations), dealerWin, (dealerWin*100/simulations), push, (push*100/simulations), blackjack.getBlackjackCounter());
		System.out.println(play);
	}
}
