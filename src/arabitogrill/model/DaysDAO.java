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
		String sql = "INSERT INTO public.\"Days\" (DATE, WORKER, HOURS, TIPS) VALUES (?, ?, ?, ?)";
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setDate(1, day.getDate());
			statement.setInt(2, day.getWorker());
                        statement.setTime(3, day.getHours());
                        statement.setDouble(4, day.getTips());
			
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
		
		String sql = "SELECT * FROM public.\"Days\"" + 
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
                                day.setTips(result.getDouble("tips"));
                                
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
		
                String sql = "UPDATE public.\"Days\" SET DATE='" + day.getDate()+ "', "
                                + "WORKER=" + day.getWorker()+ ", "
                                + "HOURS='" + day.getHours()+ "', "
                                + "TIPS=" + day.getTips()+ " "
				+ "WHERE ID=" + day.getId() + ";";
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.execute();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public void delete(Integer idDay) {
		String sql = "DELETE FROM public.\"Days\" WHERE ID=?";
		
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
        
        public List<InformationUserCalendar> read(int userId) {
            
            List<InformationUserCalendar> listInformation = new ArrayList<>();
            String sql = "select x.daily_s, x.id, x.tips, (x.minutes*(x.daily_s/60))+(x.hour*x.daily_s) as amount, x.date, x.year, x.month, x.day, x.minutes, x.hour from "+
                         "(SELECT w.daily_s,d.id,d.tips, cast((cast(cast(to_char(((d.hours)::time),'HH24') as real)*w.daily_s as character varying(100)) || '.' || cast(cast(to_char(((d.hours)::time),'MI') as real)*w.daily_s as character varying(100))) as real) as amount,d.date,to_char(((d.date)::date),'YYYY') as year ,to_char(((d.date)::date),'MM') as month ,to_char(((d.date)::date),'DD') as day, cast(to_char(((d.hours)::time),'MI') as real) as minutes, cast(to_char(((d.hours)::time),'HH24') as real) as hour "+
                         "FROM public.\"Days\" d "+
                         "INNER JOIN public.\"Workers\" w on d.worker = w.id "+
                         "WHERE w.id = ?) x ";
            try {
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setInt(1, userId);
                    ResultSet result = statement.executeQuery();
			
                    while(result.next()) {
                            InformationUserCalendar informationDay = new InformationUserCalendar(
                                result.getDouble("amount"),
                                result.getDouble("tips"),
                                result.getInt("year"),
                                result.getInt("month"),
                                result.getInt("day"),
                                result.getInt("minutes"),
                                result.getDouble("hour"),
                                result.getDate("date"),
                                result.getInt("id"),
                                result.getDouble("daily_s")
                            );
                            double minutes = informationDay.getMinutes()*informationDay.getDaily_s()/60;
                            double hour = informationDay.getHour()*informationDay.getDaily_s();
                            int firstIntValue = (int)hour;
                            int secondIntValue = (int)minutes;
                            String value = firstIntValue+"."+secondIntValue;
                            informationDay.setAmount(Double.parseDouble(value));
                            listInformation.add(informationDay);
                    }

                    result.close();
                    statement.close();
            } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    throw new RuntimeException(e);
            }
            return listInformation;
        }
}
