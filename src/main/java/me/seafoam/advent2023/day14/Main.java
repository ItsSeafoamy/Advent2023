package me.seafoam.advent2023.day14;

import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	int[][] map;

	@Override
	public void run(List<String> input) {
		map = input.stream().map(line -> line.chars().map(c -> switch (c) {
			case '.' -> 0;
			case 'O' -> 1;
			case '#' -> 2;
			default -> throw new IllegalArgumentException("Unexpected value: " + c);
		}).toArray()).toArray(int[][]::new);

		for (int x = 0; x < map.length; x++) {
			for (int y = 1; y < map[0].length; y++) {
				if (y < 1) continue;
				if (map[y][x] == 1 && map[y - 1][x] == 0) {
					map[y - 1][x] = 1;
					map[y][x] = 0;

					y -= 2;
				}
			}
		}

		int count = 0;

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[y][x] == 1) count += map[0].length - y;
			}
		}

		System.out.println(count);
	}
}