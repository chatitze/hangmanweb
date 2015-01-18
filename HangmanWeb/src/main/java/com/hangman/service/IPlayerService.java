package com.hangman.service;

import java.util.List;

import com.hangman.model.Player;
/**
 * @author Chatitze Moumin
 *
 */

public interface IPlayerService {

	public List<Player> getAllPlayers();
	public Player getPlayer(String username);
	
	public int addPlayer(String username);
	
	public Player startNewGame(String userName);
	public void submitLetter(String userName, Character letter);
	
}
