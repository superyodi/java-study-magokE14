package user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NUserDao implements ConnectionMaker {
	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost/tobi", "tobiid", "tobipw");
		
		// N사 DB Connetion 생성코드
		
		return c;
	}
}