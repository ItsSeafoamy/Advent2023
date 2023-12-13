package me.seafoam.advent2023;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;

public class Start {

	public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		//int day = 5;
		String version = "";

		List<String> input = Files.readAllLines(Paths.get("src/main/resources/day" + day + ".txt"));
		Puzzle puzzle = Class.forName("me.seafoam.advent2023.day" + day + version + ".Main").asSubclass(Puzzle.class).getConstructor().newInstance();

		long startTime = System.currentTimeMillis();
		puzzle.run(input);
		long endTime = System.currentTimeMillis();

		System.out.print("\nTook " + (endTime - startTime) + "ms");
	}
}