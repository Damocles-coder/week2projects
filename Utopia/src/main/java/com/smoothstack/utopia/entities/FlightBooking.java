package com.smoothstack.utopia.entities;

public class FlightBooking {
	private int bookingId;
	private int flightId;
	public FlightBooking(int flightId, int bookingId) {
		super();
		this.bookingId = bookingId;
		this.flightId = flightId;
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
}
