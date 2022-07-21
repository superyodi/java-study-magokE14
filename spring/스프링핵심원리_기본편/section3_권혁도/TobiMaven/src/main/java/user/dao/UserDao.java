package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import user.domain.User;

public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/*
	private ConnectionMaker connectionMaker;
	
	// 생성자 DI
	public UserDao(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	
	//수정자 DI
	public void setConnectionMaker(ConnectionMaker connectionMaker) {
		this.connectionMaker = connectionMaker;
	}
	*/
	public void useTest() throws SQLException{
		//Connection c = connectionMaker.makeConnection();
		Connection c = dataSource.getConnection();
		c.close();
		System.out.println("userTest()실행");
	}

	public void add(User user) throws SQLException{
		Connection c = dataSource.getConnection();
		
		String sql = "INSERT INTO users(id, name, password) values(?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3,  user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public User get(String id) throws SQLException{
		Connection c = dataSource.getConnection();
		
		String sql = "SELECT * FROM users WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
}
