package me.seafoam.advent2023.day5;

import java.util.ArrayList;
import java.util.List;

public class SeedMap {

	private final List<SeedMapEntry> entries = new ArrayList<>();

	public void add(long dest, long source, long length) {
		entries.add(new SeedMapEntry(dest, source, length));
	}

	public long map(long source) {
		for (SeedMapEntry entry : entries) {
			long mapped = entry.map(source);
			if (mapped != -1) return mapped;
		}

		return source;
	}

	private record SeedMapEntry(long dest, long source, long length) {

		public long map(long source) {
				if (source < this.source || source > this.source + this.length) return -1;
				return this.dest + (source - this.source);
			}
		}
}