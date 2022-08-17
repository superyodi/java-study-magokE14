package user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import user.domain.User;

public class UserDao {
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
		
		User user = null;
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));	
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if(user == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return user;
	}
	
	public void deleteAll() throws SQLException{
		Connection c = dataSource.getConnection();
		
		String sql = "TRUNCATE users";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	public int getCount() throws SQLException{
		Connection c = dataSource.getConnection();
		
		String sql = "SELECT COUNT(*) FROM users";
		PreparedStatement ps = c.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
}
