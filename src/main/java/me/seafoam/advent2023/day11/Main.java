package me.seafoam.advent2023.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		int width = input.get(0).length();
		int height = input.size();

		int[][] map = new int[width][height];
		int currentGalaxy = 1;

		List<Galaxy> galaxies = new ArrayList<>();

		for (int y = 0; y < height; y++) {
			String line = input.get(y);
			for (int x = 0; x < width; x++) {
				char c = line.charAt(x);
				if (c == '.') {
					map[x][y] = 0;
				} else if (c == '#') {
					map[x][y] = currentGalaxy++;
					galaxies.add(new Galaxy(x, y));
				}
			}
		}

		for (int x = width - 1; x >= 0; x--) {
			if (Arrays.stream(map[x]).allMatch(g -> g == 0)) {
				for (Galaxy g : galaxies) {
					if (g.x > x) g.x += 999999;
				}
			}
		}

		for (int y = height - 1; y >= 0; y--) {
			int finalY = y;
			if (Arrays.stream(map).allMatch(g -> g[finalY] == 0)) {
				for (Galaxy g : galaxies) {
					if (g.y > y) g.y += 999999;
				}
			}
		}

		long total = 0;
		for (Galaxy a : galaxies) {
			for (Galaxy b : galaxies) {
				if (a.y > b.y || (a.y == b.y && a.x > b.x)) {
					total += Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
				}
			}
		}

		System.out.println(total);
	}
}