package com.semi.artist;

public class Artist {
	private String id;
	private String name;
	private String img;
	private String type;
	private String company;
	private String debut;
	private String birth;
	private String info;
	public Artist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Artist(String id, String name, String img, String type, String company, String debut, String birth,
			String info) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.type = type;
		this.company = company;
		this.debut = debut;
		this.birth = birth;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDebut() {
		return debut;
	}
	public void setDebut(String debut) {
		this.debut = debut;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
