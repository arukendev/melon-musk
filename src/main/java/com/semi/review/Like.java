package com.semi.review;

public class Like {
	private String au_id;
	private int re_id;
	
	public Like() {
		// TODO Auto-generated constructor stub
	}

	public Like(String au_id, int re_id) {
		super();
		this.au_id = au_id;
		this.re_id = re_id;
	}

	public String getAu_id() {
		return au_id;
	}

	public void setAu_id(String au_id) {
		this.au_id = au_id;
	}

	public int getRe_id() {
		return re_id;
	}

	public void setRe_id(int re_id) {
		this.re_id = re_id;
	}
	
}
