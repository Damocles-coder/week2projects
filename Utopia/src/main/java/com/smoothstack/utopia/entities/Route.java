package com.smoothstack.utopia.entities;

public class Route {
	private final int id;
	private Airport source;
	private Airport destination;
	
	public Route(int id, String source, String destination, String sourceCity, String destinationCity) {
		this.id = id;
		this.setSource(new Airport(source,sourceCity));
		this.setDestination(new Airport(destination, destinationCity));
	}
	
	public Route(Airport source, Airport destination) {
		this.id = 0;
		this.source = source;
		this.destination = destination;
	}

	public int getId() {
		return id;
	}

	public Airport getSource() {
		return source;
	}

	public void setSource(Airport source) {
		this.source = source;
	}

	public Airport getDestination() {
		return destination;
	}

	public void setDestination(Airport destination) {
		this.destination = destination;
	}
	
}
