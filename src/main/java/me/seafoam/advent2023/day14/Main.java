package me.seafoam.advent2023.day14;

import java.util.HashMap;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private int[][] map;
	private final HashMap<String, Integer> cache = new HashMap<>();

	@Override
	public void run(List<String> input) {
		map = input.stream().map(line -> line.chars().map(c -> switch (c) {
			case '.' -> 0;
			case 'O' -> 1;
			case '#' -> 2;
			default -> throw new IllegalArgumentException("Unexpected value: " + c);
		}).toArray()).toArray(int[][]::new);

		int cycles = 1_000_000_000;
		for (int i = 0; i < cycles; i++) {
			tiltNorth();
			tiltWest();
			tiltSouth();
			tiltEast();

			if (cache.containsKey(mapToString())) {
				int cycle = i - cache.get(mapToString());
				while (i + cycle < cycles) i += cycle;
			} else {
				cache.put(mapToString(), i);
			}
		}

		int count = 0;

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				if (map[y][x] == 1) count += map[0].length - y;
			}
		}

		System.out.println(count);
		// 94590 too high
	}

	public void tiltNorth() {
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
	}

	public void tiltSouth() {
		for (int x = 0; x < map.length; x++) {
			for (int y = map[0].length - 2; y >= 0; y--) {
				if (y >= map[0].length - 1) continue;
				if (map[y][x] == 1 && map[y + 1][x] == 0) {
					map[y + 1][x] = 1;
					map[y][x] = 0;

					y += 2;
				}
			}
		}
	}

	public void tiltWest() {
		for (int y = 0; y < map[0].length; y++) {
			for (int x = 1; x < map.length; x++) {
				if (x < 1) continue;
				if (map[y][x] == 1 && map[y][x - 1] == 0) {
					map[y][x - 1] = 1;
					map[y][x] = 0;

					x -= 2;
				}
			}
		}
	}

	public void tiltEast() {
		for (int y = 0; y < map[0].length; y++) {
			for (int x = map.length - 2; x >= 0; x--) {
				if (x >= map.length - 1) continue;
				if (map[y][x] == 1 && map[y][x + 1] == 0) {
					map[y][x + 1] = 1;
					map[y][x] = 0;

					x += 2;
				}
			}
		}
	}

	public String mapToString() {
		StringBuilder sb = new StringBuilder();

		for (int y = 0; y < map[0].length; y++) {
			for (int x = 0; x < map.length; x++) {
				sb.append(map[y][x]);
			}
		}

		return sb.toString();
	}
}