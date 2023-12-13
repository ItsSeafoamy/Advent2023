package me.seafoam.advent2023.day2;

import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		int sum = 0;

		loop:
		for (String line : input) {
			int id = Integer.parseInt(line.split(":")[0].split(" ")[1]);

			String[] sets = line.split(":")[1].split(";");
			int red = 0, green = 0, blue = 0;

			for (String set : sets) {
				String[] cubes = set.trim().split(",");

				for (String cube : cubes) {
					cube = cube.trim();

					int count = Integer.parseInt(cube.split(" ")[0]);
					String colour = cube.split(" ")[1];

					if (colour.equals("red") && count > red) red = count;
					if (colour.equals("green") && count > green) green = count;
					if (colour.equals("blue") && count > blue) blue = count;
				}
			}

			sum += red * green * blue;
		}

		System.out.println(sum);
	}
}