package bowling;

import static org.junit.Assert.*;

import org.junit.Test;

import bowling.Bowling;

public abstract class BowlingTestCases {
	protected Bowling score;

	@Test
	public void misses() {
		assertEquals(0, score.score("--------------------"));
	}
	
	@Test
	public void onePinPerTry() {
		assertEquals(20, score.score("11111111111111111111"));
	}
	
	@Test
	public void oneAndFail() {
		assertEquals(10, score.score("1-1-1-1-1-1-1-1-1-1-"));
	}

	@Test
	public void oneSpare() {
		assertEquals(10, score.score("1/------------------"));
	}
	
	@Test
	public void spareEveryOther() {
		assertEquals(100, score.score("-/-/-/-/-/-/-/-/-/-/"));
	}
	
	@Test
	public void spareEveryOtherWith1() {
		assertEquals(109, score.score("1/1/1/1/1/1/1/1/1/1/"));
	}
	
	@Test
	public void spareEveryOtherWithBonus1() {
		assertEquals(110, score.score("1/1/1/1/1/1/1/1/1/1/1"));
	}
	
	@Test
	public void oneStrike() {
		assertEquals(10, score.score("X------------------"));
	}
	
	@Test
	public void oneStrikeEveryFrame() {
		assertEquals(50, score.score("X--X--X--X--X--"));
	}
	
	@Test
	public void oneStrikeEveryFrameWithOne() {
		assertEquals(60, score.score("X1-X1-X1-X1-X1-"));
	}
	
	@Test
	public void oneStrikeEveryFrameWithDoubleOne() {
		assertEquals(12+2+12+2+12+2+12+2+12+2, score.score("X11X11X11X11X11"));
	}
	
	@Test
	public void oneStrikeAtEnd() {
		assertEquals(10, score.score("------------------X--"));
	}
	
	@Test
	public void threeStrikesAtEnd() {
		assertEquals(30, score.score("------------------XXX"));
	}
	
	@Test
	public void allStrikes() {
		assertEquals(300, score.score("XXXXXXXXXXXX"));
	}
}
