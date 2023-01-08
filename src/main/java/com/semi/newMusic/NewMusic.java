package com.semi.newMusic;

public class NewMusic {
	private int rank;
	private String img;
	private String music;
	private String musicId;
	private String artist;
	private String artistId;
	private String album;
	private String albumId;
	public NewMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NewMusic(int rank, String img, String music, String musicId, String artist, String artistId, String album,
			String albumId) {
		super();
		this.rank = rank;
		this.img = img;
		this.music = music;
		this.musicId = musicId;
		this.artist = artist;
		this.artistId = artistId;
		this.album = album;
		this.albumId = albumId;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getMusic() {
		return music;
	}
	public void setMusic(String music) {
		this.music = music;
	}
	public String getMusicId() {
		return musicId;
	}
	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getArtistId() {
		return artistId;
	}
	public void setArtistId(String artistId) {
		this.artistId = artistId;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getAlbumId() {
		return albumId;
	}
	public void setAlbumId(String albumId) {
		this.albumId = albumId;
	}
	
	
}
