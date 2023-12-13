package me.seafoam.advent2023.day8b;

public class Node {

	public final String name;
	public String[] connections;
	public int stepsToEnd = -1;
	public Node nextCycle;

	public Node(String name, String left, String right) {
		this.name = name;
		connections = new String[] {left, right};
	}

	public boolean isStart() {
		return name.charAt(2) == 'A';
	}

	public boolean isEnd() {
		return name.charAt(2) == 'Z';
	}
}