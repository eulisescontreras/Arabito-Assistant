package arabitogrill.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaysDAO {
	private Connection connection;
	
	public DaysDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void create(Days day) {
		String sql = "INSERT INTO DAYS (DATE, WORKER, HOURS) VALUES (?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDate(1, day.getDate());
			statement.setInt(2, day.getWorker());
                        statement.setTime(3, day.getHours());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<Days> read(Date dateDay) {
		List<Days> daysList = new ArrayList<>();
		
		String sql = "SELECT * FROM DAYS" + 
					((dateDay!=null) ? 
							" WHERE DATE = " + dateDay + "" : "");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Days day = new Days();
				
				day.setId(result.getInt("id"));
				day.setWorker(result.getInt("worker"));
				day.setDate(result.getDate("date"));
				day.setHours(result.getTime("hours"));
                                
				daysList.add(day);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return daysList;
	}
	
	public void update(Days day) {
		String sql = "UPDATE DAYS SET (DATE, WORKER, HOURS) VALUES (?, ?, ?) WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDate(1, day.getDate());
			statement.setInt(2, day.getWorker());
                        statement.setTime(3, day.getHours());
			statement.setInt(4, day.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Integer idDay) {
		String sql = "DELETE FROM DAYS WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idDay);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
