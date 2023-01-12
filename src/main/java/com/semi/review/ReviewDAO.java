package com.semi.review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.semi.auth.Auth;
import com.semi.main.DBManager;
import com.semi.review.Review;


public class ReviewDAO {
	private static ArrayList<Review> reviews;
	
	public static void getAllReview(HttpServletRequest request) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "select * from review order by re_id";

			reviews = new ArrayList<Review>();
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				Review r = null;
				while(rs.next()) {
					// Bean
					r = new Review();
					r.setId(rs.getInt("re_id"));
					r.setName(rs.getString("re_name"));
					r.setImg(rs.getString("re_img"));
					r.setText(rs.getString("re_text"));
					r.setView(rs.getInt("re_view"));
					r.setLike(rs.getInt("re_like"));
					r.setDate(rs.getDate("re_date"));
					reviews.add(r);
				}
				request.setAttribute("reviews", reviews);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				DBManager.close(con, pstmt, rs);
			}
			
		}

	public static void reviewReg(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			request.setCharacterEncoding("utf-8");

			String sql = "insert into review values(review_seq.nextval,"
					+ "?, ?, ?, 0, 0, sysdate, ?)";

			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("name"));
			pstmt.setString(2, request.getParameter("img"));
			pstmt.setString(3, request.getParameter("text"));
			pstmt.setString(4, a.getAu_id());
			
			if(pstmt.executeUpdate() == 1) {
				System.out.println("등록성공");
			}
			
			getReview2(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}

	}

	public static void paging(int page, HttpServletRequest request) {
		
			int cnt = 10;
			int total = reviews.size();
			int pageCount = (int)Math.ceil((double)total / cnt);
			
			int start = cnt * (page - 1) + 1;
			int end = (page == pageCount) ? total : start + cnt - 1;
			
			ArrayList<Review> items = new ArrayList<Review>();
			for (int i = start-1 ; i < end; i++) {
				items.add(reviews.get(i));
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("reviews", items);
			
	}

	public static void getReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();
			int viewCnt = rs.getInt("re_view");
			viewCnt++;
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), rs.getInt("re_view"), rs.getInt("re_like"),  rs.getString("re_au_id"));
			countUpdate(viewCnt, rs.getInt("re_id"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	public static void getReview2(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_name = ? and re_img= ? and re_text= ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("name"));
			pstmt.setString(2, request.getParameter("img"));
			pstmt.setString(3, request.getParameter("text"));
			rs = pstmt.executeQuery();
			rs.next();
			int viewCnt = rs.getInt("re_view");
			viewCnt++;
			countUpdate(viewCnt, rs.getInt("re_id"));
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), rs.getInt("re_view"), rs.getInt("re_like"), rs.getString("re_au_id"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}
	
	private static void countUpdate(int viewCnt, int re_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_view = ? where re_id = ?"
					);
			pstmt.setInt(1, viewCnt);
			pstmt.setInt(2, re_id);
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void updateReview(HttpServletRequest request) {
		
			Connection con = null;
			PreparedStatement pstmt = null;
			
			
			try {
				request.setCharacterEncoding("utf-8");
				con = DBManager.connect();
				pstmt = con.prepareStatement(
						"update review set re_name= ?, re_img= ?, re_text= ? where re_id= ?"
						);
				pstmt.setString(1, request.getParameter("name"));
				pstmt.setString(2, request.getParameter("img"));
				pstmt.setString(3, request.getParameter("text"));
				pstmt.setString(4, request.getParameter("no"));
				
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	public static void deleteReview(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
			"delete from review where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			if(pstmt.executeUpdate() == 1) {
				request.setAttribute("result", "successfully deleted");
			}
		} catch (Exception e) {
			request.setAttribute("result", "db Error");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getReview3(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from review where re_id = ?";
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("no"));
			rs = pstmt.executeQuery();
			rs.next();
			Review r = new Review(rs.getInt("re_id"), rs.getString("re_name"), rs.getString("re_img"), rs.getString("re_text"), rs.getDate("re_date"), rs.getInt("re_view"), rs.getInt("re_like"),  rs.getString("re_au_id"));
			request.setAttribute("review", r);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}


	public static void reviewLike(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"insert into review_like values(review_like_seq.nextval, ?, ?)"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			pstmt.executeUpdate();
			
			reviewLikeUpdate(request);
			
			Like l = new Like();
			l.setAu_id(a.getAu_id());
			l.setRe_id(Integer.parseInt(request.getParameter("no")));
			
			request.setAttribute("like", l);
			
			
		} catch (Exception e) {
			request.setAttribute("alert", "좋아요는 1번만 가능합니다.");
			e.printStackTrace();
		} 
		
		finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	private static void reviewLikeUpdate(HttpServletRequest request) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_like = re_like+1 where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
	
	private static void reviewLikeCancelUpdate(HttpServletRequest request) {
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"update review set re_like = re_like-1 where re_id = ?"
					);
			pstmt.setString(1, request.getParameter("no"));
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getLike(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"select * from review_like where reli_au_id=? and reli_re_id=?"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			rs = pstmt.executeQuery();
			if(rs.next()) {			
			Like l = new Like();
			l.setAu_id(rs.getString("reli_au_id"));
			l.setRe_id(rs.getInt("reli_re_id"));
			System.out.println("like.au_id"+l.getAu_id());
			request.setAttribute("like", l);
			} 
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void reviewLikeCancel(HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Auth a =(Auth)hs.getAttribute("account");
		request.setAttribute("a", a);
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(
					"delete from review_like where reli_au_id=? and reli_re_id=?"
					);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("no"));
			pstmt.executeUpdate();
			reviewLikeCancelUpdate(request);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	
	
	

}
