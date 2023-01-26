package com.semi.home;

public class MyMusicLike {
	private String id;
	private String name;
	private String img;
	private String artist;
	public MyMusicLike() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyMusicLike(String id, String name, String img, String artist) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.artist = artist;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
}
