package utopia;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.smoothstack.utopia.dao.UserDAO;
import com.smoothstack.utopia.entities.User;
import com.smoothstack.utopia.jdbc.Util;
import com.smoothstack.utopia.jdbc.UtopiaUtil;

public class UserDAOTest {
	UserDAO a1;
	Util u1;
	
	void init() throws SQLException {
		BufferedReader r1;
		String url = null;
		String username = null;
		String password = null;
		try {
			r1 = new BufferedReader(new FileReader("../utopia/src/test/resources/sql_resources.txt"));
			url = r1.readLine();
			username = r1.readLine();
			password = r1.readLine();
			r1.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		u1 = new UtopiaUtil(url,username,password);
		a1 = new UserDAO(u1.getConnection());
	}

	/**
	 * Would test individually, but can't check if it is successful without using the others
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void createAndDeleteTest() throws ClassNotFoundException, SQLException {
		init();
		User test;
		//delete if existed + deletion of non existing object
		assertDoesNotThrow(()->a1.delete("email"));
		assertDoesNotThrow(()->a1.delete("email2"));
		
		//should create without throwing exception
		assertDoesNotThrow(()->a1.create(new User(0, 1, "TestFirst","TestLast", "username", "email", "password", "phone")));
		
		//cannot create another airport with same username, email, phone
		assertThrows(SQLException.class, ()->a1.create(new User(0, 1, "TestFirst","TestLast", "username", "email2", "password", "phone")));
		assertThrows(SQLException.class, ()->a1.create(new User(0, 1, "TestFirst","TestLast", "2username", "email", "password", "phone2")));
		assertThrows(SQLException.class, ()->a1.create(new User(0, 1, "TestFirst","TestLast", "2username", "email2", "password", "phone")));

		
		//should find without throwing exception
		assertDoesNotThrow(()->a1.read("email"));
		test = a1.read("email");
		assertNotNull(test);
		
		//should match data originally given to it
		assertEquals("TestFirst",test.getGivenName());
		assertEquals("TestLast",test.getFamilyName());
		
		//test deletion of existing object
		assertDoesNotThrow(()->a1.delete("email"));
	}
	
	@Test
	void updateTest() throws ClassNotFoundException, SQLException {
		init();
		a1.delete("Testcase1");
		a1.delete("Testcase2");
		a1.create(new User(0, 1, "TestFirst","TestLast", "username1", "Testcase1", "password", "phone1"));
		a1.create(new User(0, 1, "TestFirst","TestLast", "username2", "Testcase2", "password", "phone2"));
		User testCase1 = a1.read("Testcase1");
		User testCase2 = a1.read("Testcase2");
		//Confirm pk are different
		Assertions.assertNotEquals(testCase1.getId(), testCase2.getId());
		
		testCase1.setGivenName("TestFirst2");
		assertDoesNotThrow(()->a1.update(testCase1));
		testCase2.setUsername("username1");
		assertThrows(SQLException.class,()->a1.update(testCase2));
		
		//tests if city has changed
		assertEquals(a1.read("Testcase1").getGivenName(),"TestFirst2");
		assertEquals(a1.read("Testcase2").getUsername(),"username2");
		a1.delete("Testcase2");
		a1.delete("Testcase1");
	}
	
	@Test
	void readAllTest() throws ClassNotFoundException, SQLException {
		init();
		a1.delete("Testcase1");
		a1.delete("Testcase2");
		a1.delete("Testcase3");
		a1.delete("Testcase4");
		
		int size = a1.readAll().size();
		
		a1.create(new User(0, 1, "TestFirst","TestLast", "username1", "Testcase1", "password", "phone1"));
		a1.create(new User(0, 1, "TestFirst","TestLast", "username2", "Testcase2", "password", "phone2"));
		a1.create(new User(0, 1, "TestFirst","TestLast", "username3", "Testcase3", "password", "phone3"));
		a1.create(new User(0, 1, "TestFirst","TestLast", "username4", "Testcase4", "password", "phone4"));
		
		//compare sizes before and after
		assertEquals(a1.readAll().size(),size+4);
		
		a1.delete("Testcase1");
		a1.delete("Testcase2");
		a1.delete("Testcase3");
		a1.delete("Testcase4");
	}
}
