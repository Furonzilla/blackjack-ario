
public abstract class Card {

	// diamonds / clubs / hearts / spades
	private String suitType;
	// 2 à 11(as)
	private Integer cardValue;
	// As 2 3 4 5 6 7 8 9 10 Jack Queen King
	private String name;
	
	/**
	 * @param suitType
	 * @param cardValue
	 * @param name
	 */
	public Card(String suitType, Integer cardValue, String name) {
		this.suitType = suitType;
		this.cardValue = cardValue;
		this.name = name;
	}

	public String getSuitType() {
		return suitType;
	}

	public Integer getCardValue() {
		return cardValue;
	}

	public String getName() {
		return name;
	}
	
	public String getNameAndSuits() {
		return name + " of " + suitType;
	}
}
