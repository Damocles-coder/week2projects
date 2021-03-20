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
