package me.seafoam.advent2023.day9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Oasis {

	public List<Integer[]> history = new ArrayList<>();

	public Oasis(String input) {
		int[] values = Arrays.stream(input.split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

		history.add(Arrays.stream(values).boxed().toArray(Integer[]::new));
	}

	public int[] go() {
		while (!Arrays.stream(history.get(history.size() - 1)).allMatch(v -> v == 0)) {
			int[] next = new int[history.get(history.size() - 1).length - 1];

			for (int i = 0; i < next.length; i++) {
				next[i] = history.get(history.size() - 1)[i+1] - history.get(history.size() - 1)[i];
			}

			history.add(Arrays.stream(next).boxed().toArray(Integer[]::new));
		}

		int next = 0;
		int previous = 0;
		for (int i = history.size() - 1; i >= 0; i--) {
			next += history.get(i)[history.get(i).length - 1];
			previous = history.get(i)[0] - previous;
		}

		return new int[] {next, previous};
	}
}