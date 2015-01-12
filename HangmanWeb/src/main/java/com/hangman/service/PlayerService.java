package com.hangman.service;

import java.util.List;

import com.hangman.model.HangmanGame;
import com.hangman.model.Player;

public interface PlayerService {

	public HangmanGame startNewGame(String userName);
	public void submitLetter(String userName, Character letter);
	
	public HangmanGame getHangmanGame(String userName);
	public List<Player> getAllGamesStatus();
	public void removeGame(String userName);

	public boolean isExistingPlayer(String userName);
	public boolean isLetterAlreadySubmitted(String userName, Character letter);
	public boolean isPlayerWonTheGame(String userName);
	public boolean isPlayerLostTheGame(String userName);
}
