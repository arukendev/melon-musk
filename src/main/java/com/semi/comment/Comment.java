package com.semi.comment;

import java.util.Date;

public class Comment {
	private String authId;
	private String img;
	private String name;
	private String commentId;
	private String txt;
	private Date date;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(String authId, String img, String name, String commentId, String txt, Date date) {
		super();
		this.authId = authId;
		this.img = img;
		this.name = name;
		this.commentId = commentId;
		this.txt = txt;
		this.date = date;
	}
	public String getAuthId() {
		return authId;
	}
	public void setAuthId(String authId) {
		this.authId = authId;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
