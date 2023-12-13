package me.seafoam.advent2023.day6;

import java.util.Arrays;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		String timesString = input.get(0).split(":")[1].trim();
		String distancesString = input.get(1).split(":")[1].trim();

		// Part 1
		int[] times = Arrays.stream(timesString.split(" "))
				.filter(s -> !s.isEmpty())
				.mapToInt(Integer::parseInt)
				.toArray();

		int[] distances = Arrays.stream(distancesString.split(" "))
				.filter(s -> !s.isEmpty())
				.mapToInt(Integer::parseInt)
				.toArray();

		int total = 0;

		for (int i = 0; i < times.length; i++) {
			int time = times[i];
			int distance = distances[i];
			int score = 0;

			for (int speed = 1; speed <= time; speed++) {
				int d = speed * (time - speed);

				if (d > distance) score++;
			}

			if (total == 0) total = score;
			else total *= score;
		}

		System.out.println(total);

		// Part 2
		long time = Long.parseLong(timesString.replaceAll(" ", ""));
		long distance = Long.parseLong(distancesString.replaceAll(" ", ""));

		int score = 0;

		for (long speed = 1; speed <= time; speed++) {
			long d = speed * (time - speed);

			if (d > distance) score++;
		}

		System.out.println(score);
	}
}