package hibernate;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity (name = "reimbursement_requests")
public class ReimburseRequest implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_generator")
	@SequenceGenerator(name="id_generator", sequenceName = "reimburse_request_id", allocationSize = 1)
	@Column (name = "request_id")
	private int requestId;
	
//	private int employeeId;
	
	@Column
	private String type;
	
	@Column
	private String description;
	
	@Column
	private double amount;
	
	@Column (name = "time_stamp")
	private Date timeStamp;
	//private java.util.Date timeStamp;
	
	@ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
	private EmployeeInfo eRequest;
	
	@Column
	private String status;
	
	public ReimburseRequest() {
		super();
	}
	
	public ReimburseRequest(String type, String description, double amount, Date timeStamp, String status) {
		super();
		this.type = type;
		this.description = description;
		this.amount = amount;
		this.timeStamp = timeStamp;
		this.status = status;
	}
	
	public int getRequestId() {
		return requestId;
	}
	
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}
	
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public void setEmployeeInfo(EmployeeInfo eRequest) {
		this.eRequest = eRequest;
	}
	
	@Override
	public String toString() {
		return "[" + requestId + ", " + type + ", " + description + ", " + amount + ", " + timeStamp + "]";
	}
}
