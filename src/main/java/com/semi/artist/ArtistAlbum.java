package com.semi.artist;

public class ArtistAlbum {
	private String id;
	private String img;
	private String name;
	private String date;
	private String num;
	private String type;
	
	public ArtistAlbum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArtistAlbum(String id, String img, String name, String date, String num, String type) {
		super();
		this.id = id;
		this.img = img;
		this.name = name;
		this.date = date;
		this.num = num;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
}
