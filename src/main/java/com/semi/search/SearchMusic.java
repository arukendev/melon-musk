package com.semi.search;

public class SearchMusic {
	private String musicId;
	private String musicName;
	private String artistId;
	private String artistName;
	private String albumId;
	private String albumName;
	public SearchMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchMusic(String musicId, String musicName, String artistId, String artistName, String albumId,
			String albumName) {
		super();
		this.musicId = musicId;
		this.musicName = musicName;
		this.artistId = artistId;
		this.artistName = artistName;
		this.albumId = albumId;
		this.albumName = albumName;
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
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getArtistName() {
		return artistName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	
}
