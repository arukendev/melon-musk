package com.melon_musk.auth;

public class Auth {

	
	private String au_id;
	private String au_pw;
	private String au_name;
	private String au_interest;
	private String au_introduce;
	private String au_img;
	public Auth(String au_id, String au_pw, String au_name, String au_interest, String au_introduce,String au_img) {
		super();
		this.au_id = au_id;
		this.au_pw = au_pw;
		this.au_name = au_name;
		this.au_interest = au_interest;
		this.au_introduce = au_introduce;
		this.au_img = au_img;
	}
	public Auth() {
		// TODO Auto-generated constructor stub
	}
	public String getAu_id() {
		return au_id;
	}
	public void setAu_id(String au_id) {
		this.au_id = au_id;
	}
	public String getAu_pw() {
		return au_pw;
	}
	public void setAu_pw(String au_pw) {
		this.au_pw = au_pw;
	}
	public String getAu_name() {
		return au_name;
	}
	public void setAu_name(String au_name) {
		this.au_name = au_name;
	}
	public String getAu_interest() {
		return au_interest;
	}
	public void setAu_interest(String au_interest) {
		this.au_interest = au_interest;
	}
	public String getAu_introduce() {
		return au_introduce;
	}
	public void setAu_introduce(String au_introduce) {
		this.au_introduce = au_introduce;
	}
	public String getAu_img() {
		return au_img;
	}
	public void setAu_img(String au_img) {
		this.au_img = au_img;
	}
	
	
}
