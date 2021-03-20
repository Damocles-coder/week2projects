package com.smoothstack.utopia.entities;

import java.time.LocalDateTime;

/**
 * @author dyltr
 * Super object containing flight and route objects
 * created from join statements as accessing each route from each flight
 * is expensive computationally
 */
public class FlightRoute {
	private Flight flight;
	private Route route;
	
	public FlightRoute(int id, int routeId, int airplaneId, LocalDateTime departure, 
			LocalDateTime arrival, int reservedSeats, float seatPrice, 
			String source, String destination) {
		flight = new Flight(id,routeId,airplaneId,departure,arrival,reservedSeats,seatPrice);
		route = new Route(routeId,source,destination);
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Route getRoute() {
		return route;
	}
	public void setRoute(Route route) {
		this.route = route;
	}
	
}
