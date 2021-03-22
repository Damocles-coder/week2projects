package com.smoothstack.utopia.entities;

/**
 * @author dyltr
 * will only create when the seats are requested
 */
public class AirplaneType {
	private int id;
	public AirplaneType(int id, int capacity) {
		super();
		this.id = id;
		this.capacity = capacity;
	}
	private int capacity;
	public int getId() {
		return id;
	}
	public int getCapacity() {
		return capacity;
	}
}
