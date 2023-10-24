package com.melon_musk.playlist;

import java.util.Date;

public class Playlist {

	private int pl_id;
	private String pl_name;
	private int pl_view;
	private int pl_like;
	private Date pl_date;
	private String pl_au_id;
	private String pl_text;
	private String pl_img;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int pl_id, String pl_name, int pl_view, int pl_like, Date pl_date, String pl_au_id, String pl_text,
			String pl_img) {
		super();
		this.pl_id = pl_id;
		this.pl_name = pl_name;
		this.pl_view = pl_view;
		this.pl_like = pl_like;
		this.pl_date = pl_date;
		this.pl_au_id = pl_au_id;
		this.pl_text = pl_text;
		this.pl_img = pl_img;
	}
	public int getPl_id() {
		return pl_id;
	}
	public void setPl_id(int pl_id) {
		this.pl_id = pl_id;
	}
	public String getPl_name() {
		return pl_name;
	}
	public void setPl_name(String pl_name) {
		this.pl_name = pl_name;
	}
	public int getPl_view() {
		return pl_view;
	}
	public void setPl_view(int pl_view) {
		this.pl_view = pl_view;
	}
	public int getPl_like() {
		return pl_like;
	}
	public void setPl_like(int pl_like) {
		this.pl_like = pl_like;
	}
	public Date getPl_date() {
		return pl_date;
	}
	public void setPl_date(Date pl_date) {
		this.pl_date = pl_date;
	}
	public String getPl_au_id() {
		return pl_au_id;
	}
	public void setPl_au_id(String pl_au_id) {
		this.pl_au_id = pl_au_id;
	}
	public String getPl_text() {
		return pl_text;
	}
	public void setPl_text(String pl_text) {
		this.pl_text = pl_text;
	}
	public String getPl_img() {
		return pl_img;
	}
	public void setPl_img(String pl_img) {
		this.pl_img = pl_img;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}