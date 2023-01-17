package com.semi.search;

public class SearchAlbum {
	private String img = "";
	private String type = "";
	private String id = "";
	private String name = "";
	private String date = "";
	private String num = "";
	private String artist = "";
	public SearchAlbum() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchAlbum(String img, String type, String id, String name, String date, String num, String artist) {
		super();
		this.img = img;
		this.type = type;
		this.id = id;
		this.name = name;
		this.date = date;
		this.num = num;
		this.artist = artist;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
}
