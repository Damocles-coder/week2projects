package com.smoothstack.utopia.entities;

/**
 * @author dyltr
 * Object created from flight status view
 * Used to show flight information and available seats
 */
public class FlightStatus {
	private Flight flight;
	private int capacity;
	private int passengerCount;
	private int availableSeats;
	public FlightStatus(Flight flight, int capacity, int passengerCount, int availableSeats) {
		super();
		this.flight = flight;
		this.capacity = capacity;
		this.passengerCount = passengerCount;
		this.availableSeats = availableSeats;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getPassengerCount() {
		return passengerCount;
	}
	public void setPassengerCount(int passengerCount) {
		this.passengerCount = passengerCount;
	}
	public int getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}
}
