package me.seafoam.advent2023.day7;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Hand implements Comparable<Hand> {

	private final char[] cards;
	public final int bid;
	public final int score;

	private static final List<Character> cardValues = List.of('J', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'Q', 'K', 'A');

	public Hand(String cards) {
		this.cards = cards.split(" ")[0].toCharArray();
		this.bid = Integer.parseInt(cards.split(" ")[1]);

		HashMap<Character, Integer> cardAmounts = new HashMap<>();
		for (char card : this.cards) {
			cardAmounts.merge(card, 1, Integer::sum);
		}

		int jokerAmount = 0;
		if (cardAmounts.containsKey('J')) jokerAmount = cardAmounts.remove('J');

		List<Integer> values = new ArrayList<>(cardAmounts.values());
		Collections.sort(values);
		Collections.reverse(values);

		if (values.size() == 0) { // five jokers
			score = 6;
			return;
		}

		values.set(0, values.get(0) + jokerAmount);

		if (values.get(0) == 5) score = 6; // five of a kind
		else if (values.get(0) == 4) score = 5; // four of a kind
		else if (values.get(0) == 3 && values.get(1) == 2) score = 4; // full house
		else if (values.get(0) == 3) score = 3; // three of a kind
		else if (values.get(0) == 2 && values.get(1) == 2) score = 2; // two pair
		else if (values.get(0) == 2) score = 1; // one pair
		else score = 0; // high card
	}

	@Override
	public int compareTo(Hand o) {
		if (score != o.score) return score - o.score;

		for (int i = 0; i < cards.length; i++) {
			if (cardValues.indexOf(cards[i]) != cardValues.indexOf(o.cards[i])) return cardValues.indexOf(cards[i]) - cardValues.indexOf(o.cards[i]);
		}

		return 0;
	}

	@Override
	public String toString() {
		return new String(cards) + " " + bid;
	}
}