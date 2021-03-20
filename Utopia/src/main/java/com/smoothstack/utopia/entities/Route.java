package com.smoothstack.utopia.entities;

public class Route {
	private final int id;
	private String source;
	private String destination;
	
	public Route(int id, String source, String destination) {
		this.id = id;
		this.source = source;
		this.destination = destination;
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
