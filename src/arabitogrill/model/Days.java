package arabitogrill.model;

import java.sql.Date;

public class Days {
	private Integer id;
	private Date date;
	private Integer worker;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getWorker() {
		return worker;
	}
	public void setWorker(Integer worker) {
		this.worker = worker;
	}
}
