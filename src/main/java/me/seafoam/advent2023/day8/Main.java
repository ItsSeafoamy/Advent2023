package me.seafoam.advent2023.day8;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private HashMap<String, String[]> nodes = new HashMap<>();

	@Override
	public void run(List<String> input) {
		for (int i = 2; i < input.size(); i++) {
			Pattern pattern = Pattern.compile("([a-zA-Z]{3}).*\\(([a-zA-Z]{3}), ([a-zA-Z]{3})");

			Matcher m = pattern.matcher(input.get(i));
			m.find();

			nodes.put(m.group(1), new String[] {m.group(2), m.group(3)});
		}

		int[] directions = input.get(0).chars().map(c -> {
			if (c == 'L') return 0;
			if (c == 'R') return 1;
			return -1;
		}).toArray();

		long steps = 0;
		int currentDir = 0;
		String currentNode = "AAA";

		while (!currentNode.equals("ZZZ")) {
			currentNode = nodes.get(currentNode)[directions[currentDir]];
			currentDir = (++currentDir) % directions.length;
			steps++;
		}

		System.out.println(steps);
	}
}