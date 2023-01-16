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
	private String au_id;
	private int comment;
	private int reported;
	
	public Review() {
		// TODO Auto-generated constructor stub
	}


	public Review(int id, String name, String img, String text, Date date, int view, int like, String au_id,
			int comment, int reported) {
		super();
		this.id = id;
		this.name = name;
		this.img = img;
		this.text = text;
		this.date = date;
		this.view = view;
		this.like = like;
		this.au_id = au_id;
		this.comment = comment;
		this.reported = reported;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public String getAu_id() {
		return au_id;
	}

	public void setAu_id(String au_id) {
		this.au_id = au_id;
	}

	public int getComment() {
		return comment;
	}

	public void setComment(int comment) {
		this.comment = comment;
	}


	public int getReported() {
		return reported;
	}


	public void setReported(int reported) {
		this.reported = reported;
	}
	
	
}
