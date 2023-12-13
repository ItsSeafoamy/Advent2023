package me.seafoam.advent2023.day1;

import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private final String[] digits = new String[] {
		"zero", "one", "two", "three", "four",
		"five", "six", "seven", "eight", "nine"
	};

	@Override
	public void run(List<String> input) {
		int sum = 0;

		for (String line : input) {
			StringBuilder numbers = new StringBuilder();

			for (int i = 0; i < line.length(); i++) {
				if (Character.isDigit(line.charAt(i))) {
					numbers.append(line.charAt(i));
				} else {
					for (int j = 0; j < digits.length; j++) {
						if (line.substring(i).startsWith(digits[j])) {
							numbers.append(j);
							break;
						}
					}
				}
			}

			int num = Integer.parseInt(numbers.charAt(0) + String.valueOf(numbers.charAt(numbers.length() - 1)));
			sum += num;
		}

		System.out.println(sum);
	}
}