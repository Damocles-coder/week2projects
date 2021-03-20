package com.smoothstack.utopia.entities;

import java.time.LocalDateTime;

public class Flight {
	private final int id;
	private int routeId;
	private int airplaneId;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private int reservedSeats;
	
	public Flight(int id, int routeId, int airplaneId, LocalDateTime departure, LocalDateTime arrival, int reservedSeats) {
		this.id=id;
		this.routeId=routeId;
		this.setAirplaneId(airplaneId);
		this.departure = departure;
		this.arrival = arrival;
		this.setReservedSeats(reservedSeats);
	}
	
	/**
	 * ID should not change
	 * @return
	 */
	public int getId() {
		return id;
	}
	public int getRouteId() {
		return routeId;
	}
	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}
	public LocalDateTime getDeparture() {
		return departure;
	}
	public void setDeparture(LocalDateTime departure) {
		this.departure = departure;
	}
	public LocalDateTime getArrival() {
		return arrival;
	}
	public void setArrival(LocalDateTime arrival) {
		this.arrival = arrival;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public int getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(int airplaneId) {
		this.airplaneId = airplaneId;
	}
}
