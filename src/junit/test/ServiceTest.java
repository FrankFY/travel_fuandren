package junit.test;

import org.junit.Test;

import com.fuandren.domain.User;
import com.fuandren.exception.UserExistException;
import com.fuandren.service.BusinessService;
import com.fuandren.service.impl.BusinessServiceImpl;

public class ServiceTest {

	@Test
	public void testRegister(){
		User user = new User();
		user.setId("2013005600");
		user.setUsername("bbb");
		user.setPassword("123");
		BusinessService service = new BusinessServiceImpl();
		try {
			service.registerUser(user);
			System.out.println("注册成功");
		} catch (UserExistException e) {
			System.out.println("用户已存在");
		}
	}
	
	@Test
	public void testLogin(){
		BusinessService service = new BusinessServiceImpl();
		User user = service.loginUser("bbb", "123");
		System.out.println(user);
	}
	
}
