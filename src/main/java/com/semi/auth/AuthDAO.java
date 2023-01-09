package com.semi.auth;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.semi.main.DBManager;

public class AuthDAO {

	public static boolean loginCheck(HttpServletRequest request) {
		
		HttpSession hs = request.getSession();
				Auth a =(Auth)hs.getAttribute("account");
				System.out.println(a);
		if (a==null) {
			request.setAttribute("loginPage", "jsp/auth/login_before.jsp");
			return false;
		}else {
			request.setAttribute("loginPage", "jsp/auth/loginOK.jsp");
			return true;
		}
		
		
		
		
	}
	
	
	public static boolean profile(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a = (Auth) hs.getAttribute("account");
		if (a != null) {
			request.setAttribute("account", a);
			return true;
		}
		return false;
	}
	public static void login(HttpServletRequest request) {
		String userid= request.getParameter("id");
		String userpw= request.getParameter("pw");
		
		//���� ����� ����Ǹ� �Ʒ��� ������ �Ƿ����� ��
		String iddd=(String) request.getAttribute("iddd");
		String pwww= (String)request.getAttribute("pwww");
		
		if (iddd!=null) {
			userid = iddd;
			userpw = pwww;
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select *from auth where au_id=?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				
				if (userpw.equals(rs.getString("au_pw"))) {
					request.setAttribute("r","로그인 성공");
					
					//id,name 
					Auth a = new Auth();
					a.setAu_id(rs.getString("au_id"));
					a.setAu_pw(rs.getString("au_pw"));
				//	a.setAu_addr(rs.getString("a_addr"));
				//a.setAu_gender(rs.getString("a_gender"));
					a.setAu_name(rs.getString("au_name"));
					a.setAu_interest(rs.getString("au_interest"));
					a.setAu_introduce(rs.getString("au_introduce"));
					a.setAu_img(rs.getString("au_img"));
				
					
			HttpSession hs =request.getSession();
			hs.setAttribute("account", a);
			hs.setMaxInactiveInterval(30*10);
				}else {
					request.setAttribute("r","비번에러");
					request.setAttribute("contentPage", "jsp/auth/login.jsp");

				}
			}else {
				request.setAttribute("r","없는아이디");
				request.setAttribute("contentPage", "jsp/auth/login.jsp");

			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, rs);
		}
	}


	public static void logout(HttpServletRequest req) {
		
		//로그아웃하는 일
				//세션 죽이기
		
		HttpSession hs = req.getSession();
		
		hs.setAttribute("account", null);
		//hs.removeAttribute("account");
				//hs.invalidate();
				//애초에 만들어진적도 없거나, 설정시간 만료
		loginCheck(req);
	}


	public static void setAccount(HttpServletRequest request) {
		//가입하는 일-crud	-c
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into auth values(?,?,?,?,?,?)";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			String path = request.getSession().getServletContext().getRealPath("files/auth");
			
			
			MultipartRequest mr = new MultipartRequest(request,path,20*1024*1024,"utf-8",new DefaultFileRenamePolicy());
			
				
			String id = mr.getParameter("id");
			String pw = mr.getParameter("pw");
			String name = mr.getParameter("name");
		//	String gender = mr.getParameter("gender");
		//	String addr = mr.getParameter("addr");
			String[] interest = mr.getParameterValues("interest");
			String introduce = mr.getParameter("introduce");
			String file = mr.getFilesystemName("file");
			String interest2="";
			
			if(interest != null) {
				
			for (String s : interest) {
				System.out.println(s);
				interest2 += s+"!";//food!excer!game,dev
			}
			}else {
				interest2="관심사없음";
			}
			
			if (introduce.isEmpty()) {
				introduce="...";
			}
			
			System.out.println(name);
			System.out.println(id);
			System.out.println(pw);
		//	System.out.println(addr);
		//	System.out.println(gender);
			System.out.println(interest2);
			System.out.println(file);
			System.out.println(introduce);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
		//	pstmt.setString(5, gender);
		//	pstmt.setString(6, addr);
			pstmt.setString(4, interest2);
			pstmt.setString(5, introduce);
			pstmt.setString(6, file);
			
			if (pstmt.executeUpdate()==1) {
				System.out.println("가입성공");
				request.setAttribute("r", "가입성공!!");
			}
			
			//받은 값 다 띄우기
			
			//request.getParameter(sql)
		} catch (Exception e) {
		e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
	}


	public static int makeInterest(HttpServletRequest request) {

		
		Auth a =(Auth)request.getSession().getAttribute("account");
		
	if(a!=null){
		String interest = a.getAu_interest();//요리!영화!게임!개발!
		String[]interest2=	interest.split("!");		
		request.setAttribute("inter", interest2);
		return 1;
	}
	return 0;	
		
	}


	public static void delAccount(HttpServletRequest request) {

		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete auth where au_id=?";
		
		Auth a = (Auth) request.getSession().getAttribute("account");
		try {
			con= DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			
			if (pstmt.executeUpdate()==1) {
				request.setAttribute("r", "탈퇴성공");
				logout(request);
			}
		} catch (Exception e) {
			request.setAttribute("r", "탈퇴실패");
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
	}


	public static void updateAccount(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "update auth set au_img=? ,au_name=?,au_pw=?,au_interest=?,au_introduce=? where au_id=?";
	
		HttpSession hs = request.getSession();
		Auth a = (Auth) hs.getAttribute("account");
		
		try {
			request.setCharacterEncoding("utf-8");
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			
			String path = request.getSession().getServletContext().getRealPath("files/auth");
			
			
			MultipartRequest mr = new MultipartRequest(request,path,20*1024*1024,"utf-8",new DefaultFileRenamePolicy());
			
			String name = mr.getParameter("name");
			String pw = mr.getParameter("pw");
		//	String addr = mr.getParameter("addr");
			String[] interest = mr.getParameterValues("interest");
			String introduce = mr.getParameter("introduce");
			String oldFile = mr.getParameter("img");//11.jpg��������
			String newFile =mr.getFilesystemName("img2");//사진을 새롭게 추가	
			
			
//			String name = request.getParameter("name");
//			String pw = request.getParameter("pw");
//			
//			String[] interest = request.getParameterValues("interest");
//			String introduce = request.getParameter("introduce");
//			
			
			
			
			
			
			
			String interest2="";
			
			if(interest != null) {
				
			for (String s : interest) {
				System.out.println(s);
				interest2 +=s+"!";//food!excer!game,dev
			}
			}else {
				interest2="관심사없음";
			}
			
			if (introduce.equals("")) {
				introduce="...";
			}
			System.out.println(name);
			System.out.println(pw);
		
			System.out.println(interest2);
			System.out.println(introduce);
			
			
			pstmt.setString(2, name);
			pstmt.setString(3, pw);
			pstmt.setString(4, interest2);
			pstmt.setString(5, introduce);
			
			if (newFile == null) {
				pstmt.setString(1, oldFile);
				a.setAu_img(oldFile);
			} else {				
				a.setAu_img(newFile);
				oldFile = oldFile.replace("files/auth/", "");
				String delFile = path + "/" + oldFile;
				File f = new File(delFile);
				f.delete();
			}
			
			pstmt.setString(6, a.getAu_id());
		if(a!=null) {	
			if (pstmt.executeUpdate()==1) {
				System.out.println("수정성공");
				request.setAttribute("r", "수정성공");
			
				request.setAttribute("iddd", a.getAu_id());
				request.setAttribute("pwww", pw);
				a.setAu_name(name);
				a.setAu_pw(pw);
				a.setAu_interest(interest2);
				a.setAu_introduce(introduce);
				
//				
//				login(request);
//				makeInterest(request);
			}
		}	
		
			//request.getParameter(sql)
		} catch (Exception e) {
			request.setAttribute("r", "실패!");
			System.out.println("실패");
		e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	}


//public static void getAccount(HttpServletRequest request) {
////1.세션가져오기
//Account a =(Account)request.getSession().getAttribute("account");
//a.getA_id();
////2. 파라미터 실어주기
//request.getParameter("id");
//
//Connection con = null;
//PreparedStatement pstmt = null;
//String sql = "select *from";
//
//}

	
	

