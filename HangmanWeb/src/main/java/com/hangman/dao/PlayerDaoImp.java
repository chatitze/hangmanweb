package com.hangman.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hangman.model.Player;

/**
 * @author Chatitze Moumin
 *
 */
@Repository
@Transactional
public class PlayerDaoImp implements IPlayerDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public PlayerDaoImp(){
		super();
	}
	
	public PlayerDaoImp(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Override
	public List<Player> getPlayerList() {

		@SuppressWarnings("unchecked")
		List<Player> list =  sessionFactory.getCurrentSession().createCriteria(Player.class).list();
		return  list;
	}

	@Override
	public Player getPlayer(String username) {
		return (Player) sessionFactory.getCurrentSession()
				.createCriteria(Player.class)
				.add(Restrictions.ilike("userName", username))
				.uniqueResult();
	}
	
	@Override
	public int save(Player player) {
		sessionFactory.getCurrentSession().persist(player);
		return player.getId();
	}

	@Override
	public void update(Player player) {
		sessionFactory.getCurrentSession().update(player);
		
	}
}
