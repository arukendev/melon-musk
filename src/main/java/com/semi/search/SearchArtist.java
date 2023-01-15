package com.semi.search;

public class SearchArtist {
	private String img;
	private String id;
	private String name;
	private String info;
	public SearchArtist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchArtist(String img, String id, String name, String info) {
		super();
		this.img = img;
		this.id = id;
		this.name = name;
		this.info = info;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
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
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
}
