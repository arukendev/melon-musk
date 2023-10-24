package com.melon_musk.album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.melon_musk.artist.Artist;
import com.melon_musk.artist.Artists;
import com.melon_musk.auth.Auth;
import com.melon_musk.main.Comment;
import com.melon_musk.main.DBManager;

public class AlbumDAO {

	public static void setAlbum(HttpServletRequest request) {
		
		Album a = (Album) request.getAttribute("album");
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
		String sql = "insert into album values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getId());			
			pstmt.setString(2, artistId);
			pstmt.setString(3, artistName);
			pstmt.setString(4, a.getName());
			pstmt.setString(5, a.getImg());
			pstmt.setString(6, a.getType());
			pstmt.setString(7, a.getDate());
			pstmt.setString(8, a.getGenre());
			pstmt.setString(9, a.getPublisher());
			pstmt.setString(10, a.getAgency());
			pstmt.setString(11, a.getInfo());
			
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

	public static void getAlbum(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from album where al_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("albumId"));
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				Album a = new Album();
				Artists as = null;
				ArrayList<Artists> artists = new ArrayList<Artists>();
				a.setId(rs.getString("al_id"));
				a.setName(rs.getString("al_name"));
				a.setImg(rs.getString("al_img"));
				a.setType(rs.getString("al_type"));
				a.setDate(rs.getString("al_date"));
				a.setGenre(rs.getString("al_genre"));
				a.setPublisher(rs.getString("al_publisher"));
				a.setAgency(rs.getString("al_agency"));
				a.setInfo(rs.getString("al_info"));
				String[] artistIds = rs.getString("al_ar_id").split(",");
				String[] artistNames = rs.getString("al_ar_name").split(",");
				for (int i = 0; i < artistIds.length; i++) {
					as = new Artists();
					as.setId(artistIds[i]);
					as.setName(artistNames[i]);
					artists.add(as);
				}
				request.setAttribute("album", a);
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
		Album al = (Album) request.getAttribute("album");
		
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "insert into album_comment values(al_comment_seq.nextval,?,?,?,current_date)";
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getAu_id());
				pstmt.setString(2, al.getId());
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
		String sql = "select au_id, au_img, au_name, alco_id, alco_date, alco_txt "
				+ "from auth, album, album_comment "
				+ "where alco_au_id = au_id and alco_al_id = al_id and al_id = ? "
				+ "order by alco_date desc";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("albumId"));
			rs = pstmt.executeQuery();
			
			ArrayList<Comment> comments = new ArrayList<Comment>(); 
			Comment c = null;
			while (rs.next()) {
				c = new Comment();
				c.setAuthId(rs.getString("au_id"));
				c.setImg(rs.getString("au_img"));
				c.setName(rs.getString("au_name"));
				c.setCommentId(rs.getString("alco_id"));
				c.setDate(rs.getDate("alco_date"));
				c.setTxt(rs.getString("alco_txt"));
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
		String sql = "delete album_comment where alco_id = ?";
		
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
		String sql = "insert into album_like values(al_like_seq.nextval, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("albumId"));
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
		String sql = "delete album_like where alli_au_id = ? and alli_al_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("albumId"));
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
		Album a = (Album) request.getAttribute("album");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from album_like where alli_al_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getId());
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
		Album a = (Album) request.getAttribute("album");
		Auth au = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from album_like where alli_al_id = ? and alli_au_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getId());
			if (au == null) {
				pstmt.setString(2, "");
			} else {				
				pstmt.setString(2, au.getAu_id());
			}
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				request.setAttribute("likeAuth", rs.getString("alli_au_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void updateAlbum(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		String sql = "update album set al_info = ? where al_id = ?";
		
		try {
			request.setCharacterEncoding("utf-8");
			String info = request.getParameter("info");
			if (info.equals("")) {
				info = "none";
			} else {
				info = info.replaceAll("\r\n", "<br>");
			}
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, info);
			pstmt.setString(2, request.getParameter("albumId"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정완료");
			}
		} catch (Exception e) {
			System.out.println("수정실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}

}
