package junit.test;

import org.junit.Test;

import com.fuandren.dao.UserDao;
import com.fuandren.dao.impl.UserDaoImpl;
import com.fuandren.domain.User;


public class UserDaoTest {

	@Test
	public void testAdd(){
		User user = new User();
		user.setId("2013005512");
		user.setUsername("aaa");
		user.setPassword("123");
		UserDao dao = new UserDaoImpl();
		dao.add(user);
	}
	
	@Test
	public void testFind(){
		UserDao dao = new UserDaoImpl();
		User user = dao.find("aaa", "123");
		System.out.println(user);
	}
	
	@Test
	public void testFindByUsername(){
		UserDao dao = new UserDaoImpl();
		System.out.println(dao.find("aaa"));
	}
}
