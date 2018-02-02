package arabitogrill.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection( ){
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/arabito-assistant", "root", "23795053");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
