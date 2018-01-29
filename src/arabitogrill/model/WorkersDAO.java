package arabitogrill.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WorkersDAO {
	private Connection connection;
	
	public WorkersDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void create(Workers worker) {
//		String sql = "INSERT INTO workers (NAME, EMAIL, MOBILE, DAILY_S, CHARGE, BIRTH) VALUES (?, ?, ?, ?, ?, ?)";

		String sql = "INSERT INTO public.\"Workers\" (FIRST_NAME, SECOND_NAME, SURNAME, SECOND_SURNAME, EMAIL, MOBILE, DAILY_S, CHARGE, BIRTH) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
                    PreparedStatement statement = connection.prepareStatement(sql);

                    statement.setString(1, worker.getFirstName());
                    statement.setString(2, worker.getSecondName());
                    statement.setString(3, worker.getSurname());
                    statement.setString(4, worker.getSecondSurname());
                    statement.setString(5, worker.getEmail());
                    statement.setString(6, worker.getMobile());
                    statement.setBigDecimal(7, worker.getDailyS());
                    statement.setString(8, worker.getCharge());
                    statement.setDate(9, worker.getBirth());

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
		
		String sql = "SELECT * FROM public.\"Workers\" WHERE NAME LIKE '%" + nameWorker + "%'";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
                            Workers worker = new Workers();

                            worker.setId(result.getInt("id"));
                            worker.setFirstName(result.getString("first_name"));
                            worker.setSecondName(result.getString("second_name"));
                            worker.setSurname(result.getString("surname"));
                            worker.setSecondSurname(result.getString("second_surname"));
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
	
	public ObservableList<Workers> getObservableWorker() {
		ObservableList<Workers> workersList = FXCollections.observableArrayList();
		
		String sql = "SELECT " +
                             "  * " +
                             "FROM " +
                             "  public.\"Workers\";";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
                            Workers worker = new Workers();

                            worker.setId(result.getInt("id"));
                            worker.setFirstName(result.getString("first_name"));
                            worker.setSecondName(result.getString("second_name"));
                            worker.setSurname(result.getString("surname"));
                            worker.setSecondSurname(result.getString("second_surname"));
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
		String sql = "UPDATE public.\"Workers\" SET FIRST_NAME='" + worker.getFirstName()+ "', "
                                + "SECOND_NAME='" + worker.getSecondName()+ "', "
                                + "SURNAME='" + worker.getSurname()+ "', "
                                + "SECOND_SURNAME='" + worker.getSecondSurname()+ "', "
				+ "EMAIL='" + worker.getEmail() + "', "
				+ "MOBILE='" + worker.getMobile() + "', "
				+ "DAILY_S=" + worker.getDailyS() + ", "
				+ "CHARGE='"+ worker.getCharge() +"', "
				+ "BIRTH='"+ worker.getBirth() +"' "
				+ "WHERE ID=" + worker.getId() + ";";		
		try {
			System.out.println(sql);
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Integer idWorker) {
		String sql = "DELETE FROM public.\"Workers\" WHERE ID=?";
		
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
