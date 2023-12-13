package me.seafoam.advent2023.day12;

import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		List<Row> rows = input.stream().map(Row::new).toList();

		long count = 0;
		for (Row row : rows) {
			count += row.count();
		}

		System.out.println(count);
	}
}