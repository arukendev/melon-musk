package com.semi.album;

public class AlbumMusic {
	private int cd;
	private int num;
	private String id;
	private String name;
	private String artistId;
	private String artist;
	public AlbumMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AlbumMusic(int cd, int num, String id, String name, String artistId, String artist) {
		super();
		this.cd = cd;
		this.num = num;
		this.id = id;
		this.name = name;
		this.artistId = artistId;
		this.artist = artist;
	}
	public int getCd() {
		return cd;
	}
	public void setCd(int cd) {
		this.cd = cd;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	
}
