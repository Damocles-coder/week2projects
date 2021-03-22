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
	
	public FlightRoute(Flight flight, Route route) {
		this.flight = flight;
		this.route = route;
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
