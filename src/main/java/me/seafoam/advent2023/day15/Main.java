package me.seafoam.advent2023.day15;

import java.util.Arrays;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		Arrays.stream(input.get(0).split(",")).map(s -> {
			int value = 0;

			for (char c : s.toCharArray()) {
				value += c;
				value *= 17;
				value %= 256;
			}

			return value;
		}).reduce(Integer::sum).ifPresent(System.out::println);
	}
}