package com.smoothstack.utopia.services;

/**
 * @author dyltr
 * Contains single instances of services
 * -Not thread safe in creation
 * If I was doing a full-stack application,
 * I would store each service in the context
 */
public class ServiceManager {
	private static FlightService flightService = null;

	public static FlightService getFlightService() {
		return flightService;
	}

	public static void setFlightService(FlightService flightService) {
		ServiceManager.flightService = flightService;
	}
	
}