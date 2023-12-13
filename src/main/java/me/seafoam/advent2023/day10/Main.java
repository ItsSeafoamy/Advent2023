package me.seafoam.advent2023.day10;

import java.awt.Point;
import java.util.List;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 4;
	public static final int WEST = 8;

	@Override
	public void run(List<String> input) {
		Point start = new Point();
		int width = input.get(0).length();
		int height = input.size();

		Tile[][] map = new Tile[width][height];
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				char c = input.get(y).charAt(x);
				map[x][y] = switch (c) {
					case '|' -> new Tile(x, y, NORTH | SOUTH);
					case '-' -> new Tile(x, y, EAST | WEST);
					case 'L' -> new Tile(x, y, NORTH | EAST);
					case 'J' -> new Tile(x, y, NORTH | WEST);
					case '7' -> new Tile(x, y, SOUTH | WEST);
					case 'F' -> new Tile(x, y, SOUTH | EAST);
					case 'S' -> {
						start = new Point(x, y);
						Tile startPipe = new Tile(x, y, 0);
						startPipe.mainLoop = true;
						yield startPipe;
					}
					default -> new Tile(x, y, 0);
				};
			}
		}

		if (start.x < width - 1) if (map[start.x + 1][start.y].isWest()) map[start.x][start.y].setEast();
		if (start.x > 0) if (map[start.x - 1][start.y].isEast()) map[start.x][start.y].setWest();
		if (start.y < height - 1) if (map[start.x][start.y + 1].isNorth()) map[start.x][start.y].setSouth();
		if (start.y > 0) if (map[start.x][start.y - 1].isSouth()) map[start.x][start.y].setNorth();

		int steps = 0;
		Point current = new Point(start);
		int direction;
		if (map[current.x][current.y].isNorth()) direction = NORTH;
		else if (map[current.x][current.y].isEast()) direction = EAST;
		else  direction = SOUTH;

		do {
			steps++;
			switch (direction) {
				case NORTH -> current.y--;
				case EAST -> current.x++;
				case SOUTH -> current.y++;
				case WEST -> current.x--;
			}
			if (map[current.x][current.y].isNorth() && (direction & SOUTH) != SOUTH) direction = NORTH;
			else if (map[current.x][current.y].isEast() && (direction & WEST) != WEST) direction = EAST;
			else if (map[current.x][current.y].isSouth() && (direction & NORTH) != NORTH) direction = SOUTH;
			else if (map[current.x][current.y].isWest() && (direction & EAST) != EAST) direction = WEST;

			map[current.x][current.y].mainLoop = true;
		} while (current.x != start.x || current.y != start.y);

		System.out.println(steps / 2);

		// Part 2

		int enclosedTiles = 0;
		for (int y = 1; y < height; y++) {
			int loopCrossings = 0;
			boolean alongEdge = false;
			int currentCorner = 0;

			for (int x = 0; x < width; x++) {
				if (map[x][y].mainLoop) {
					if (map[x][y].isVertical()) loopCrossings++;
					else if (!alongEdge && map[x][y].isCorner()) {
						alongEdge = true;
						if (map[x][y].isNorth()) currentCorner = NORTH;
						else if (map[x][y].isSouth()) currentCorner = SOUTH;
					} else if (alongEdge && map[x][y].isCorner()) {
						if (map[x][y].isNorth() && currentCorner == SOUTH) loopCrossings++;
						else if (map[x][y].isSouth() && currentCorner == NORTH) loopCrossings++;
						alongEdge = false;
					}
				} else if (loopCrossings % 2 == 1) {
					enclosedTiles++;
					map[x][y].enclosed = true;
				}
			}
		}

		System.out.println(enclosedTiles);

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (map[x][y].mainLoop) {
					if (map[x][y].isHorizontal()) System.out.print('━');
					else if (map[x][y].isVertical()) System.out.print('┃');
					else if (map[x][y].isNorth() && map[x][y].isEast()) System.out.print('┗');
					else if (map[x][y].isNorth() && map[x][y].isWest()) System.out.print('┛');
					else if (map[x][y].isSouth() && map[x][y].isWest()) System.out.print('┓');
					else if (map[x][y].isSouth() && map[x][y].isEast()) System.out.print('┏');
				}
				else if (map[x][y].enclosed) System.out.print('X');
				else System.out.print('·');
			}
			System.out.println();
		}
	}
}