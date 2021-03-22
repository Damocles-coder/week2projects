package com.smoothstack.utopia.entities;

public class BookingUser {
	private int bookingId;
	private int userId;
	public BookingUser(int bookingId, int userId) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
