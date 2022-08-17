package user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import user.dao.DaoFactory;
import user.dao.UserDao;
import user.domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=DaoFactory.class)
public class UserDaoTest {
	
	// 테스트 오브젝트가 만들어지고 나면 스프링 테스트 컨텍스트에 의해 자동으로 같이 주입된다.
	@Autowired
	private ApplicationContext context;
	
	private UserDao dao;
	private User user1;
	private User user2;
	private User user3;
	
	@Before
	public void setUp() {
		System.out.println(this.context);
		System.out.println(this);
		
		this.dao = context.getBean("userDao", UserDao.class);

		this.user1 = new User("TestID1", "TestNm1", "TestPw1");
		this.user2 = new User("TestID2", "TestNm2", "TestPw2");
		this.user3 = new User("TestID3", "TestNm3", "TestPw3");
	}
	
	@Test
	public void addAndGet() throws SQLException{
		// User user1 = new User("tobiId1", "tobiNm1", "tobiPw1");
		// User user2 = new User("tobiId2", "tobiNm2", "tobiPw2");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
	
		User userget2 = dao.get(user2.getId());
		assertThat(userget2.getName(), is(user2.getName()));
		assertThat(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException{
		// User user1 = new User("TestID1", "TestNm1", "TestPw1");
		// User user2 = new User("TestID2", "TestNm2", "TestPw2");
		// User user3 = new User("TestID3", "TestNm3", "TestPw3");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(), is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
	
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unkwon_id");
	}
}
