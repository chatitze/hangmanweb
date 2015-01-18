package com.hangman.dao;

import java.util.List;

import com.hangman.model.Player;

/**
 * @author Chatitze Moumin
 *
 */
public interface IPlayerDao {

	public List<Player> getPlayerList();
	
	public Player getPlayer(String username);
	
	public int save(Player player);
	
	public void update(Player player);
}
