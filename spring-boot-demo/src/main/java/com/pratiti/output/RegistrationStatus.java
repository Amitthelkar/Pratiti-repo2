package com.pratiti.output;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RegistrationStatus {

	private boolean status;
	private String message;
	private int custid;
	private LocalDateTime localDateTime;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getCustid() {
		return custid;
	}
	public void setCustid(int custid) {
		this.custid = custid;
	}
	public LocalDateTime getLocalDate() {
		return localDateTime;
	}
	public void setLocalDate(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	
	
	
	
}
