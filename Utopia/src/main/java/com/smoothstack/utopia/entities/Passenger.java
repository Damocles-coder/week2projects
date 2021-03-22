package com.smoothstack.utopia.entities;

import java.time.LocalDate;

/**
 * @author dyltr
 */
public class Passenger {
	private int id;
	private int bookingId;
	private String givenName;
	private String familyName;
	private LocalDate dob;
	private String gender;
	private String address;
	
	public Passenger(int id, int bookingId, String givenName, String familyName, LocalDate dob,
			String gender, String address) {
		this.address = address;
		this.bookingId = bookingId;
		this.dob = dob;
		this.familyName = familyName;
		this.gender = gender;
		this.givenName = givenName;
		this.id = id;
	}
	
	public Passenger(int bookingId, String givenName, String familyName, LocalDate dob,
			String gender, String address) {
		this.address = address;
		this.bookingId = bookingId;
		this.dob = dob;
		this.familyName = familyName;
		this.gender = gender;
		this.givenName = givenName;
		this.id = 0;
	}

	public int getId() {
		return id;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
