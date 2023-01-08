package com.semi.artist;

public class Artists {
	private String id;
	private String name;
	public Artists() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Artists(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
