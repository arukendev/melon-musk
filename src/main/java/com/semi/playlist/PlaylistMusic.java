package com.semi.playlist;

public class PlaylistMusic {
	
	private String pl_name;
	private String al_img;
	private String mu_name;
	private String ar_name;
	private String mu_lyrics;
	private String pl_id;
	private String mu_id;
	
	public PlaylistMusic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PlaylistMusic(String pl_name,String al_img, String mu_name, String ar_name, String mu_lyrics,String pl_id,String mu_id) {
		super();
		this.pl_name = pl_name;
		this.al_img = al_img;
		this.mu_name = mu_name;
		this.ar_name = ar_name;
		this.mu_lyrics = mu_lyrics;
		this.pl_id = pl_id;
		this.mu_id = mu_id;
	}
	
	
	public String getAl_img() {
		return al_img;
	}
	public void setAl_img(String al_img) {
		this.al_img = al_img;
	}
	public String getMu_name() {
		return mu_name;
	}
	public void setMu_name(String mu_name) {
		this.mu_name = mu_name;
	}
	public String getAr_name() {
		return ar_name;
	}
	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}
	public String getMu_lyrics() {
		return mu_lyrics;
	}
	public void setMu_lyrics(String mu_lyrics) {
		this.mu_lyrics = mu_lyrics;
	}
	
	public String getPl_id() {
		return pl_id;
	}
	public void setPl_id(String pl_id) {
		this.pl_id = pl_id;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public String getMu_id() {
		return mu_id;
	}
	public void setMu_id(String mu_id) {
		this.mu_id = mu_id;
	}
	
	
	
}
