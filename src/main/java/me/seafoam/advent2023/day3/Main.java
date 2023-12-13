package me.seafoam.advent2023.day3;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	private final List<Gear> gears = new ArrayList<>();

	@Override
	public void run(List<String> input) {
		int xSize = input.get(0).length();
		int ySize = input.size();

		char[][] data = new char[xSize][ySize];

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				data[x][y] = input.get(y).charAt(x);
			}
		}

		int sum = 0;
		boolean inNumber = false;
		boolean adjacentToSymbol = false;
		Point gearPosition = null;

		StringBuilder number = new StringBuilder();

		for (int y = 0; y < ySize; y++) {
			for (int x = 0; x < xSize; x++) {
				char c = data[x][y];

				if (Character.isDigit(c) && !inNumber) {
					inNumber = true;
					number.append(c);

					adjacentToSymbol = isAdjacentToSymbol(data, x, y);
					gearPosition = getAdjacentGear(data, x, y);
				} else if (Character.isDigit(c) && inNumber) {
					number.append(c);

					if (!adjacentToSymbol) adjacentToSymbol = isAdjacentToSymbol(data, x, y);
					if (gearPosition == null) gearPosition = getAdjacentGear(data, x, y);
				} else if (!Character.isDigit(c) && inNumber) {
					inNumber = false;

					if (adjacentToSymbol) {
						sum += Integer.parseInt(number.toString());
						adjacentToSymbol = false;
					}

					if (gearPosition != null) {
						addGear(gearPosition.x, gearPosition.y, Integer.parseInt(number.toString()));
						gearPosition = null;
					}

					number = new StringBuilder();
				}
			}

			if (inNumber) {
				inNumber = false;

				if (adjacentToSymbol) {
					sum += Integer.parseInt(number.toString());
					adjacentToSymbol = false;
				}

				if (gearPosition != null) {
					addGear(gearPosition.x, gearPosition.y, Integer.parseInt(number.toString()));
					gearPosition = null;
				}

				number = new StringBuilder();
			}
		}

		System.out.println(sum);

		sum = 0;

		for (Gear gear : gears) {
			if (gear.isValid()) sum += gear.getRatio();
		}

		System.out.println(sum);
	}

	public boolean isAdjacentToSymbol(char[][] data, int x, int y) {
		for (int j = y-1; j <= y+1; j++) {
			for (int i = x-1; i <= x+1; i++) {
				if (i < 0 || j < 0 || i >= data.length || j >= data[0].length) continue;
				if (i == x && j == y) continue;

				if (!Character.isDigit(data[i][j]) && data[i][j] != '.') return true;
			}
		}

		return false;
	}

	public Point getAdjacentGear(char[][] data, int x, int y) {
		for (int j = y-1; j <= y+1; j++) {
			for (int i = x-1; i <= x+1; i++) {
				if (i < 0 || j < 0 || i >= data.length || j >= data[0].length) continue;
				if (i == x && j == y) continue;

				if (data[i][j] == '*') return new Point(i, j);
			}
		}

		return null;
	}

	public void addGear(int x, int y, int value) {
		for (Gear gear : gears) {
			if (gear.x == x && gear.y == y) {
				gear.addValue(value);
				return;
			}
		}

		gears.add(new Gear(x, y, value));
	}
}