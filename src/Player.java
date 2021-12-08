import java.util.ArrayList;

/**
 * 
 */

/**
 * @author ario
 *
 */
public class Player {
	private String name;
	private ArrayList<Card> hand;
	private boolean lost;

	/**
	 * @param name
	 */
	public Player(String name) {
		super();
		this.name = name;
		this.hand = new ArrayList<>();
		this.lost = false;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void drawCard(ArrayList<Card> deck) {
		this.getHand().add(deck.get(0));
		deck.remove(0);
		this.checkLost();
	}

	public void giveBackCardToDeck(ArrayList<Card> deck) {
		for (Card card : hand) {
			deck.add(card);
		}
		;
		hand.clear();
	}

	public String getName() {
		return name;
	}

	public Integer getValueOfHand() {
		Integer valueOfHand = 0;
		for (Card card : hand) {
			valueOfHand += card.getCardValue();
		}	
		return valueOfHand;
	}
	
	public void checkLost() {
		if (this.getValueOfHand() > 21) {
			this.lost = true;
		}
		if (this.getValueOfHand() <= 21) {
			this.lost = false;
		}
	}
	
	public boolean getLost() {
		return this.lost;
	}

}
