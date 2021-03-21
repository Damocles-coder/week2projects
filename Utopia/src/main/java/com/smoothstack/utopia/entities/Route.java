package com.smoothstack.utopia.entities;

public class Route {
	private final int id;
	private String source;
	private String destination;
	private String sourceCity;
	private String destinationCity;
	
	public Route(int id, String source, String destination, String sourceCity, String destinationCity) {
		super();
		this.id = id;
		this.source = source;
		this.destination = destination;
		this.sourceCity = sourceCity;
		this.destinationCity = destinationCity;
	}

	public String getSourceCity() {
		return sourceCity;
	}

	public void setSourceCity(String sourceCity) {
		this.sourceCity = sourceCity;
	}

	public String getDestinationCity() {
		return destinationCity;
	}

	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}

	public int getId() {
		return id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
}
