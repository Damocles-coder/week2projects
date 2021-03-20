package utopia;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.Test;
import com.smoothstack.utopia.jdbc.Util;
import com.smoothstack.utopia.jdbc.UtopiaUtil;

class UtilTest {

	@Test
	void getConnectionTest() {
		Util test = new UtopiaUtil("jdbc:mysql://localhost:3306/utopia", "Dylan", "Tran");

		assertNotNull(test);
		Connection test2;
		try {
			test2 = test.getConnection();
			assertNotNull(test2);
			test2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
