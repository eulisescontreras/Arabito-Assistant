package arabitogrill.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Workers {
	private Integer id;
	private String name;
	private String email;
	private String mobile;
	private String charge;
	private BigDecimal dailyS;
	private Date birth;
	
        public Workers(){}
        public Workers(String name, String email, String mobile, String charge, BigDecimal dailyS, Date birth){
            this.name = name;
            this.email = email;
            this.mobile = mobile;
            this.charge = charge;
            this.dailyS = dailyS;
            this.birth = birth;
        } 

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
	public BigDecimal getDailyS() {
		return dailyS;
	}
	public void setDailyS(BigDecimal dailyS) {
		this.dailyS = dailyS;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
}
