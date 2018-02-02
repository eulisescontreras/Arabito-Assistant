package arabitogrill.model;

import java.math.BigDecimal;
import java.sql.Date;

public class Bills {
	private Integer id;
	private BigDecimal amount;
	private String spend;
	private Date createdAt;
	private Date expirationAt;
        private String nro;
        private String name;
        private String observation;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getSpend() {
		return spend;
	}
	public void setSpend(String spend) {
		this.spend = spend;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getExpirationAt() {
		return expirationAt;
	}
	public void setExpirationAt(Date expirationAt) {
		this.expirationAt = expirationAt;
	}
        public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
        public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
        public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	
}
