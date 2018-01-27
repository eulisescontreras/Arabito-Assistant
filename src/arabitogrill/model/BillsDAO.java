package arabitogrill.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BillsDAO {
	private Connection connection;
	
	public BillsDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}

	public void create(Bills bill) {
		String sql = "INSERT INTO public.\"Bills\" (AMOUNT, CREATED_AT, EXPIRATION_AT, SPEND) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setBigDecimal(1, bill.getAmount());
			statement.setDate(2, bill.getCreatedAt());
			statement.setDate(3, bill.getExpirationAt());
			statement.setString(4, bill.getSpend());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}

	public List<Bills> read(Integer idBill) {
		List<Bills> billsList = new ArrayList<>();
		
		String sql = "SELECT * FROM public.\"Bills\"" + ((idBill!=null) ? " WHERE ID = " + idBill + "" : "");
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Bills bill = new Bills();
				
				bill.setId(result.getInt("id"));
				bill.setAmount(result.getBigDecimal("amount"));
				bill.setSpend(result.getString("spend"));
				bill.setCreatedAt(result.getDate("created_at"));
				bill.setExpirationAt(result.getDate("expiration_at"));
				
				billsList.add(bill);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return billsList;
	}
	
	public ObservableList<Bills> getObservableBills() {
		ObservableList<Bills> billsList = FXCollections.observableArrayList();
		
		String sql = "SELECT " +
                             "  * " +
                             "FROM " +
                             "  public.\"Bills\";";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet result = statement.executeQuery();
			
			while(result.next()) {
				Bills bill = new Bills();
				
				bill.setId(result.getInt("id"));
				bill.setAmount(result.getBigDecimal("amount"));
				bill.setSpend(result.getString("spend"));
				bill.setCreatedAt(result.getDate("created_at"));
				bill.setExpirationAt(result.getDate("expiration_at"));
				
				billsList.add(bill);
			}
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		return billsList;
	}
	
	public void update(Bills bill) {
		String sql = "UPDATE public.\"Bills\" SET (AMOUNT, CREATED_AT, EXPIRATION_AT, SPEND) VALUES (?, ?, ?, ?) WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setBigDecimal(1, bill.getAmount());
			statement.setDate(2, bill.getCreatedAt());
			statement.setDate(3, bill.getExpirationAt());
			statement.setString(4, bill.getSpend());
			statement.setInt(5, bill.getId());
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Integer idBill) {
		String sql = "DELETE FROM public.\"Bills\" WHERE ID=?";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, idBill);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
}
