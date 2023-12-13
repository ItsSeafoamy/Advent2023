package me.seafoam.advent2023.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class Row {

	public String springs;
	public final Pattern fullPattern;

	public Row(String input) {
		springs = input.split(" ")[0];
		for (int i = 0; i < 4; i++) {
			springs += "?" + input.split(" ")[0];
		}

		String p = input.split(" ")[1];
		for (int i = 0; i < 4; i++) {
			p += "," + input.split(" ")[1];
		}

		String regex = "^\\.*";
		regex += String.join("\\.+", Arrays.stream(p.split(",")).map(s -> "#{" + s + "}").toArray(String[]::new));
		regex += "\\.*$";

		fullPattern = Pattern.compile(regex);
	}

	public long count() {
		long count = 0;

		List<String> possibilities = new ArrayList<>(List.of(""));
		for (int i = 0; i < springs.length(); i++) {
			if (springs.charAt(i) != '?') {
				for (int j = 0; j < possibilities.size(); j++) {
					possibilities.set(j, possibilities.get(j) + springs.charAt(i));
				}
			} else {
				List<String> newPossibilities = new ArrayList<>();
				for (String possibility : possibilities) {
					newPossibilities.add(possibility + ".");
					newPossibilities.add(possibility + "#");
				}
				possibilities = newPossibilities;
			}
		}

		for (String possibility : possibilities) {
			if (fullPattern.matcher(possibility).matches()) {
				count++;
			}
		}

		return count;
	}
}