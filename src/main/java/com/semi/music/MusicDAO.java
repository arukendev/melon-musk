package com.semi.music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.semi.artist.Artists;
import com.semi.auth.Auth;
import com.semi.main.Comment;
import com.semi.main.DBManager;

public class MusicDAO {

	public static void setMusic(HttpServletRequest request) {
		
		Music m = (Music) request.getAttribute("music");
		ArrayList<Artists> ars = (ArrayList<Artists>) request.getAttribute("artists");
		ArrayList<String> artistIds = new ArrayList<String>();
		ArrayList<String> artistNames = new ArrayList<String>();
		
		String artistId = "";
		String artistName = "";
		
		for (Artists ar : ars) {
			artistIds.add(ar.getId());
			artistNames.add(ar.getName());
		}
		artistId = String.join(",", artistIds);
		artistName = String.join(",", artistNames);
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into music values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			pstmt.setString(2, artistId);
			pstmt.setString(3, artistName);
			pstmt.setString(4, m.getAlId());
			pstmt.setString(5, m.getAlName());
			pstmt.setString(6, m.getAlImg());
			pstmt.setString(7, m.getDate());
			pstmt.setString(8, m.getName());
			pstmt.setString(9, m.getGenre());
			pstmt.setString(10, m.getLyrics());
			pstmt.setString(11, m.getLink());
			
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

	public static void getMusic(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from music where mu_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("musicId"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Music m = new Music();
				Artists as = null;
				ArrayList<Artists> artists = new ArrayList<Artists>();
				m.setId(rs.getString("mu_id"));
				m.setAlId(rs.getString("mu_al_id"));
				m.setAlName(rs.getString("mu_al_name"));
				m.setAlImg(rs.getString("mu_al_img"));
				m.setDate(rs.getString("mu_date"));
				m.setName(rs.getString("mu_name"));
				m.setGenre(rs.getString("mu_genre"));
				m.setLyrics(rs.getString("mu_lyrics"));
				m.setLink(rs.getString("mu_link"));
				String[] artistIds = rs.getString("mu_ar_id").split(",");
				String[] artistNames = rs.getString("mu_ar_name").split(",");
				for (int i = 0; i < artistIds.length; i++) {
					as = new Artists();
					as.setId(artistIds[i]);
					as.setName(artistNames[i]);
					artists.add(as);
				}
				request.setAttribute("music", m);
				request.setAttribute("artists", artists);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void setComment(HttpServletRequest request) {
			
		Auth a = (Auth) request.getSession().getAttribute("account");
		
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "insert into music_comment values(mu_comment_seq.nextval,?,?,?,current_date)";
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getAu_id());
				pstmt.setString(2, request.getParameter("musicId"));
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
		String sql = "select au_id, au_img, au_name, muco_id, muco_date, muco_txt "
				+ "from auth, music, music_comment "
				+ "where muco_au_id = au_id and muco_mu_id = mu_id and mu_id = ? "
				+ "order by muco_date desc";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("musicId"));
			rs = pstmt.executeQuery();
			
			ArrayList<Comment> comments = new ArrayList<Comment>(); 
			Comment c = null;
			while (rs.next()) {
				c = new Comment();
				c.setAuthId(rs.getString("au_id"));
				c.setImg(rs.getString("au_img"));
				c.setName(rs.getString("au_name"));
				c.setCommentId(rs.getString("muco_id"));
				c.setDate(rs.getDate("muco_date"));
				c.setTxt(rs.getString("muco_txt"));
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
		String sql = "delete music_comment where muco_id = ?";
		
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
	
	public static void setLike(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into music_like values(mu_like_seq.nextval, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("musicId"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("좋아요 추가");
			}
		} catch (Exception e) {
			System.out.println("좋아요 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void delLike(HttpServletRequest request) {
		
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete music_like where muli_au_id = ? and muli_mu_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("musicId"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제 성공");
			}
			
		} catch (Exception e) {
			System.out.println("삭제 실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void getLikeCount(HttpServletRequest request) {
		Music m = (Music) request.getAttribute("music");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from music_like where muli_mu_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				request.setAttribute("likeCount", rs.getInt("count(*)"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void getLikeInfo(HttpServletRequest request) {
		Music m = (Music) request.getAttribute("music");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from music_like where muli_mu_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getId());
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				request.setAttribute("likeAuth", rs.getString("muli_au_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

}
