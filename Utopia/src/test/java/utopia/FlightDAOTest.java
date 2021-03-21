package utopia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.smoothstack.utopia.dao.FlightDAO;
import com.smoothstack.utopia.entities.Flight;
import com.smoothstack.utopia.jdbc.Util;
import com.smoothstack.utopia.jdbc.UtopiaUtil;

class FlightDAOTest {
	FlightDAO a1;
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
		a1 = new FlightDAO(u1.getConnection());
	}

	/**
	 * Would test individually, but can't check if it is successful without using the others
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	@Test
	void createAndDeleteTest() throws ClassNotFoundException, SQLException {
		init();
		Flight test;
		
	}
	
	@Test
	void updateTest() throws ClassNotFoundException, SQLException {
		init();
		Flight test;
		
	}

}
