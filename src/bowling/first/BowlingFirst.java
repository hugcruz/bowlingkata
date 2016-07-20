package bowling.first;

import bowling.Bowling;

public class BowlingFirst implements Bowling {
	
	public int score(String string) {
		int result = 0;
		
		int i = 0;
		int frames = 1;
		while(i < string.length()){
			int scoreWindow = getScoreWindow(string.substring(i));
			int frameWindow = getFrameWindow(string.substring(i));
			
			if(frames <= 10){
				result += getScore(string.substring(i), scoreWindow, frameWindow);
			}
			
			frames += 1;
			i += frameWindow;
		}
		
		return result;
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

	private int getScore(String substring, int scoreWindow, int frameWindow) {
		int score = getFrameScore(substring, frameWindow);
		
		if(substring.length()>frameWindow){
			score += getBonusScore(substring.substring(frameWindow), scoreWindow - frameWindow);
		}
		
		return score;
	}

	private int getBonusScore(String substring, int bonusWindow) {
		int score = 0;
		
		for(int i=0; i < bonusWindow; ++i){
			score += getSingleScore(getChar(substring, i));
		}
		
		return score;
	}

	private int getFrameScore(String substring, int frameWindow) {
		int score = 0;
		
		for(int i=0; i < frameWindow; ++i){
			score += getSingleScore(getChar(substring, i));
		}
		
		return Math.min(score, 10);
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

	private char getChar(String substring, int i) {
		if(i<substring.length()) return substring.charAt(i);
		return '-';
	}
}
