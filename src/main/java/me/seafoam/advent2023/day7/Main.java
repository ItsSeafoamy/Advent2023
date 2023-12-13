package me.seafoam.advent2023.day7;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import me.seafoam.advent2023.Puzzle;

public class Main implements Puzzle {

	@Override
	public void run(List<String> input) {
		AtomicLong score = new AtomicLong();
		AtomicLong rank = new AtomicLong();

		input.stream().map(Hand::new).sorted().forEach(h -> {
			score.addAndGet(rank.incrementAndGet() * h.bid);

			//System.out.println("#" + rank.get() + " " + h + " - " + h.score + " - " + score.get());
		});

		System.out.println(score);
	}
}