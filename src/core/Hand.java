package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {

	private List<Card> hand = new ArrayList<Card>();
	private Direction owner;

	public void addCard(Card card) {
		hand.add(card);
	}

	public Card getCard(int index) {
		return hand.get(index);
	}

	public Card removeCard(Card card) {
		int index = hand.indexOf(card);
		if (index < 0)
			return null;
		else
			return hand.remove(index);
	}

	public Card removeCard(int index) {
		return hand.remove(index);
	}

	public void discard() {
		hand.clear();
	}

	public int getNumberOfCards() {
		return hand.size();
	}

	public void sort() {
		Collections.sort(hand);
	}

	public boolean isEmpty() {
		return hand.isEmpty();
	}

	public boolean containsCard(Card card) {
		return this.hand.contains(card);
	}

	public int findCard(Card card) {
		return hand.indexOf(card);
	}

	public String toString() {
		return hand.toString();
	}

	public void setOwner(Direction direction) {
		owner = direction;
	}

	public boolean hasSuit(Suit suit) {
		int n = getNumberOfCards();
		for (int i = 0; i < n; i++) {
			if (this.getCard(i).getSuit() == suit)
				return true;
		}
		return false;
	}

	public Direction getOwner() {
		return owner;
	}

	public void print() {
		int i;
		int n;
		Card auxCard;
		Rank auxRank;
		Suit auxSuit;

		n = getNumberOfCards();
		for (i = 0; i < n; i++) {
			auxCard = hand.get(i);
			auxRank = auxCard.getRank();
			auxSuit = auxCard.getSuit();
			if (i != 0)
				System.out.print(" | ");
			System.out.print(auxSuit.getSymbol() + auxRank.getSymbol());
		}
		System.out.println();
	}

	public int HCP() {
		int i, n;
		int resp = 0;
		Card auxCard;
		n = getNumberOfCards();
		for (i = 0; i < n; i++) {
			auxCard = hand.get(i);
			resp += auxCard.points();
		}
		return resp;
	}

}