package me.seafoam.advent2023.day3;

public class Gear {

	public final int x, y;
	private final int[] values = new int[2];
	private boolean possible = true;

	public Gear(int x, int y, int value) {
		this.x = x;
		this.y = y;
		values[0] = value;
	}

	public void addValue(int value) {
		if (values[1] == 0) { // I hope 0 is not a valid value
			values[1] = value;
		} else {
			possible = false;
		}
	}

	public boolean isValid() {
		return possible && values[1] != 0;
	}

	public int getRatio() {
		return values[0] * values[1];
	}
}