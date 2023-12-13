package me.seafoam.advent2023.day5;

import java.util.Arrays;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private final SeedMap[] maps = new SeedMap[7];
	private int counter = 0;

	@Override
	public void run(List<String> input) {
		long[] seeds = Arrays.stream(input.get(0).split(":")[1].trim().split(" ")).mapToLong(Long::parseLong).toArray();

		for (int i = 3; i < input.size(); i++) {
			String line = input.get(i);

			if (line.isEmpty()) continue;
			if (line.contains(":")) {
				counter++;
				continue;
			}

			if (maps[counter] == null) maps[counter] = new SeedMap();

			long[] parts = Arrays.stream(line.split(" ")).mapToLong(Long::parseLong).toArray();
			maps[counter].add(parts[0], parts[1], parts[2]);
		}

		// Part 1
		long location = Long.MAX_VALUE;

		for (long seed : seeds) {
			for (SeedMap map : maps) {
				seed = map.map(seed);
			}

			if (seed < location) location = seed;
		}

		System.out.println(location);

		// Part 2
		location = Long.MAX_VALUE;

		for (int i = 0; i < seeds.length; i += 2) {
			long from = seeds[i];
			long length = seeds[i + 1];

			for (long l = from; l < from + length; l++) {
				long mapped = l;

				for (SeedMap map : maps) {
					mapped = map.map(mapped);
				}

				if (mapped < location) {
					location = mapped;
				}
			}
		}

		System.out.println(location);
	}
}