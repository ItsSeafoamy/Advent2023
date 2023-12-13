package me.seafoam.advent2023.day10;

public class Tile {

	public static final int NORTH = 1;
	public static final int EAST = 2;
	public static final int SOUTH = 4;
	public static final int WEST = 8;

	public boolean mainLoop;
	public int x, y;
	public int direction;

	public boolean enclosed;

	public Tile(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	public boolean isNorth() {
		return (direction & NORTH) == NORTH;
	}

	public boolean isEast() {
		return (direction & EAST) == EAST;
	}

	public boolean isSouth() {
		return (direction & SOUTH) == SOUTH;
	}

	public boolean isWest() {
		return (direction & WEST) == WEST;
	}

	public void setNorth() {
		direction |= NORTH;
	}

	public void setEast() {
		direction |= EAST;
	}

	public void setSouth() {
		direction |= SOUTH;
	}

	public void setWest() {
		direction |= WEST;
	}

	public boolean isPipe() {
		return direction != 0;
	}

	public boolean isHorizontal() {
		return isEast() && isWest();
	}

	public boolean isVertical() {
		return isNorth() && isSouth();
	}

	public boolean isCorner() {
		return (isNorth() && isEast()) || (isEast() && isSouth()) || (isSouth() && isWest()) || (isWest() && isNorth());
	}
}