package com.smoothstack.utopia.entities;

public class FlightBooking {
	private int bookingId;
	private int flightId;
	private int classId;
	public FlightBooking(int flightId, int bookingId, int classId) {
		this.bookingId = bookingId;
		this.flightId = flightId;
		this.classId = classId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getflightId() {
		return flightId;
	}
	public void setflightId(int flightId) {
		this.flightId = flightId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
}
