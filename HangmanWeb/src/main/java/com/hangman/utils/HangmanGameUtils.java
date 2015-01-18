package com.hangman.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author Chatitze Moumin
 *
 */
public class HangmanGameUtils {

	public static final List<Character> letterList = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z');
	  
	public static String[] randomWords = { "geography", "cat", "yesterday", "java", "truck", "opportunity",
											"fish", "token", "transportation", "bottom", "apple", "cake",
											"remote", "pocket", "terminology", "arm", "cranberry", "tool",
											"caterpillar", "spoon", "watermelon", "laptop", "toe", "toad",
											"fundamental", "capitol", "garbage", "anticipate"};

	public static String selectRandomWord() {
	    int index = new Random().nextInt(randomWords.length);
	    return randomWords[index];
	}
}
