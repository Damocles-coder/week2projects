package com.smoothstack.utopia.entities;

public class BookingPayment {
	private int bookingId;
	private String stripeId;
	private boolean refunded;
	public BookingPayment(int bookingId, String stripeId, boolean refunded) {
		super();
		this.bookingId = bookingId;
		this.stripeId = stripeId;
		this.refunded = refunded;
	}
	public int getBookingId() {
		return bookingId;
	}
	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}
	public String getStripeId() {
		return stripeId;
	}
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	public boolean isRefunded() {
		return refunded;
	}
	public void setRefunded(boolean refunded) {
		this.refunded = refunded;
	}
}
