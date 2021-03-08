package model;

import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;

public class PlayingCardImpl implements PlayingCard {
	private Suit suit;
	private Value value;
	private int score;

	public PlayingCardImpl(Suit suit, Value value, int score) {
		this.suit = suit;
		this.value = value;
		this.score = score;
	}

	@Override
	public Suit getSuit() {
		
		return this.suit;
	}

	@Override
	public Value getValue() {
		return this.value;
	}

	@Override
	public int getScore() {
		return this.score;
	}

	@Override
	public boolean equals(PlayingCard card) {
		return (this.value.equals(card.getValue()) && this.suit.equals(card.getSuit()));
	}

	public boolean equals(Object card) {
		if (card instanceof PlayingCardImpl) {
			PlayingCardImpl playCard = (PlayingCardImpl) card;
			return (this.suit.equals(playCard.suit) && this.value.equals(playCard.value));
		}
		return false;
	}

	public String toString() {
		return String.format("Value: %s, Suit: %s, Score:  %s", this.value.name().charAt(0) + this.value.name().substring(1).toLowerCase(), this.suit.name().charAt(0) + this.suit.name().substring(1).toLowerCase(), this.score);
	}

}
//---------------------------------------------------
