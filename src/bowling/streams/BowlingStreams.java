package bowling.streams;

import java.util.stream.IntStream;

import bowling.Bowling;

public class BowlingStreams implements Bowling {
	public int score(String resultSheet) {
		return score(resultSheet, 1);
	}
	
	private int score(String resultSheet, int frame) {
		if(frame > 10) return 0;
		
		//recursively add current frame score to remaining score
		return 
				getScore(resultSheet, getScoreWindow(resultSheet), getFrameWindow(resultSheet))
				+
				score(resultSheet.substring(getFrameWindow(resultSheet)), frame + 1);
	}
		
	private int getScore(String substring, int scoreWindow, int frameWindow) {
		int score = getFrameScore(substring, frameWindow);
		
		if(substring.length()>frameWindow){
			score += getBonusScore(substring.substring(frameWindow), scoreWindow - frameWindow);
		}
		
		return score;
	}

	private int getBonusScore(String substring, int bonusWindow) {
		//add (reduce) all single scores in bonus window
		return IntStream
				.range(0, bonusWindow)
				.reduce(0, (acc, index) -> acc + getSingleScore(getChar(substring, index)));
		
	}

	private int getFrameScore(String substring, int frameWindow) {
		//add (reduce) all single scores in frame window (max value is 10)
		return Math.min(
			IntStream
				.range(0, frameWindow)
				.reduce(
						0,
						(acc, index) -> acc + getSingleScore(getChar(substring, index))
				)
			, 10
		);
		
	}

	private char getChar(String substring, int i) {
		if(i<substring.length()) return substring.charAt(i);
		return '-';
	}

	private int getScoreWindow(String results) {
		if(results.startsWith("X")) return 3;
		if(getChar(results, 1) == '/') return 3;
		return 2;
	}
	
	private int getFrameWindow(String results) {
		if(results.startsWith("X")) return 1;
		return 2;
	}

	private int getSingleScore(char c){
		switch (c) {
		case '-':
			return 0;
		case '/':
		case 'X':
			return 10;
		default:
			return Integer.parseInt(String.valueOf(c));
		}
	}

}
