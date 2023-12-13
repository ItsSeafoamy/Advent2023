package me.seafoam.advent2023.day13;

import java.util.ArrayList;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private final List<Pattern> patterns = new ArrayList<>();

	@Override
	public void run(List<String> input) {
		List<String> lines = new ArrayList<>();
		for (String line : input) {
			if (!line.isEmpty()) lines.add(line);
			else {
				int[][] pattern = new int[lines.get(0).length()][lines.size()];

				for (int y = 0; y < lines.size(); y++) {
					String row = lines.get(y);
					for (int x = 0; x < row.length(); x++) {
						int i = row.charAt(x) == '#' ? 1 : 0;
						pattern[x][y] = i;
					}
				}
				lines.clear();

				patterns.add(new Pattern(pattern));
			}
		}

		// Part 1

		int count = 0;

		loop:
		for (Pattern p : patterns) {
			for (int x = 1; x < p.pattern.length; x++) {
				if (p.isVerticalReflection(x)) {
					count += x;
					continue loop;
				}
			}

			for (int y = 1; y < p.pattern[0].length; y++) {
				if (p.isHorizontalReflection(y)) {
					count += y * 100;
					continue loop;
				}
			}
		}

		System.out.println(count);

		// Part 2

		count = 0;

		loop:
		for (Pattern p : patterns) {
			for (int x = 1; x < p.pattern.length; x++) {
				if (p.isAlmostVerticalReflection(x)) {
					count += x;
					continue loop;
				}
			}

			for (int y = 1; y < p.pattern[0].length; y++) {
				if (p.isAlmostHorizontalReflection(y)) {
					count += y * 100;
					continue loop;
				}
			}
		}

		System.out.println(count);
	}
}