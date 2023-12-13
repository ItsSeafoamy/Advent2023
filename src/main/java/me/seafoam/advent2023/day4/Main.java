package me.seafoam.advent2023.day4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		int points = 0;
		HashMap<Integer, Integer> scratchCards = new HashMap<>();

		for (int card = 0; card < input.size(); card++) {
			String line = input.get(card);
			String[] parts = line.split(":")[1].split("\\|");

			List<Integer> winningNumbers = Arrays.stream(parts[0].trim().replaceAll("  ", " ").split(" ")).mapToInt(Integer::parseInt).boxed().toList();
			List<Integer> myNumbers = Arrays.stream(parts[1].trim().replaceAll("  ", " ").split(" ")).mapToInt(Integer::parseInt).boxed().toList();

			int matches = 0;

			for (Integer num : myNumbers) {
				if (winningNumbers.contains(num)) matches++;
			}

			if (matches > 0) points += Math.pow(2, matches - 1);

			int count = scratchCards.merge(card, 1, Integer::sum);

			for (int i = 1; i <= matches; i++) {
				scratchCards.merge(card + i, count, Integer::sum);
			}
		}

		// Part 1
		System.out.println(points);

		// Part 2
		System.out.println(scratchCards.values().stream().mapToInt(Integer::intValue).sum());
	}
}