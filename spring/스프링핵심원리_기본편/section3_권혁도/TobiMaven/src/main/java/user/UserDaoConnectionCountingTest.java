package user;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import user.dao.CountingConnectionMaker;
import user.dao.CountingDaoFactory;
import user.dao.UserDao;

public class UserDaoConnectionCountingTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		//
		// DAO 사용 코드
		dao.useTest();
		dao.useTest();
		dao.useTest();
		dao.useTest();
		dao.useTest();
		
		CountingConnectionMaker ccm = context.getBean("connectionMaker", CountingConnectionMaker.class);
		System.out.println("Connection counter: " + ccm.getCounter());
	}

}
