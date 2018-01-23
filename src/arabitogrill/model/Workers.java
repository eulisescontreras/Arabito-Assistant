package arabitogrill.model;

import java.util.Date;

public class Workers {
	private Integer id;
	private String name;
	private String email;
	private String mobile;
	private String charge;
	private Double dailyS;
	private Date birth;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCharge() {
		return charge;
	}
	public void setCharge(String charge) {
		this.charge = charge;
	}
	public Double getDailyS() {
		return dailyS;
	}
	public void setDailyS(Double dailyS) {
		this.dailyS = dailyS;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
