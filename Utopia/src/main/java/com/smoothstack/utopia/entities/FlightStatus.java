package com.smoothstack.utopia.entities;

/**
 * @author dyltr
 * Object created from flight status view
 * Used to show flight information and available seats
 */
public class FlightStatus {
	private Flight flight;
	private int capacity;
	private int capacity2;
	private int capacity3;
	public FlightStatus(Flight flight, int capacity, int capacity2, int capacity3) {
		super();
		this.flight = flight;
		this.capacity = capacity;
		this.capacity2 = capacity2;
		this.capacity3 = capacity3;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public int getCapacity2() {
		return capacity2;
	}
	public void setCapacity2(int capacity2) {
		this.capacity2 = capacity2;
	}
	public int getCapacity3() {
		return capacity3;
	}
	public void setCapacity3(int capacity3) {
		this.capacity3 = capacity3;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
}
