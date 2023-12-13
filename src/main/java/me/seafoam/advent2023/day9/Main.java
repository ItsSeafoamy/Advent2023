package me.seafoam.advent2023.day9;

import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		int sumNext = 0, sumPrevious = 0;

		for (String l : input) {
			Oasis oasis = new Oasis(l);
			int[] result = oasis.go();

			sumNext += result[0];
			sumPrevious += result[1];
		}

		System.out.println(sumNext);
		System.out.println(sumPrevious);
	}
}