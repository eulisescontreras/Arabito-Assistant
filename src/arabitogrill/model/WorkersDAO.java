package arabitogrill.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WorkersDAO {
	private Connection connection;
	
	public WorkersDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void create(Workers worker) {
		String sql = "INSERT INTO WORKERS (NAME, EMAIL, MOBILE, DAILY_S, CHARGE, BIRTH) VALUES (?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, worker.getName());
			statement.setString(2, worker.getEmail());
			statement.setString(3, worker.getMobile());
			statement.setBigDecimal(4, worker.getDailyS());
			statement.setString(5, worker.getCharge());
			statement.setDate(6, worker.getBirth());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<Workers> read(String nameWorker) {
		List<Workers> workersList = new ArrayList<>();
		
		String sql = "SELECT * FROM WORKERS WHERE NAME LIKE '%" + nameWorker + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Workers worker = new Workers();
				
				worker.setId(result.getInt("id"));
				worker.setName(result.getString("name"));
				worker.setEmail(result.getString("email"));
				worker.setMobile(result.getString("mobile"));
				worker.setCharge(result.getString("charge"));
				worker.setDailyS(result.getBigDecimal("daily_s"));
				worker.setBirth(result.getDate("birth"));
				
				workersList.add(worker);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return workersList;
	}
	
	public void update(Workers worker) {
		String sql = "UPDATE WORKERS SET (NAME, EMAIL, MOBILE, DAILY_S, CHARGE, BIRTH) VALUES (?, ?, ?, ?, ?, ?) WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, worker.getName());
			statement.setString(2, worker.getEmail());
			statement.setString(3, worker.getMobile());
			statement.setBigDecimal(4, worker.getDailyS());
			statement.setString(5, worker.getCharge());
			statement.setDate(6, worker.getBirth());
			statement.setInt(7, worker.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Integer idWorker) {
		String sql = "DELETE FROM WORKERS WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idWorker);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
