package com.semi.review;

import java.util.Date;

public class Review {
	private int id;
	private String name;
	private String img;
	private String text;
	private Date date;
	private int view;
	private int like;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}

	public Review(int id, String name, String img, String text, int view, int like, Date date) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.text = text;
		this.view = view;
		this.like = like;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getView() {
		return view;
	}

	public void setView(int view) {
		this.view = view;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
