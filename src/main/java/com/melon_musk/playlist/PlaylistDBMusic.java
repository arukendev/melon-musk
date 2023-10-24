package com.melon_musk.playlist;

public class PlaylistDBMusic {
	private String id;
	private String arId;
	private String arName;
	private String alId;
	private String alName;
	private String alImg;
	private String date;
	private String name;
	private String genre;
	private String lyrics;
	private String link;
	
	public PlaylistDBMusic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PlaylistDBMusic(String id, String arId, String arName, String alId, String alName, String alImg, String date,
			String name, String genre, String lyrics, String link) {
		super();
		this.id = id;
		this.arId = arId;
		this.arName = arName;
		this.alId = alId;
		this.alName = alName;
		this.alImg = alImg;
		this.date = date;
		this.name = name;
		this.genre = genre;
		this.lyrics = lyrics;
		this.link = link;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArId() {
		return arId;
	}

	public void setArId(String arId) {
		this.arId = arId;
	}

	public String getArName() {
		return arName;
	}

	public void setArName(String arName) {
		this.arName = arName;
	}

	public String getAlId() {
		return alId;
	}

	public void setAlId(String alId) {
		this.alId = alId;
	}

	public String getAlName() {
		return alName;
	}

	public void setAlName(String alName) {
		this.alName = alName;
	}

	public String getAlImg() {
		return alImg;
	}

	public void setAlImg(String alImg) {
		this.alImg = alImg;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	
	
	
	
	
	
}
