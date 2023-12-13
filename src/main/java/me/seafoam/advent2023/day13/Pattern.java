package me.seafoam.advent2023.day13;

public class Pattern {

	public int[][] pattern;

	public Pattern(int[][] pattern) {
		this.pattern = pattern;
	}

	public boolean isVerticalReflection(int x) {
		for (int y = 0; y < pattern[0].length; y++) {
			for (int xl = x - 1, xr = x; xl >= 0 && xr < pattern.length; xl--, xr++) {
				if (pattern[xl][y] != pattern[xr][y]) return false;
			}
		}
		return true;
	}

	public boolean isHorizontalReflection(int y) {
		for (int x = 0; x < pattern.length; x++) {
			for (int yu = y - 1, yd = y; yu >= 0 && yd < pattern[0].length; yu--, yd++) {
				if (pattern[x][yu] != pattern[x][yd]) return false;
			}
		}
		return true;
	}

	public boolean isAlmostVerticalReflection(int x) {
		boolean foundError = false;
		for (int y = 0; y < pattern[0].length; y++) {
			for (int xl = x - 1, xr = x; xl >= 0 && xr < pattern.length; xl--, xr++) {
				if (pattern[xl][y] != pattern[xr][y]) {
					if (foundError) return false;
					foundError = true;
				}
			}
		}
		return foundError;
	}

	public boolean isAlmostHorizontalReflection(int y) {
		boolean foundError = false;
		for (int x = 0; x < pattern.length; x++) {
			for (int yu = y - 1, yd = y; yu >= 0 && yd < pattern[0].length; yu--, yd++) {
				if (pattern[x][yu] != pattern[x][yd]) {
					if (foundError) return false;
					foundError = true;
				}
			}
		}
		return foundError;
	}
}