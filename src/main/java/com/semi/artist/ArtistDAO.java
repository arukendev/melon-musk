package com.semi.artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.auth.Auth;
import com.semi.main.Comment;
import com.semi.main.DBManager;

public class ArtistDAO {

	public static void setArtist(HttpServletRequest request) {
		Artist a = (Artist) request.getAttribute("artist");
		ArrayList<Artists> m = (ArrayList<Artists>) request.getAttribute("members");
		String memberId = "";
		String memberName = "";
		if (m.size() == 0) {
			memberId = "none";
			memberName = "none";
		} else {
			ArrayList<String> memberIds = new ArrayList<String>();
			ArrayList<String> memberNames = new ArrayList<String>();
			for (Artists ar : m) {
				memberIds.add(ar.getId());
				memberNames.add(ar.getName());
			}
			memberId = String.join(",", memberIds);
			memberName = String.join(",", memberNames);
		}
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into artist values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getId());
			pstmt.setString(2, a.getName());
			pstmt.setString(3, memberId);
			pstmt.setString(4, memberName);
			pstmt.setString(5, a.getImg());
			pstmt.setString(6, a.getType());
			pstmt.setString(7, a.getCompany());
			pstmt.setString(8, a.getDebut());
			pstmt.setString(9, a.getBirth());
			pstmt.setString(10, a.getInfo());
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("등록 성공");
			}
		} catch (Exception e) {
			System.out.println("등록 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

	public static void getArtist(HttpServletRequest request) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from artist where ar_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("artistId"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Artist a = new Artist();
				Artists as = null;
				ArrayList<Artists> members = new ArrayList<Artists>();
				a.setId(rs.getString("ar_id"));
				a.setName(rs.getString("ar_name"));
				a.setImg(rs.getString("ar_img"));
				a.setType(rs.getString("ar_type"));
				a.setCompany(rs.getString("ar_company"));
				a.setDebut(rs.getString("ar_debut"));
				a.setBirth(rs.getString("ar_birth"));
				a.setInfo(rs.getString("ar_info"));
				String[] memberIds = rs.getString("ar_member_id").split(",");
				String[] memberNames = rs.getString("ar_member_name").split(",");
				for (int i = 0; i < memberIds.length; i++) {
					as = new Artists();
					as.setId(memberIds[i]);
					as.setName(memberNames[i]);
					members.add(as);
				}
				request.setAttribute("artist", a);
				request.setAttribute("members", members);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void setComment(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");
		Artist ar = (Artist) request.getAttribute("artist");
		
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "insert into artist_comment values(ar_comment_seq.nextval,?,?,?,current_date)";
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getAu_id());
				pstmt.setString(2, ar.getId());
				pstmt.setString(3, request.getParameter("txt"));
				
				if(pstmt.executeUpdate() == 1) {
					System.out.println("전송성공");
				}
				
			} catch (Exception e) {
				System.out.println("전송실패");
				e.printStackTrace();
			}finally {
				DBManager.close(con, pstmt, null);
			}
	}

	public static void getComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select au_id, au_img, au_name, arco_id, arco_date, arco_txt "
				+ "from auth, artist, artist_comment "
				+ "where arco_au_id = au_id and arco_ar_id = ar_id and ar_id = ? "
				+ "order by arco_date desc";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("artistId"));
			rs = pstmt.executeQuery();
			
			ArrayList<Comment> comments = new ArrayList<Comment>(); 
			Comment c = null;
			while (rs.next()) {
				c = new Comment();
				c.setAuthId(rs.getString("au_id"));
				c.setImg(rs.getString("au_img"));
				c.setName(rs.getString("au_name"));
				c.setCommentId(rs.getString("arco_id"));
				c.setDate(rs.getDate("arco_date"));
				c.setTxt(rs.getString("arco_txt"));
				comments.add(c);
			}
			
			request.setAttribute("comments", comments);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
		
	}

	public static void delComment(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete artist_comment where arco_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("commentId"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제성공");
			}
		} catch (Exception e) {
			System.out.println("삭제실패");
			e.printStackTrace();
		}finally {
			DBManager.close(con, pstmt, null);
		}
		
	}
}
