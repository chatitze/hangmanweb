package com.hangman.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

public class HangmanGame implements Serializable {

	  private static final long serialVersionUID = 2854637468960193039L;
	  
	  private static final List<Character> letterList = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	  
	  private static String[] randomWords = { "geography", "cat", "yesterday", "java", "truck", "opportunity",
			"fish", "token", "transportation", "bottom", "apple", "cake",
			"remote", "pocket", "terminology", "arm", "cranberry", "tool",
			"caterpillar", "spoon", "watermelon", "laptop", "toe", "toad",
			"fundamental", "capitol", "garbage", "anticipate"};

	  private int remainingMoves = 7;
	  private final String secretWord;
	  
	  private final List<Character> 		visibleLetters    = new ArrayList<Character>();
	  private final List<Character> 		secretWordLetters = new ArrayList<Character>();
	  private final Map<Character,Boolean>  usedLetters       = new HashMap<Character,Boolean>();

	  /**
	   * Initialise the secretWordLetters from secretWord 
	   * and the visibleLetters with '_' characters. 
	   * 
	   * @param secretWord
	   */
	  public HangmanGame(){
	    this.secretWord = StringUtils.upperCase(HangmanGame.selectRandomWord());
	    int secretWordLegth = this.secretWord.length();
	    
	    // set all the letters in alphabet and initialise them false (not used yet) in the beginning of each new game
	    for(int i = 0; i < letterList.size(); i++){
	    	usedLetters.put(letterList.get(i), false);
	    }
	    
	    for (int i = 0; i < secretWordLegth; i++) {
	      secretWordLetters.add(this.secretWord.charAt(i));
	      visibleLetters.add('_');
	    }  
	    
	  }

	  public String getSecretWord() {
	    return secretWord;
	  }

	  public List<Character> getSecretWordLetters() {
	    return secretWordLetters;
	  }

	  public List<Character> getVisibleLetters() {
	    return visibleLetters;
	  }

	  public Map<Character, Boolean> getUsedLetters() {
	    return usedLetters;
	  }
	  
	  public int getRemainingMoves() {
		return remainingMoves;
	  }

	  public void setRemainingMoves(int remainingMoves) {
		this.remainingMoves = remainingMoves;
	  }

	  public static String selectRandomWord() {
	    int index = new Random().nextInt(randomWords.length);
	    return randomWords[index];
	  }
}
