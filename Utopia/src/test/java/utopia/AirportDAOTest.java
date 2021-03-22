package utopia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.smoothstack.utopia.dao.AirportDAO;
import com.smoothstack.utopia.entities.Airport;
import com.smoothstack.utopia.jdbc.Util;
import com.smoothstack.utopia.jdbc.UtopiaUtil;

class AirportDAOTest {
	AirportDAO a1;
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
		a1 = new AirportDAO(u1.getConnection());
	}

	/**
	 * Would test individually, but can't check if it is successful without using the others
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void createAndDeleteTest() throws ClassNotFoundException, SQLException {
		init();
		Airport test;
		//delete if existed + deletion of non existing object
		assertDoesNotThrow(()->a1.delete(new Airport("TES","Testcase1")));
		
		//should create without throwing exception
		assertDoesNotThrow(()->a1.create(new Airport("TES","Testcase1")));
		
		//cannot create another airport with same code
		assertThrows(SQLException.class, ()->a1.create(new Airport("TES","Testcase1")));
		
		//should find without throwing exception
		assertDoesNotThrow(()->a1.readByCode("TES"));
		test = a1.readByCode("TES").get(0);
		assertNotNull(test);
		
		//should match data originally given to it
		assertEquals("TES",test.getIataId());
		assertEquals("Testcase1",test.getCity());
		
		//test deletion of existing object
		assertDoesNotThrow(()->a1.delete(new Airport("TES","Testcase1")));
	}
	
	@Test
	void updateTest() throws ClassNotFoundException, SQLException {
		init();
		a1.delete(new Airport("TES","Testcase2"));
		a1.delete(new Airport("TER","Testcase22"));
		a1.create(new Airport("TES","Testcase2"));
		a1.create(new Airport("TER","Testcase22"));
		//Could have matching city name, just not code
		assertDoesNotThrow(()->a1.update(new Airport("TES","Testcase22")));
		//Will delete and create if you want to change code
		
		//tests if city has changed
		assertEquals(a1.readByCode("TES").get(0).getCity(),"Testcase22");
		a1.delete(new Airport("TES","Testcase2"));
		a1.delete(new Airport("TER","Testcase22"));
	}
	
	@Test
	void readAllTest() throws ClassNotFoundException, SQLException {
		init();
		a1.delete(new Airport("TS1","Test1"));
		a1.delete(new Airport("TS2","Test2"));
		a1.delete(new Airport("TS3","Test3"));
		a1.delete(new Airport("TS4","Test4"));
		
		int size = a1.readAll().size();
		
		a1.create(new Airport("TS1","Test1"));
		a1.create(new Airport("TS2","Test2"));
		a1.create(new Airport("TS3","Test3"));
		a1.create(new Airport("TS4","Test4"));
		
		//compare sizes before and after
		assertEquals(a1.readAll().size(),size+4);
		
		a1.delete(new Airport("TS1","Test1"));
		a1.delete(new Airport("TS2","Test2"));
		a1.delete(new Airport("TS3","Test3"));
		a1.delete(new Airport("TS4","Test4"));
	}

}
