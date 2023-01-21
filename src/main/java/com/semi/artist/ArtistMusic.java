package com.semi.artist;

public class ArtistMusic {
	private String rank;
	private String id;
	private String name;
	private String artist;
	public ArtistMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArtistMusic(String rank, String id, String name, String artist) {
		super();
		this.rank = rank;
		this.id = id;
		this.name = name;
		this.artist = artist;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
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
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
