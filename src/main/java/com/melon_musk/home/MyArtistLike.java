package com.melon_musk.home;

public class MyArtistLike {
	private String id;
	private String name;
	private String img;
	
	public MyArtistLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MyArtistLike(String id, String name, String img) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
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
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
}
