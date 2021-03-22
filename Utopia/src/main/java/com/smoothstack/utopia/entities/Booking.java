package com.smoothstack.utopia.entities;

/**
 * @author dyltr
 * Object to override ticket cancellation
 */
public class Booking {
	private int id;
	private boolean isActive;
	private String confirmationCode;
	public int getId() {
		return id;
	}
	public Booking(int id, boolean isActive, String confirmationCode) {
		this.id = id;
		this.isActive = isActive;
		this.confirmationCode = confirmationCode;
	}
	
	public Booking(boolean isActive) {
		this.id = 0;
		this.isActive = isActive;
		this.confirmationCode = this.hashCode()+"";
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
}
