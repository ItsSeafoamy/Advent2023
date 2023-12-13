package me.seafoam.advent2023.day5b;

public class Range {

	public long start;
	public long length;

	public Range(long start, long length) {
		this.start = start;
		this.length = length;
	}

	public Range intersection(Range other) {
		if (other.start > start + length || other.start + other.length < start) return null;

		long newStart = Math.max(start, other.start);
		long newEnd = Math.min(start + length, other.start + other.length);

		return new Range(newStart, newEnd - newStart);
	}

	public Range before(Range other) {
		if (start > other.start) return null;
		return new Range(start, Math.min((other.start - start), length));
	}

	public Range after(Range other) {
		if (start + length < other.start + other.length) return null;
		long newStart = Math.max(start, other.start + other.length);
		return new Range(newStart, (start + length) - newStart);
	}

	public Range[] splitAt(Range other) {
		return new Range[] {
			before(other),
			intersection(other),
			after(other)
		};
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Range other)) return false;
		return start == other.start && length == other.length;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + (start + length) + "]";
	}
}