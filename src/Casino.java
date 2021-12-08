import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author ario
 *
 */
public class Casino {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your player name :");
		String player1Name = scanner.nextLine();
		Player player1 = new Player(player1Name);
//        scanner.close();
		Player dealer = new Player("The dealer");
		boolean run = true;
		String userInput = ""; // tmp var that holds input

		while (run) {
			// do something
			playBlackjack(player1, dealer);

			System.out.println("Play Again? [Y/N]");
			userInput = scanner.next(); // Read first time

			// Run while user does not put Y || y || N || n
			while (!userInput.matches("[YyNn]")) {
				System.out.println("Incorrect input.");
				System.out.println("Play Again? [Y/N]");
				userInput = scanner.next();
			}

			// User does not want more actions
			if (userInput.matches("[Nn]")) {
				System.out.println("Do you wish to stop playing? [Y/Any other key]");
				String choice = scanner.next();

				// Stop the program
				if (choice.toLowerCase().equals("y"))
					run = false;
			}
		}
		scanner.close(); // Close scanner outside

	}

	public static void playBlackjack(Player player1, Player dealer) {
		// création d'un deck de cartes
		ArrayList<Card> deck = new ArrayList<Card>();
		// ajout des cartes de valeur 2 à 10 pour les 4 enseignes
		for (Integer i = 2; i < 11; i++) {
			deck.add(new ClubsCard(i, i.toString()));
			deck.add(new DiamondsCard(i, i.toString()));
			deck.add(new HeartsCard(i, i.toString()));
			deck.add(new SpadesCard(i, i.toString()));
		}
		// ajout des cartes As Jack Queen et King pour les 4 enseignes
		deck.add(new ClubsCard(10, "Jack"));
		deck.add(new ClubsCard(10, "Queen"));
		deck.add(new ClubsCard(10, "King"));
		deck.add(new ClubsCard(11, "As"));
		deck.add(new DiamondsCard(10, "Jack"));
		deck.add(new DiamondsCard(10, "Queen"));
		deck.add(new DiamondsCard(10, "King"));
		deck.add(new DiamondsCard(11, "As"));
		deck.add(new HeartsCard(10, "Jack"));
		deck.add(new HeartsCard(10, "Queen"));
		deck.add(new HeartsCard(10, "King"));
		deck.add(new HeartsCard(11, "As"));
		deck.add(new SpadesCard(10, "Jack"));
		deck.add(new SpadesCard(10, "Queen"));
		deck.add(new SpadesCard(10, "King"));
		deck.add(new SpadesCard(11, "As"));
		// je mélange les cartes
		Collections.shuffle(deck);
		// chaque joueur tire 2 cartes, on montre la première carte du croupier
		player1.drawCard(deck);
		player1.drawCard(deck);
		System.out.println(player1.getName() + " has drawn 2 cards.");
		System.out
				.println(player1.getName() + "'s first card is : " + player1.getHand().get(0).getNameAndSuits() + ".");
		System.out
				.println(player1.getName() + "'s second card is : " + player1.getHand().get(1).getNameAndSuits() + ".");
		System.out.println(player1.getName() + "'s hand has a value of : " + player1.getValueOfHand().toString() + ".");
		dealer.drawCard(deck);
		dealer.drawCard(deck);
		System.out.println(dealer.getName() + " has drawn 2 cards.");
		System.out.println(dealer.getName() + "'s first card is : " + dealer.getHand().get(0).getNameAndSuits() + ".");

		// le joueur tire une autre carte ou reste
		boolean run = true;
		int drawnCardByPlayer1 = 0;
		boolean dealerSecondCardIsHidden = true;
		Scanner scanner = new Scanner(System.in); // Init before loops
		String userInput = ""; // tmp var that holds input

		while (run) {
			System.out.println("(D)raw another card or (S)tay?");
			userInput = scanner.next(); // Read first time

			// Run while user does not put 1 || 2
			while (!userInput.matches("[DdSs]")) {
				System.out.println("Incorrect input.");
				System.out.println("(D)raw another card or (S)tay?");
				userInput = scanner.next();
			}

			// User does not want to draw anymore
			if (userInput.matches("[Ss]")) {
				System.out.println("You choose to stay, are you sure? [Y/Any other key]");
				String choice = scanner.next();

				// Stop the player1 turn
				if (choice.toLowerCase().equals("y")) {
					if (dealerSecondCardIsHidden) {
						System.out.println(dealer.getName() + "'s second card is : "
								+ dealer.getHand().get(1).getNameAndSuits() + ".");
						dealerSecondCardIsHidden = false;
					}
					;
					run = false;
					break;
				} else {
					continue;
				}

			}

			// draw card
			System.out.println(player1.getName() + " has drawn a " + deck.get(0).getNameAndSuits() + ".");
			player1.drawCard(deck);
			player1.checkLost();
			System.out.println(
					player1.getName() + "'s hand has a value of : " + player1.getValueOfHand().toString() + ".");
			drawnCardByPlayer1 += 1;
			if (player1.getValueOfHand() >= 21 || drawnCardByPlayer1 == 4) {
				run = false;
			}
		}
		// show dealer's second card
		if (!player1.getLost() && dealerSecondCardIsHidden) {
			System.out.println(
					dealer.getName() + "'s second card is : " + dealer.getHand().get(1).getNameAndSuits() + ".");
			System.out
					.println(dealer.getName() + "'s hand has a value of : " + dealer.getValueOfHand().toString() + ".");
			dealerSecondCardIsHidden = false;
		}
		;
//		scanner.close(); // Close scanner outside
		// tour du croupier
		if (!player1.getLost() && dealer.getValueOfHand() < 17) {
			System.out.println("It's the dealer's turn to draw cards.");
		}
		while (!player1.getLost() && !dealer.getLost() && dealer.getValueOfHand() < 17) {
			System.out.println(dealer.getName() + " has drawn a " + deck.get(0).getNameAndSuits() + ".");
			dealer.drawCard(deck);
			System.out
					.println(dealer.getName() + "'s hand has a value of : " + dealer.getValueOfHand().toString() + ".");
		}
		// showdown
		if (player1.getLost()) {
			System.out.println(dealer.getName() + " wins!");
		} else if (dealer.getLost()) {
			System.out.println(player1.getName() + " wins!");
		} else if (player1.getValueOfHand() > dealer.getValueOfHand()) {
			System.out.println(player1.getName() + " wins!");
			System.out.println("dealer : " + dealer.getValueOfHand().toString());
			System.out.println(player1.getName() + " : " + player1.getValueOfHand().toString());
		} else if (dealer.getValueOfHand() > player1.getValueOfHand()) {
			System.out.println(dealer.getName() + " wins!");
			System.out.println("dealer : " + dealer.getValueOfHand().toString());
			System.out.println(player1.getName() + " : " + player1.getValueOfHand().toString());
		}
		else {
			System.out.println("Draw!");
			System.out.println("dealer : " + dealer.getValueOfHand().toString());
			System.out.println(player1.getName() + " : " + player1.getValueOfHand().toString());
		}
		player1.giveBackCardToDeck(deck);
		dealer.giveBackCardToDeck(deck);
		Collections.shuffle(deck);
	}
}
