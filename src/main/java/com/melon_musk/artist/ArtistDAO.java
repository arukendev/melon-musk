package com.melon_musk.artist;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.melon_musk.auth.Auth;
import com.melon_musk.main.Comment;
import com.melon_musk.main.DBManager;
import com.melon_musk.music.Music;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
			Connection con = null;
			PreparedStatement pstmt = null;
			String sql = "insert into artist_comment values(ar_comment_seq.nextval,?,?,?,current_date)";
			try {
				con = DBManager.connect();
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, a.getAu_id());
				pstmt.setString(2, request.getParameter("artistId"));
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
	
	public static void setLike(HttpServletRequest request) {
		Auth a = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "insert into artist_like values(ar_like_seq.nextval, ?, ?)";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("artistId"));
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
		String sql = "delete artist_like where arli_au_id = ? and arli_ar_id = ?";
		
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, a.getAu_id());
			pstmt.setString(2, request.getParameter("artistId"));
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
		Artist a = (Artist) request.getAttribute("artist");
		
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select count(*) from artist_like where arli_ar_id = ?";
		
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
		Artist a = (Artist) request.getAttribute("artist");
		Auth au = (Auth) request.getSession().getAttribute("account");
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "select * from artist_like where arli_ar_id = ? and arli_au_id = ?";
		
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
				request.setAttribute("likeAuth", rs.getString("arli_au_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, rs);
		}
	}

	public static void updateArtist(HttpServletRequest request) {
		
		Connection con  = null;
		PreparedStatement pstmt = null;
		String sql = "update artist "
				+ "set ar_img = ?, ar_company = ?, ar_debut = ?, ar_birth = ?, ar_info = ? "
				+ "where ar_id = ?";
		
		String path = request.getServletContext().getRealPath("files/artist");
		System.out.println(path);
		
		try {
			request.setCharacterEncoding("utf-8");
			MultipartRequest mr = new MultipartRequest(
				request,
				path,
				31457280,
				"utf-8",
				new DefaultFileRenamePolicy()
			);
			String artistId = mr.getParameter("artistId");
			String oldImg = mr.getParameter("oldImg");
			String img = mr.getFilesystemName("img");
			String company = mr.getParameter("company");
			String debut = mr.getParameter("debut");
			String birth = mr.getParameter("birth");
			String info = mr.getParameter("info");
			
			if (company == null || company.equals("")) {
				company = "none";
			}
			if (debut == null || debut.equals("")) {
				debut = "none";
			}
			if (birth == null || birth.equals("")) {
				birth = "none";
			} else {
				birth = birth.replace("-", ".");
			}
			if (info.equals("")) {
				info = "none";
			} else {
				info = info.replaceAll("\r\n", "<br>");
			}
			
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			if (img == null) {
				pstmt.setString(1, oldImg);
			} else {
				pstmt.setString(1, "files/artist/" + img);
				if (oldImg.contains("files/artist/")) {
					oldImg = oldImg.replace("files/artist/", "");
					String delImg = path + "/" + oldImg;
					File f = new File(delImg);
					f.delete();
				}
			}
			pstmt.setString(2, company);
			pstmt.setString(3, debut);
			pstmt.setString(4, birth);
			pstmt.setString(5, info);
			pstmt.setString(6, artistId);
			
			request.setAttribute("parameter", mr.getParameter("artistId"));
			
			if (pstmt.executeUpdate() == 1) {
				System.out.println("수정성공");
			}
			
		} catch (Exception e) {
			System.out.println("수정실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
		
	}

	public static void delArtist(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "delete artist where ar_id = ?";
		String path = request.getServletContext().getRealPath("files/artist");
		String oldImg = request.getParameter("i");
		if (oldImg.contains("files/artist/")) {
			oldImg = oldImg.replace("files/artist/", "");
			String delImg = path + "/" + oldImg;
			File f = new File(delImg);
			f.delete();
		}
		try {
			con = DBManager.connect();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("artistId"));
			if (pstmt.executeUpdate() == 1) {
				System.out.println("삭제성공");
			}
		} catch (Exception e) {
			System.out.println("삭제실패");
			e.printStackTrace();
		} finally {
			DBManager.close(con, pstmt, null);
		}
	}
}
