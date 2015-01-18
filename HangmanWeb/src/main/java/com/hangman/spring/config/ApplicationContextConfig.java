package com.hangman.spring.config;

import java.util.Properties;

import javax.sql.DataSource;



import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.hangman.dao.GameDaoImp;
import com.hangman.dao.IGameDao;
import com.hangman.dao.IPlayerDao;
import com.hangman.dao.PlayerDaoImp;
import com.hangman.model.Game;
import com.hangman.model.Player;
import com.hangman.service.IPlayerService;
import com.hangman.service.PlayerServiceImp;

/**
 * @author Chatitze Moumin
 *
 */
@Configuration
@ComponentScan("com.hangman.controller")
@EnableWebMvc
@EnableTransactionManagement
public class ApplicationContextConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
	@Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

	@Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/hangmangame");
    	dataSource.setUsername("root");
    	dataSource.setPassword("root");
    	
    	return dataSource;
    }
	
	private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	sessionBuilder.addAnnotatedClasses(Player.class);
    	sessionBuilder.addAnnotatedClasses(Game.class);
    	return sessionBuilder.buildSessionFactory();
    }

    @Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "playerDao")
    public IPlayerDao getPlayerDao(SessionFactory sessionFactory) {
    	return new PlayerDaoImp(sessionFactory);
    }

    @Autowired
    @Bean(name = "gameDao")
    public IGameDao getGameDao(SessionFactory sessionFactory) {
    	return new GameDaoImp(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "playerService")
    public IPlayerService getPlayerService(IPlayerDao playerDao, IGameDao gameDao) {
    	return new PlayerServiceImp(playerDao, gameDao);
    }

}
