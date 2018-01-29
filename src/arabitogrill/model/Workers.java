package arabitogrill.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Workers {
	private Integer id;
	private String first_name;
    private String second_name;
    private String surname;
    private String second_surname;
	private String email;
	private String mobile;
	private String charge;
	private BigDecimal dailyS;
	private Date birth;
	
        public Workers(){}
        public Workers(String first_name, String second_name, String surname, String second_surname, String email, String mobile, String charge, BigDecimal dailyS, Date birth){
            this.first_name = first_name;
            this.second_name = second_name;
            this.surname = surname;
            this.second_surname = second_surname;
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
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
        public String getSecondName() {
		return second_name;
	}
	public void setSecondName(String second_name) {
		this.second_name = second_name;
	}
        public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
        public String getSecondSurname() {
		return second_surname;
	}
	public void setSecondSurname(String second_surname) {
		this.second_surname = second_surname;
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
