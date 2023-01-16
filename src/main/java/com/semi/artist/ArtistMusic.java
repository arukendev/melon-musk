package com.semi.artist;

public class ArtistMusic {
	private int rank;
	private String id;
	private String name;
	public ArtistMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ArtistMusic(int rank, String id, String name) {
		super();
		this.rank = rank;
		this.id = id;
		this.name = name;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
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
	
}
