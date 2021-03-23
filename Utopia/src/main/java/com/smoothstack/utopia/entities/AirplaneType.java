package com.smoothstack.utopia.entities;

/**
 * @author dyltr will only create when the seats are requested
 */
public class AirplaneType {
	private int id;
	private int capacity;
	private int capacity2;
	private int capacity3;

	public int getCapacity2() {
		return capacity2;
	}

	public void setCapacity2(int capacity2) {
		this.capacity2 = capacity2;
	}

	public int getCapacity3() {
		return capacity3;
	}

	public void setCapacity3(int capacity3) {
		this.capacity3 = capacity3;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public AirplaneType(int id, int capacity, int capacity2, int capacity3) {
		this.id = id;
		this.capacity = capacity;
		this.capacity2 = capacity2;
		this.capacity3 = capacity3;
	}

	public AirplaneType(int capacity, int capacity2, int capacity3) {
		this.capacity = capacity;
		this.capacity2 = capacity2;
		this.capacity3 = capacity3;
		id = 0;
	}

	public int getId() {
		return id;
	}

	public int getCapacity() {
		return capacity;
	}
}
