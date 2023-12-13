package me.seafoam.advent2023.day5b;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		String seedLine = input.get(0);

		List<Range> current = Pattern.compile("\\d+ \\d+")
				.matcher(seedLine)
				.results()
				.map(s -> s.group().split(" "))
				.map(s -> new Range(Long.parseLong(s[0]), Long.parseLong(s[1])))
				.collect(Collectors.toList());

		List<Range> next = new ArrayList<>();

		for (int i = 3; i < input.size(); i++) {
			String line = input.get(i);

			if (line.isEmpty()) continue;
			if (line.contains(":")) {
				next.addAll(current);
				current = next;
				next = new ArrayList<>();

				continue;
			}

			Range map = new Range(Long.parseLong(line.split(" ")[1]), Long.parseLong(line.split(" ")[2]));
			long dest = Long.parseLong(line.split(" ")[0]);

			for (int j = 0; j < current.size(); j++) {
				Range seed = current.get(j);
				Range[] split = seed.splitAt(map);

				// before
				if (split[0] != null && split[0].length > 0 && !split[0].equals(seed)) current.add(split[0]);

				// inside
				if (split[1] != null && split[1].length > 0) {
					long offset = dest - map.start;

					next.add(new Range(split[1].start + offset, split[1].length));
					current.set(j, null);
				}

				// after
				if (split[2] != null && split[2].length > 0 && !split[2].equals(seed)) current.add(split[2]);
			}

			current = current.stream().filter(Objects::nonNull).collect(Collectors.toList());
		}

		current.stream().mapToLong(s -> s.start).min().ifPresent(System.out::println);
	}
}