package com.semi.playlist;

import java.util.Date;

public class Playlist {

	private int pl_id;
	private String pl_name;
	private int pl_view;
	private int pl_like;
	private Date pl_date;
	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Playlist(int pl_id, String pl_name, int pl_view, int pl_like, Date pl_date) {
		super();
		this.pl_id = pl_id;
		this.pl_name = pl_name;
		this.pl_view = pl_view;
		this.pl_like = pl_like;
		this.pl_date = pl_date;
	}
	public int getPl_id() {
		return pl_id;
	}
	public void setPl_id(int pl_id) {
		this.pl_id = pl_id;
	}
	public  String getPl_name() {
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}