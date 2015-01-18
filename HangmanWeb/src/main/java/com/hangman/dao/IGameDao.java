package com.hangman.dao;

import com.hangman.model.Game;

/**
 * @author Chatitze Moumin
 *
 */
public interface IGameDao {

	public void update(Game game);
	
	public void delete(int id);
}
