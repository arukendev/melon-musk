package com.semi.album;

public class Album {
	private String id;
	private String name;
	private String img;
	private String type;
	private String date;
	private String genre;
	private String publisher;
	private String agency;
	private String info;
	public Album() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Album(String id, String name, String img, String type, String date, String genre, String publisher,
			String agency, String info) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.type = type;
		this.date = date;
		this.genre = genre;
		this.publisher = publisher;
		this.agency = agency;
		this.info = info;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
