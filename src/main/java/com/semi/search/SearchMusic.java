package com.semi.search;

public class SearchMusic {
	private String number;
	private String musicId;
	private String musicName;
	private String artistName;
	public SearchMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchMusic(String number, String musicId, String musicName, String artistName) {
		super();
		this.number = number;
		this.musicId = musicId;
		this.musicName = musicName;
		this.artistName = artistName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getMusicName() {
		return musicName;
	}
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	
}
