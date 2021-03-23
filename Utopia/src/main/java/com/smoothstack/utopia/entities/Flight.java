package com.smoothstack.utopia.entities;

import java.time.LocalDateTime;

public class Flight {
	private final int id;
	private int routeId;
	private int airplaneId;
	private LocalDateTime departure;
	private LocalDateTime arrival;
	private int reservedSeats;
	private int reservedSeats2;
	private int reservedSeats3;
	private float seatPrice;

	public Flight(int id, int routeId, int airplaneId, LocalDateTime departure, LocalDateTime arrival,
			int reservedSeats, int reservedSeats2, int reservedSeats3, float seatPrice) {
		this.id = id;
		this.routeId = routeId;
		this.setAirplaneId(airplaneId);
		this.departure = departure;
		this.arrival = arrival;
		this.reservedSeats = reservedSeats;
		this.seatPrice = seatPrice;
		this.reservedSeats2 = reservedSeats2;
		this.reservedSeats3 = reservedSeats3;
	}

	public int getReservedSeats2() {
		return reservedSeats2;
	}

	public void setReservedSeats2(int reservedSeats2) {
		this.reservedSeats2 = reservedSeats2;
	}

	public int getReservedSeats3() {
		return reservedSeats3;
	}

	public void setReservedSeats3(int reservedSeats3) {
		this.reservedSeats3 = reservedSeats3;
	}

	public Flight(int routeId, int airplaneId, LocalDateTime departure, LocalDateTime arrival, int reservedSeats,
			int reservedSeats2, int reservedSeats3, float seatPrice) {
		this.routeId = routeId;
		this.setAirplaneId(airplaneId);
		this.departure = departure;
		this.arrival = arrival;
		this.setReservedSeats(reservedSeats);
		this.seatPrice = seatPrice;
		id = this.hashCode();
		this.reservedSeats2 = reservedSeats2;
		this.reservedSeats3 = reservedSeats3;
	}

	/**
	 * ID should not change
	 * 
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

	public float getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(float seatPrice) {
		this.seatPrice = seatPrice;
	}

	@Override
	public int hashCode() {
		int result = routeId * airplaneId + "yolo".hashCode();
		return result;
	}
}
