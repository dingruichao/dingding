package com.ruhua.domain.geo;

public class POI {
	private String name;
	private Coordinate lnglat;
	private String type;
	private String address;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Coordinate getCoordinate() {
		return lnglat;
	}
	public void setCoordinate(Coordinate lnglat) {
		this.lnglat = lnglat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
