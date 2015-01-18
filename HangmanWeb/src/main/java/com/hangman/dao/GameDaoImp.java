package com.hangman.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hangman.model.Game;

/**
 * @author Chatitze Moumin
 *
 */
@Repository
@Transactional
public class GameDaoImp implements IGameDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public GameDaoImp(){
		super();
	}
	
	public GameDaoImp(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void update(Game game) {
		sessionFactory.getCurrentSession().update(game);
	}

	@Override
	public void delete(int id) {
		Game gameToDelete = new Game();
		gameToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(gameToDelete);
	}

}
