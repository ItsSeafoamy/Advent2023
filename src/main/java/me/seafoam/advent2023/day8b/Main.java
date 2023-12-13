package me.seafoam.advent2023.day8b;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private final HashMap<String, Node> nodes = new HashMap<>();

	@Override
	public void run(List<String> input) {
		int[] directions = input.get(0).chars().map(c -> {
			if (c == 'L') return 0;
			if (c == 'R') return 1;
			return -1;
		}).toArray();

		Pattern pattern = Pattern.compile("([a-zA-Z\\d]{3}).*\\(([a-zA-Z\\d]{3}), ([a-zA-Z\\d]{3})");

		for (int i = 2; i < input.size(); i++) {
			Matcher m = pattern.matcher(input.get(i));
			m.find();

			nodes.put(m.group(1), new Node(m.group(1), m.group(2), m.group(3)));
		}

		for (Node node : nodes.values()) {
			if (node.isEnd()) {
				node.stepsToEnd = 0;
				continue;
			}
			Node checking = node;

			for (int direction : directions) {
				checking = nodes.get(checking.connections[direction]);
			}

			node.nextCycle = checking;

			if (node.nextCycle.isEnd()) {
				node.stepsToEnd = 1;
			}
		}

		while (nodes.values().stream().anyMatch(node -> node.stepsToEnd == -1)) {
			for (Node node : nodes.values()) {
				if (node.stepsToEnd != -1) continue;
				if (node.nextCycle.stepsToEnd == -1) continue;

				node.stepsToEnd = node.nextCycle.stepsToEnd + 1;
			}
		}

		long lcd = 0;

		for (Node node : nodes.values()) {
			if (!node.isStart()) continue;

			if (lcd == 0) {
				lcd = node.stepsToEnd;
				continue;
			}

			lcd = lcd * node.stepsToEnd / gcd(lcd, node.stepsToEnd);
		}

		System.out.println(lcd * directions.length);
	}

	private long gcd(long a, long b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
}