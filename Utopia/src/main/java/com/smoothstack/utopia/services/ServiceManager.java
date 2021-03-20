/**
 * 
 */
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
	
	public static FlightService getFlight() {
		if(flightService == null) {
			flightService = new FlightService();
		}
		return flightService;
	}
}
