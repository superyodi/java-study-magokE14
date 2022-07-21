package user.dao;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class DaoFactory {
	/* 생성자 방식 DI
	@Bean
	public UserDao userDao() {
		return new UserDao(new DConnectionMaker());
	}
	*/
	
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/tobi");
		dataSource.setUsername("tobiid");
		dataSource.setPassword("tobipw");
		
		return dataSource;
	}
	
	// 수정자 방식 DI
	@Bean
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setDataSource(dataSource());
		return userDao;
	}
	
	/*
	@Bean
	public ConnectionMaker connectionMaker() {
		return new DConnectionMaker();
	}
	*/
}
